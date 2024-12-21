package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.questions.QuestionRepository;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestionRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.PatchAnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizResultService {
  private final QuizResultRepository quizResultRepository;
  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final AnsweredQuestionRepository answeredQuestionRepository;
  private final AnswerRepository answerRepository;

  public GetQuizResult create(PostQuizResult dto) {
    QuizResult quizResult = new QuizResult();

    Quiz quiz = quizRepository.findById(dto.quizId()).orElseThrow(NotFoundException::new);
    quizResult.setQuiz(quiz);

    List<AnsweredQuestion> questions =
        questionRepository.findQuestionByQuiz(quiz).stream()
            .map(question -> copyQuestion(question))
            .toList();
    quizResult.add(questions);

    quizResultRepository.save(quizResult);

    return GetQuizResult.from(quizResult);
  }

  public AnsweredQuestion copyQuestion(Question question) {
    Integer validAnswerCount = 0;
    for (Answer answer : question.getAnswers()) {
      if (answer.getIsCorrect()) validAnswerCount++;
    }
    AnsweredQuestion copiedQuestion = new AnsweredQuestion(question, validAnswerCount);
    return answeredQuestionRepository.save(copiedQuestion);
  }

  public List<GetQuizResult> getAll() {
    return quizResultRepository.findAll().stream()
        .map(result -> GetQuizResult.from(result))
        .toList();
  }

  public GetQuizResult getById(Long id) {
    QuizResult quizResult = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);
    return GetQuizResult.from(quizResult);
  }

  public GetQuizResult submit(Long id) {
    QuizResult result = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);
    if (result.getIsCompleted()) {
      throw new BadRequestException("quiz has already been submitted");
    }

    List<AnsweredQuestion> questions = result.getQuestions();
    for (AnsweredQuestion question : questions) {
      if (isAnsweredCorrect(question)) {
        result.addPoint();
      }
    }
    result.setIsCompleted(true);
    quizResultRepository.save(result);
    return GetQuizResult.from(result);
  }

  public Boolean isAnsweredCorrect(AnsweredQuestion question) {
    // TODO als een antwoord fout is dan flag the tag
    List<Answer> answers = question.getSelectedAnswers();

    if (answers.size() != question.getValidAnswerCount()) return false;

    for (Answer answer : answers) {
      if (!answer.getIsCorrect()) return false;
    }
    return true;
  }

  public GetQuizResult submitAnswer(Long id, PatchAnsweredQuestion patch) {
    QuizResult result = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);

    List<Answer> answers = answerRepository.findAllById(patch.answerIds());
    if (patch.answerIds().size() != answers.size()) {
      throw new BadRequestException("Invalid Answer Id");
    }

    AnsweredQuestion question =
        answeredQuestionRepository.findById(patch.questionId()).orElseThrow(NotFoundException::new);

    question.setSelectedAnswers(answers);

    quizResultRepository.save(result);
    return GetQuizResult.from(result);
  }
}

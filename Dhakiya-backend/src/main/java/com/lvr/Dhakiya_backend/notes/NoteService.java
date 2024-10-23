package com.lvr.Dhakiya_backend.notes;

import com.lvr.Dhakiya_backend.flashcards.CreateFlashcardDto;
import com.lvr.Dhakiya_backend.flashcards.Flashcard;
import com.lvr.Dhakiya_backend.flashcards.FlashcardRepository;
import com.lvr.Dhakiya_backend.notes.notecollection.CreateNoteDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note create(CreateNoteDto note) {
        return noteRepository.save(CreateNoteDto.to(note));
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note getById(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        noteRepository.findById(id).orElseThrow();
        noteRepository.deleteById(id);
    }
}

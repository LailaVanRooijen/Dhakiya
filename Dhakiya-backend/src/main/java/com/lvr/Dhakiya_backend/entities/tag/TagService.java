package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.tag.dto.CreateTag;
import com.lvr.Dhakiya_backend.entities.tag.dto.PatchTag;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;
  private final EnvironmentRepository environmentRepository;

  public Tag create(CreateTag dto) {
    Tag createdTag = CreateTag.to(dto);

    Environment environment =
        environmentRepository
            .findById(dto.environmentId())
            .orElseThrow(() -> new BadRequestException("Environment not found"));
    createdTag.setEnvironment(environment);

    tagRepository.save(createdTag);

    return createdTag;
  }

  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  public Tag getById(Long id) {
    return tagRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public Tag patch(Long id, PatchTag patch) {
    Tag tag = tagRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.tag() != null && !patch.tag().isEmpty()) {
      tag.setTitle(patch.tag());
    }
    if (patch.isFlaggedPositive() != null) {
      if (patch.isFlaggedPositive() == true) {
        tag.setFlaggedPositiveCount(tag.getFlaggedPositiveCount() + 1);
      }
      tag.setSeenCount(tag.getSeenCount() + 1);
    }
    if (patch.reset() != null && patch.reset() == true) {
      tag.setSeenCount(0);
      tag.setFlaggedPositiveCount(0);
    }

    return tagRepository.save(tag);
  }

  public void delete(Long id) {
    tagRepository.findById(id).orElseThrow(NotFoundException::new);
    tagRepository.deleteById(id);
  }
}

package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
  public final TagRepository tagRepository;
  public final EnvironmentRepository environmentRepository;

  public Tag createTag(CreateTag dto) {
    Tag createdTag = CreateTag.to(dto);
    Environment environment =
        environmentRepository
            .findById(dto.environmentId())
            .orElseThrow(() -> new BadRequestException("Environment not found"));
    createdTag.setEnvironment(environment);
    return tagRepository.save(createdTag);
  }

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public Tag getById(Long id) {
    return tagRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public Tag patchTag(Long id, PatchTag patch) {
    Tag tag = tagRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.tag() != null && !patch.tag().isEmpty()) {
      tag.setTag(patch.tag());
    }
    if (patch.isSeen() != null && patch.isSeen() == true) {
      tag.setSeenCount(tag.getSeenCount() + 1);
    }
    if (patch.isFlaggedNegative() != null && patch.isFlaggedNegative() == true) {
      tag.setFlaggedNegativeCount(tag.getFlaggedNegativeCount() + 1);
    }
    if (patch.reset() != null && patch.reset() == true) {
      tag.setSeenCount(0);
      tag.setFlaggedNegativeCount(0);
    }

    return tagRepository.save(tag);
  }

  public void deleteTag(Long id) {
    tagRepository.findById(id).orElseThrow(NotFoundException::new);
    tagRepository.deleteById(id);
  }
}

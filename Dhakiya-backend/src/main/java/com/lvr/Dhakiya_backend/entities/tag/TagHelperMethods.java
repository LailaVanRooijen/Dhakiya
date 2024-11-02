package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagHelperMethods {
  private final TagRepository tagRepository;

  public Set<Tag> convertToTags(List<Long> tagIds) {
    Set<Tag> tags =
        tagIds.stream()
            .map(
                id ->
                    tagRepository
                        .findById(id)
                        .orElseThrow(() -> new BadRequestException("invalid tag id")))
            .collect(Collectors.toSet());
    if (tags.size() < tagIds.size()) {
      throw new BadRequestException("duplicate tags are not allowed");
    }
    return tags;
  }
}

package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;

  public Tag create(TagDto dto) {
    return tagRepository.save(TagDto.to(dto));
  }

  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  public Tag getById(Long id) {
    return tagRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    tagRepository.findById(id).orElseThrow(NotFoundException::new);
    tagRepository.deleteById(id);
  }

  public Tag update(Long id, TagDto patch) {
    Tag tag = tagRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.name() != null) tag.setName(patch.name());
    return tagRepository.save(tag);
  }
}

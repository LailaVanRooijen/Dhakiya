package com.lvr.Dhakiya_backend.tag;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;

  public Tag create(TagDto dto) {
    System.out.println("creating a tag"); // todo remove me later, debug
    return tagRepository.save(TagDto.to(dto));
  }

  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  public Tag getById(Long id) {
    return tagRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}

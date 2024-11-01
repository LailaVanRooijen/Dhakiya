package com.lvr.Dhakiya_backend.tag;

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
    System.out.println("getting all");
    return tagRepository.findAll();
  }
}

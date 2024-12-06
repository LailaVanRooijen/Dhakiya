package com.lvr.Dhakiya_backend.entities.noteset;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteSetService {
  private final NoteSetRepository noteSetRepository;

  public List<NoteSet> getAll() {
      return noteSetRepository.findAll();
  }

  public NoteSet getById(Long id) {
      return noteSetRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}

package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;

  public NoteCollection getById(Long id) {
    return noteCollectionRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}

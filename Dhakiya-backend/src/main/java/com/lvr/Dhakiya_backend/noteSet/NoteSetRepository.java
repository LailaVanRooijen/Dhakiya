package com.lvr.Dhakiya_backend.noteSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteSetRepository extends JpaRepository<NoteSet, Long> {}

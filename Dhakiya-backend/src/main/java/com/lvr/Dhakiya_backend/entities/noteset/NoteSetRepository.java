package com.lvr.Dhakiya_backend.entities.noteset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteSetRepository extends JpaRepository<NoteSet, Long> {}

package com.lvr.Dhakiya_backend.entities.notecollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteCollectionRepository extends JpaRepository<NoteCollection, Long> {}
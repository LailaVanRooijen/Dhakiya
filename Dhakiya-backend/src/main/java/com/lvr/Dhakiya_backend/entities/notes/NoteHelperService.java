package com.lvr.Dhakiya_backend.entities.notes;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteHelperService {
    private final NoteRepository noteRepository;

    public List<Note> convertToNoteList(List<Long> noteIds){
        return noteIds.stream().map(id-> noteRepository.findById(id).orElseThrow(NotFoundException::new)).toList();
    }

    public Set<Note> convertToNoteSet(List<Long> noteIds){
        return noteIds.stream().map(id-> noteRepository.findById(id).orElseThrow(NotFoundException::new)).collect(Collectors.toSet());
    }
}

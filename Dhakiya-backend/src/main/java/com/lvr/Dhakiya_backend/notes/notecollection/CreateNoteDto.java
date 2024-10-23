package com.lvr.Dhakiya_backend.notes.notecollection;

import com.lvr.Dhakiya_backend.notes.Note;

public record CreateNoteDto(String title, String content, String label) {
    public static CreateNoteDto from(Note note){
        return new CreateNoteDto(note.getTitle(),note.getContent(),note.getLabel());
    }

    public static Note to(CreateNoteDto noteDto){
        return new Note(noteDto.title(),noteDto.content(),noteDto.label);
    }
}

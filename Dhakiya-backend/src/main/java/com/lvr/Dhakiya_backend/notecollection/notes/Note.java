package com.lvr.Dhakiya_backend.notecollection.notes;

import com.lvr.Dhakiya_backend.notecollection.NoteCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTES;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Note {
  @GeneratedValue @Id Long id;

  private String title;
  private String content;
  private String label;

  @ManyToOne private NoteCollection noteCollection;

  public Note(String title, String content, String label) {
    this.title = title;
    this.content = content;
    this.label = label;
  }
}

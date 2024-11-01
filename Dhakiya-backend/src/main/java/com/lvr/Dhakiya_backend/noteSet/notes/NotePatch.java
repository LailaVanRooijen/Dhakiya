package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.tag.Tag;
import java.util.Set;

public record NotePatch(String title, String content, Set<Tag> tags) {}

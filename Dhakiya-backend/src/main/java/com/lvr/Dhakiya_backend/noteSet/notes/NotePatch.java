package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.tag.Tag;
import java.util.List;

public record NotePatch(String title, String content, List<Tag> tags) {}

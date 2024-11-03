package com.lvr.Dhakiya_backend.entities.notes;

import java.util.List;

public record NotePatch(
    Long noteSetId, String title, String content, List<Long> addTags, List<Long> deleteTags) {}

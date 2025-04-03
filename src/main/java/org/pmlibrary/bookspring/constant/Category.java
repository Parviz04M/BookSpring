package org.pmlibrary.bookspring.constant;

public enum Category {
    LITERARY_FICTION("Literary Fiction"),
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    ROMANCE("Romance"),
    ADVENTURE_FICTION("Adventure Fiction");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


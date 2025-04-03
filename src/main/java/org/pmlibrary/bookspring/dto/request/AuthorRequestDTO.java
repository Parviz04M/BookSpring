package org.pmlibrary.bookspring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDTO {
    private String firstName;
    private String lastName;
    private String nationality;
    private String yearsThatLived;
    private Boolean stillAlive;
}

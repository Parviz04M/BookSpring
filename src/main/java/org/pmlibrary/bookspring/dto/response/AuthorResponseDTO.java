package org.pmlibrary.bookspring.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String yearsThatLived;
    private Boolean stillAlive;
}

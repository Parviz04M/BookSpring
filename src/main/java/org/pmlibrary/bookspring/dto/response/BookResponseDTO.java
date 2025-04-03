package org.pmlibrary.bookspring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.pmlibrary.bookspring.constant.Category;
import org.pmlibrary.bookspring.entity.AuthorEntity;

@Getter
@Setter
public class BookResponseDTO {
    private Long id;
    private String title;
    private Category category;
    private Integer publicationDate;
    private Integer price;
    private AuthorEntity author;
}

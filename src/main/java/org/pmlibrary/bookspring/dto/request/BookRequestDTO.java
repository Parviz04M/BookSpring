package org.pmlibrary.bookspring.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.pmlibrary.bookspring.constant.Category;

@Getter
@Setter
public class BookRequestDTO {
    private String title;
    private Category category;
    private Integer publicationDate;
    private Integer price;
    private Long authorId;
}

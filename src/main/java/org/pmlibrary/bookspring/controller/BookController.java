package org.pmlibrary.bookspring.controller;

import org.pmlibrary.bookspring.dto.request.BookRequestDTO;
import org.pmlibrary.bookspring.dto.response.BookResponseDTO;
import org.pmlibrary.bookspring.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.0.1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntity<>(bookService.create(bookRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<BookResponseDTO> readAll() {
        return bookService.readAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO readById(@PathVariable Long id) {
        return bookService.readByID(id);
    }

    @GetMapping("/read-by-title/{title}")
    public BookResponseDTO readByTitle(@PathVariable String title) {
        return bookService.readBookByTitle(title);
    }

    @PutMapping("/update")
    public ResponseEntity<BookResponseDTO> update(@RequestParam Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntity<>(bookService.update(id, bookRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return bookService.delete(id);
    }
}



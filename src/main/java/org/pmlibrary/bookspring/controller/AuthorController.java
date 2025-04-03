package org.pmlibrary.bookspring.controller;

import org.pmlibrary.bookspring.dto.request.AuthorRequestDTO;
import org.pmlibrary.bookspring.dto.response.AuthorResponseDTO;
import org.pmlibrary.bookspring.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.0.1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponseDTO> create(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return new ResponseEntity<>(authorService.create(authorRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<AuthorResponseDTO> readAll(){
        return authorService.readAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO readById(@PathVariable Long id) {
        return authorService.readByID(id);
    }

    @GetMapping("/read-by-first-or-last-name")
    public AuthorResponseDTO readByFirstOrLastName(@RequestParam String first, @RequestParam String last) {
        return authorService.readAuthorByFirstNameOrLastName(first, last);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorResponseDTO> update(@RequestParam Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        return new ResponseEntity<>(authorService.update(id, authorRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return authorService.delete(id);
    }
}


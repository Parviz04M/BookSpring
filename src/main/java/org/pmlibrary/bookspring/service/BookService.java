package org.pmlibrary.bookspring.service;

import org.modelmapper.ModelMapper;
import org.pmlibrary.bookspring.dto.request.BookRequestDTO;
import org.pmlibrary.bookspring.dto.response.BookResponseDTO;
import org.pmlibrary.bookspring.entity.AuthorEntity;
import org.pmlibrary.bookspring.entity.BookEntity;
import org.pmlibrary.bookspring.exception.ResourceNotFoundException;
import org.pmlibrary.bookspring.repository.AuthorRepository;
import org.pmlibrary.bookspring.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    public BookResponseDTO create(BookRequestDTO bookRequestDTO) {
        AuthorEntity authorEntity = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", bookRequestDTO.getAuthorId()));

        BookEntity bookEntity = modelMapper.map(bookRequestDTO, BookEntity.class);
        bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);

        return modelMapper.map(bookEntity, BookResponseDTO.class);
    }

    public List<BookResponseDTO> readAll() {
        List<BookEntity> books = bookRepository.findAll();

        return books
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookResponseDTO.class))
                .toList();
    }

    public BookResponseDTO readByID(Long id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", id)), BookResponseDTO.class);
    }

    public BookResponseDTO update(Long id, BookRequestDTO bookRequestDTO) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));

        AuthorEntity authorEntity = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", bookRequestDTO.getAuthorId()));

        modelMapper.map(bookRequestDTO, bookEntity);
        bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);

        return modelMapper.map(bookEntity, BookResponseDTO.class);
    }

    public ResponseEntity<String> delete(Long id) {
        if (!bookRepository.existsById(id)) {
            return new ResponseEntity<>("Book with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book with id " + id + " deleted successfully", HttpStatus.OK);
    }

    public BookResponseDTO readBookByTitle(String title) {
        return modelMapper.map(bookRepository.readBookByTitleIgnoreCase(title), BookResponseDTO.class);
    }
}


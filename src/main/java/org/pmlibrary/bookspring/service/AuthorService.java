package org.pmlibrary.bookspring.service;

import org.modelmapper.ModelMapper;
import org.pmlibrary.bookspring.dto.request.AuthorRequestDTO;
import org.pmlibrary.bookspring.dto.response.AuthorResponseDTO;
import org.pmlibrary.bookspring.entity.AuthorEntity;
import org.pmlibrary.bookspring.exception.ResourceNotFoundException;
import org.pmlibrary.bookspring.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorResponseDTO create(AuthorRequestDTO authorRequestDTO) {
        AuthorEntity authorEntity = modelMapper.map(authorRequestDTO, AuthorEntity.class);
        authorRepository.save(authorEntity);
        return modelMapper.map(authorEntity, AuthorResponseDTO.class);
    }

    public List<AuthorResponseDTO> readAll() {
        List<AuthorEntity> authors = authorRepository.findAll();

        return authors
                .stream()
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorResponseDTO.class))
                .toList();
    }

    public AuthorResponseDTO readByID(Long id) {
        return modelMapper.map(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", id)), AuthorResponseDTO.class);
    }

    public AuthorResponseDTO update(Long id, AuthorRequestDTO authorRequestDTO) {
        return authorRepository.findById(id)
                .map(authorEntity -> {
                    modelMapper.map(authorRequestDTO, authorEntity);
                    authorRepository.save(authorEntity);
                    return modelMapper.map(authorEntity, AuthorResponseDTO.class);
                }).orElseThrow(() -> new ResourceNotFoundException("Author", id));
    }

    public ResponseEntity<String> delete(Long id) {
        if (!authorRepository.existsById(id)) {
            return new ResponseEntity<>("Author with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        authorRepository.deleteById(id);
        return new ResponseEntity<>("Author with id " + id + " deleted successfully", HttpStatus.OK);
    }

    public AuthorResponseDTO readAuthorByFirstNameOrLastName(String first, String last) {
        return modelMapper.map(authorRepository.readAuthorByFirstNameOrLastNameIgnoreCase(first, last), AuthorResponseDTO.class);
    }
}



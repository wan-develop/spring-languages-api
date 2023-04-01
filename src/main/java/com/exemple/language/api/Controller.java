package com.exemple.language.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class Controller {

    @Autowired
    private LanguageRepository repository;

    //Home
    @GetMapping(value = "/")
    public String getOnline() {
        return "online";
    }

    //GET ALL
    @GetMapping(value = "/languages")
    public ResponseEntity<List<Language>> getLanguages() {
        List<Language> languages = repository.findAll(Sort.by(Direction.ASC, "ranking"));
        return ResponseEntity.ok(languages);
    }

    //GET BY ID
    @GetMapping(value = "/language/{id}")
    public ResponseEntity<Language> getLanguages(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    //ADD
    @PostMapping(value = "/add")
    public ResponseEntity<String> postAdd(@RequestBody Language newLanguage) {
        repository.save(newLanguage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //UPDATE
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> putUpdate(@PathVariable String id, @RequestBody Language newLanguage) {
        
        Optional<Language> oldData = repository.findById(id);

        if(!oldData.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        Language updatedLanguage = oldData.get();

        updatedLanguage.updateFrom(newLanguage);

        repository.save(updatedLanguage);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //DELETE
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUpdate(@PathVariable String id) {
        
        if (!repository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.model.UsersModel;
import com.google.backend.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = CollageConstant.USER_CONTEXT)
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UsersModel model) {
        return ResponseEntity.ok(service.create(model));
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

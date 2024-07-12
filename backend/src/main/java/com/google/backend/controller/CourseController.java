package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.model.CourseModel;
import com.google.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = CollageConstant.COURSE_CONTEXT)
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping(value = CollageConstant.CREATE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CourseModel model) {
        return ResponseEntity.ok(service.create(model));
    }

    @DeleteMapping(value = CollageConstant.DELETE_CONTEXT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(CollageConstant.SUCCESS);
    }

    @GetMapping(value = CollageConstant.GET_CONTEXT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = CollageConstant.LIST_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody CourseModel model) {
        return ResponseEntity.ok(service.getList(model));
    }
}

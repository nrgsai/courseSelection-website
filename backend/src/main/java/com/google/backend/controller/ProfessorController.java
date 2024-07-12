package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.model.ProfessorModel;
import com.google.backend.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = CollageConstant.PROFESSOR_CONTEXT)
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping(value = CollageConstant.CREATE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody ProfessorModel model) {
        return ResponseEntity.ok(service.create(model));
    }

    @PostMapping(value = CollageConstant.UPDATE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody ProfessorModel model) {
        return ResponseEntity.ok(service.update(model));
    }

    @DeleteMapping(value = CollageConstant.DELETE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(CollageConstant.SUCCESS);
    }

    @GetMapping(value = CollageConstant.GET_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = CollageConstant.LIST_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody ProfessorModel model,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam("offset") Integer offset) {
        return ResponseEntity.ok(service.getList(model, limit, offset));
    }
}

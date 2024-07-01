package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.model.ExpertiseModel;
import com.google.backend.service.ExpertiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = CollageConstant.EXPERTISE_CONTEXT)
@RequiredArgsConstructor
public class ExpertiseController {

    private final ExpertiseService service;

    @PostMapping(value = CollageConstant.CREATE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody ExpertiseModel model) {
        return ResponseEntity.ok(service.create(model));
    }

    @DeleteMapping(value = CollageConstant.DELETE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(CollageConstant.SUCCESS);
    }

    @PostMapping(value = CollageConstant.LIST_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody ExpertiseModel model,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam("offset") Integer offset) {
        return ResponseEntity.ok(service.getList(model, limit, offset));
    }
}

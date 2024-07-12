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

    @PostMapping(value = CollageConstant.CREATE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UsersModel model) {
        return ResponseEntity.ok(service.create(model));
    }

    @DeleteMapping(value = CollageConstant.DELETE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping(value = CollageConstant.LIST_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody UsersModel model,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam("offset") Integer offset) {
        return ResponseEntity.ok(service.getListUser(model, limit, offset));
    }

    @GetMapping(value = CollageConstant.ROLE_CONTEXT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRole() {
        return ResponseEntity.ok(service.getRole());
    }
}

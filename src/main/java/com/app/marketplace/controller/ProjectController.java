package com.app.marketplace.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.marketplace.Constants;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.exception.RequestFormatException;
import com.app.marketplace.model.ErrorDTO;
import com.app.marketplace.model.ProjectDTO;
import com.app.marketplace.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "")
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        ResponseEntity<Object> responseEntity = null;
        try {
            final Project project = projectService.createProject(projectDTO);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(project);
        } catch (final RequestFormatException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.INCORRECT_INPUT, ex.getMessage()));
        }

        return responseEntity;
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllProjects() {

        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProject(@NotEmpty @PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(projectService.getProject(id));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }

        return responseEntity;
    }

    @GetMapping(value = "/{id}/bids")
    public ResponseEntity<Object> getAllBids(@NotEmpty @PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(projectService.getAllBids(id));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }

        return responseEntity;

    }

    @GetMapping(value = "/{id}/lbid")
    public ResponseEntity<Object> getLowestBid(@NotEmpty @PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(projectService.getLowestBidAmount(id));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }

        return responseEntity;

    }

}

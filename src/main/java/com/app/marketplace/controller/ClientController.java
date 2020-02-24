package com.app.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.marketplace.Constants;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.exception.RequestFormatException;
import com.app.marketplace.model.ErrorDTO;
import com.app.marketplace.model.ProjectDTO;
import com.app.marketplace.service.ClientService;
import com.app.marketplace.service.ProjectService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/addproject", method = RequestMethod.POST)
    public ResponseEntity<Object> createProject(@RequestBody ProjectDTO projectDTO) {
        ResponseEntity<Object> responseEntity = null;
        try {
            final Project project = projectService.createProject(projectDTO);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(project);
        } catch (final RequestFormatException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.INCORRECT_INPUT, ex.getMessage()));
        }

        return responseEntity;
    }

    @RequestMapping(value = "/getproject", method = RequestMethod.GET)
    public ResponseEntity<Object> getProject(@RequestParam Integer projectId) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(projectService.getProject(projectId));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }

        return responseEntity;
    }

    @RequestMapping(value = "/getlowestbid", method = RequestMethod.GET)
    public ResponseEntity<Object> getLowestBid(@RequestParam Integer projectId) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(clientService.getLowestBidAmount(projectId));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }

        return responseEntity;

    }

}

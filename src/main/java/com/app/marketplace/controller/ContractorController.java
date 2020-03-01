package com.app.marketplace.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.marketplace.Constants;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;
import com.app.marketplace.model.ErrorDTO;
import com.app.marketplace.service.ContractorService;
import com.app.marketplace.service.ProjectService;

@RestController
@RequestMapping("/contractor")
public class ContractorController {

    @Autowired
    ContractorService contractorService;

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/{contractorId}/bids")
    public ResponseEntity<Object> addBid(@RequestBody BidDTO bidDTO) {

        ResponseEntity<Object> responseEntity = null;

        if (isValidBid(bidDTO)) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(contractorService.addBid(bidDTO));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(Constants.BID_NOT_CREATED, Constants.INCOMPLETE_BID));
        }
        return responseEntity;
    }

    @GetMapping(value = "/{contractorId}/bids")
    public ResponseEntity<Object> getAllBids(@NotEmpty @PathVariable Integer contractorId, @RequestParam(required = false) Integer projectId) {

        ResponseEntity<Object> responseEntity = null;

        try {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(contractorService.getAllBids(contractorId, projectId));
        } catch (final DataNotFoundException ex) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(Constants.DATA_NOT_FOUND, ex.getMessage()));
        }
        return responseEntity;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addContractor(@RequestBody ContractorDTO contractorDTO) {

        ResponseEntity<Object> responseEntity = null;

        if (isContractorDataValid(contractorDTO)) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(contractorService.addContractor(contractorDTO));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(Constants.CONTRACTOR_NOT_CREATED, Constants.INCOMPLETE_INPUT));
        }
        return responseEntity;
    }

    private boolean isContractorDataValid(ContractorDTO contractorDTO) {
        if (contractorDTO.getAddress() == null || contractorDTO.getName() == null) {
            return false;
        }
        return true;
    }

    private boolean isValidBid(BidDTO bidDTO) {
        if (bidDTO.getBidAmount() == 0) {
            return false;
        }
        return true;
    }

}

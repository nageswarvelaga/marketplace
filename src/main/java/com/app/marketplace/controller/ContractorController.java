package com.app.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.marketplace.Constants;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;
import com.app.marketplace.model.ErrorDTO;
import com.app.marketplace.service.ContractorService;
import com.app.marketplace.service.ProjectService;

@RestController
@RequestMapping("/contract")
public class ContractorController {

    @Autowired
    ContractorService contractorService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/addbid", method = RequestMethod.POST)
    public ResponseEntity<Object> addBid(@RequestBody BidDTO bidDTO) {

        ResponseEntity<Object> responseEntity = null;

        if (isValidBid(bidDTO)) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(contractorService.addBid(bidDTO));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(Constants.BID_NOT_CREATED, Constants.INCOMPLETE_BID));
        }
        return responseEntity;
    }

    @RequestMapping(value = "/addcontractor", method = RequestMethod.POST)
    public ResponseEntity<Object> addContractor(@RequestBody ContractorDTO contractorDTO) {

        ResponseEntity<Object> responseEntity = null;

        if (isContractorDataValid(contractorDTO)) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(contractorService.addContractor(contractorDTO));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(Constants.CONTRACTOR_NOT_CREATED, Constants.INCOMPLETE_INPUT));
        }
        return responseEntity;
    }

    @RequestMapping(value = "/allprojects", method = RequestMethod.GET)
    public ResponseEntity<Object> getProjects() {

        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
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

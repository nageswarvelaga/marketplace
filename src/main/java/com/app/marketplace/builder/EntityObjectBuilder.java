package com.app.marketplace.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Contractor;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.RequestFormatException;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;
import com.app.marketplace.model.ProjectDTO;

@Component
public class EntityObjectBuilder {

    /**
     * Build Project Entity from the input object
     *
     * @param projectDTO
     * @return
     * @throws RequestFormatException
     */
    public Project buildProjectEntity(ProjectDTO projectDTO) throws RequestFormatException {
        final Project project = new Project();
        project.setDescription(projectDTO.getDescription());
        project.setBudget(projectDTO.getBudget());

        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("MM-dd-yyyy").parse(projectDTO.getLastDate());
        } catch (final ParseException e) {
            throw new RequestFormatException("Incorrect Date format");
        }
        project.setLastDate(date1);

        return project;
    }

    public Bids buildBidEntity(BidDTO bidDTO) {
        final Bids bids = new Bids();
        bids.setProjectId(bidDTO.getProjectId());
        bids.setContractorId(bidDTO.getContractorId());
        bids.setDuration(bidDTO.getDuration());
        bids.setBidAmount(bidDTO.getBidAmount());
        bids.setMinBidAmount(bidDTO.getMinBidAmount());

        return bids;
    }

    public Bids buildBidEntity(Bids bid) {
        final Bids newBid = new Bids();
        newBid.setProjectId(bid.getProjectId());
        newBid.setContractorId(bid.getContractorId());
        newBid.setDuration(bid.getDuration());
        newBid.setBidAmount(bid.getMinBidAmount());
        newBid.setMinBidAmount(bid.getMinBidAmount());

        return newBid;
    }

    public Contractor buildContractorEntity(ContractorDTO contractorDTO) {
        final Contractor contractor = new Contractor();
        contractor.setName(contractorDTO.getName());
        contractor.setAddress(contractorDTO.getAddress());

        return contractor;
    }
}

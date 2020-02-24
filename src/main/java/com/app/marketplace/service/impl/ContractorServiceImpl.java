package com.app.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.marketplace.builder.EntityObjectBuilder;
import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Contractor;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;
import com.app.marketplace.repository.BidRepository;
import com.app.marketplace.repository.ContractorRepository;
import com.app.marketplace.service.ContractorService;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    ContractorRepository contractorRepository;

    @Autowired
    EntityObjectBuilder entityObjectBuilder;

    @Override
    public Bids addBid(BidDTO bidDTO) {
        final Bids bid = bidRepository.save(entityObjectBuilder.buildBidEntity(bidDTO));

        autoBid(bid.getProjectId(), bid.getBidAmount());

        return bid;
    }

    @Override
    public Contractor addContractor(ContractorDTO contractorDTO) {
        return contractorRepository.save(entityObjectBuilder.buildContractorEntity(contractorDTO));
    }

    private void autoBid(Integer projectId, Integer bidAmount) {
        final List<Bids> bidsList = bidRepository.findByProjectIdAndMinBidAmount(projectId, bidAmount);

        for (final Bids bid : bidsList) {
            bidRepository.save(entityObjectBuilder.buildBidEntity(bid));
        }
    }

}

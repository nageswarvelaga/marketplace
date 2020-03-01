package com.app.marketplace.service;

import java.util.List;

import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Contractor;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;

public interface ContractorService {

    public Bids addBid(BidDTO bidDTO);

    public Contractor addContractor(ContractorDTO contractorDTO);

    public List<Bids> getAllBids(Integer contractorId, Integer projectId) throws DataNotFoundException;

}

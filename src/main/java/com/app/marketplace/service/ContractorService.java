package com.app.marketplace.service;

import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Contractor;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;

public interface ContractorService {

    public Bids addBid(BidDTO bidDTO);

    public Contractor addContractor(ContractorDTO contractorDTO);

}

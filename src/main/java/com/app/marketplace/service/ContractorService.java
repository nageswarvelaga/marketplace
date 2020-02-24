package com.app.marketplace.service;

import com.app.marketplace.dao.model.Bids;
import com.app.marketplace.dao.model.Contractor;
import com.app.marketplace.model.BidDTO;
import com.app.marketplace.model.ContractorDTO;

public interface ContractorService {

    public Bids addBid(BidDTO bidDTO);

    public Contractor addContractor(ContractorDTO contractorDTO);

}

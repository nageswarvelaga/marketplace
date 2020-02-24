package com.app.marketplace.service;

import org.springframework.stereotype.Service;

import com.app.marketplace.dao.model.Bids;
import com.app.marketplace.exception.DataNotFoundException;

@Service
public interface ClientService {

    public Bids getLowestBidAmount(Integer projectId) throws DataNotFoundException;
}

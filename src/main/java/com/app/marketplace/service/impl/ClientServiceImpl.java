package com.app.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.marketplace.Constants;
import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.repository.BidRepository;
import com.app.marketplace.repository.ProjectRepository;
import com.app.marketplace.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ProjectRepository ProjectRepository;

    @Autowired
    BidRepository bidRepository;

    @Override
    public Bids getLowestBidAmount(Integer projectId) throws DataNotFoundException {

        final Project project = ProjectRepository.findById(projectId).orElseThrow(() -> new DataNotFoundException(Constants.PROJECT_NOT_FOUND));

        final List<Bids> bidsList = bidRepository.findByProjectIdAndCreationDate(projectId, project.getLastDate());
        if (bidsList.isEmpty()) {
            throw new DataNotFoundException(Constants.NO_BIDS_FOUND + projectId);
        }
        Bids lowestBid = bidsList.get(0);
        for (final Bids bid : bidsList) {
            if (lowestBid.getBidAmount() > bid.getBidAmount()) {
                lowestBid = bid;
            }
        }
        return lowestBid;
    }

}

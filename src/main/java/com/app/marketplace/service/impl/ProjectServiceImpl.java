package com.app.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.marketplace.Constants;
import com.app.marketplace.builder.EntityObjectBuilder;
import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.exception.RequestFormatException;
import com.app.marketplace.model.ProjectDTO;
import com.app.marketplace.repository.BidRepository;
import com.app.marketplace.repository.ProjectRepository;
import com.app.marketplace.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    EntityObjectBuilder entityObjectBuilder;

    @Override
    public Project createProject(ProjectDTO projectDTO) throws RequestFormatException {

        final Project project = projectRepository.save(entityObjectBuilder.buildProjectEntity(projectDTO));
        return project;
    }

    @Override
    public Project getProject(Integer projectId) throws DataNotFoundException {
        return projectRepository.findById(projectId).orElseThrow(() -> new DataNotFoundException(Constants.PROJECT_NOT_FOUND));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Bids getLowestBidAmount(Integer projectId) throws DataNotFoundException {

        final Project project = projectRepository.findById(projectId).orElseThrow(() -> new DataNotFoundException(Constants.PROJECT_NOT_FOUND));

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

    @Override
    public List<Bids> getAllBids(Integer projectId) throws DataNotFoundException {
        final List<Bids> bidsList = bidRepository.findByProjectId(projectId);
        if (bidsList.isEmpty()) {
            throw new DataNotFoundException(Constants.NO_BIDS_FOUND + projectId);
        }
        return bidsList;
    }

}

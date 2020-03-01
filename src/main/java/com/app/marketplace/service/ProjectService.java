package com.app.marketplace.service;

import java.util.List;

import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Project;
import com.app.marketplace.exception.DataNotFoundException;
import com.app.marketplace.exception.RequestFormatException;
import com.app.marketplace.model.ProjectDTO;

public interface ProjectService {

    public Project createProject(ProjectDTO project) throws RequestFormatException;

    public Project getProject(Integer projectId) throws DataNotFoundException;

    public List<Project> getAllProjects();

    public Bids getLowestBidAmount(Integer projectId) throws DataNotFoundException;

    public List<Bids> getAllBids(Integer projectId) throws DataNotFoundException;

}

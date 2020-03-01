package com.app.marketplace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.marketplace.dao.Bids;
import com.app.marketplace.dao.Project;
import com.app.marketplace.repository.BidRepository;
import com.app.marketplace.repository.ProjectRepository;
import com.app.marketplace.service.ProjectService;

public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private BidRepository bidRepository;

    @BeforeEach
    public void setup() {
        final Project project = new Project();
        project.setId(1);
        project.setDescription("Test");
        project.setLastDate(new Date());

        Mockito.when(projectRepository.findById(1)).thenReturn(Optional.of(project));

        final Bids bid = new Bids();
        bid.setProjectId(project.getId());
        bid.setContractorId(1);
        bid.setBidAmount(100);
        bid.setCreationDate(new Date());

        final List<Bids> bids = new ArrayList<Bids>();
        bids.add(bid);

        Mockito.when(bidRepository.findByProjectIdAndCreationDate(1, project.getLastDate())).thenReturn(bids);
    }

    public void getLowestBidAmountTest() {

        final Bids bid = projectService.getLowestBidAmount(1);

        assertEquals(100, bid.getBidAmount());
    }
}

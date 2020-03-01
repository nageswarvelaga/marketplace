package com.app.marketplace.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.app.marketplace.dao.Project;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entiryManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findByProjectIdTest() {
        final Project project = new Project();
        project.setDescription("Test");

        entiryManager.persist(project);
        entiryManager.flush();

        assertTrue(projectRepository.findById(1).isPresent());

    }

}

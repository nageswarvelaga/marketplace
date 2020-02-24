package com.app.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.marketplace.dao.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}

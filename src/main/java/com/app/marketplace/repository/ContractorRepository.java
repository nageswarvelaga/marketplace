package com.app.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.marketplace.dao.model.Contractor;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {

}

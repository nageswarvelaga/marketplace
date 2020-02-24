package com.app.marketplace.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String description;

    Integer budget;

    @DateTimeFormat
    Date lastDate;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getBudget() {
        return budget;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

}

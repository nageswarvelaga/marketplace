package com.app.marketplace.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class ProjectDTO {

    Integer id;

    @NotNull
    String description;

    @NotNull
    Integer budget;

    @NotNull
    String lastDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDay(String lastDate) {
        this.lastDate = lastDate;
    }

}

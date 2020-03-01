package com.app.marketplace.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Persistent;

@Persistent
@Entity
public class Bids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bidId;

    @NotNull
    Integer projectId;

    @NotNull
    Integer contractorId;

    @NotNull
    Integer bidAmount;

    Integer duration;

    Integer minBidAmount;

    @CreationTimestamp
    Date creationDate;

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getContractorId() {
        return contractorId;
    }

    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMinBidAmount() {
        return minBidAmount;
    }

    public void setMinBidAmount(Integer minBidAmount) {
        this.minBidAmount = minBidAmount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}

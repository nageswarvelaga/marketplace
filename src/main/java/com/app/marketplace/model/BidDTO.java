package com.app.marketplace.model;

import org.springframework.stereotype.Component;

@Component
public class BidDTO {
    Integer projectId;

    Integer contractorId;

    Integer bidAmount;

    Integer duration;

    Integer minBidAmount;

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

}

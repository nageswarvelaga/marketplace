package com.app.marketplace.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.marketplace.dao.Bids;

@Repository
public interface BidRepository extends JpaRepository<Bids, Integer> {

    public static final String AUTO_BIDDING_QUERY = "from Bids b where b."
            + "projectId=?1 AND b.bidAmount >?2 AND b.minBidAmount !=0 AND b.minBidAmount <?2 group by b.contractorId";

    public static final String GET_BIDS_QUERY = "from Bids b where b.projectId=?1 AND creationDate <=?2";

    List<Bids> findByProjectId(Integer projectId);

    @Query(AUTO_BIDDING_QUERY)
    List<Bids> findByProjectIdAndMinBidAmount(Integer projectId, Integer bidAmount);

    @Query(GET_BIDS_QUERY)
    List<Bids> findByProjectIdAndCreationDate(Integer projectId, Date lastDate);

}

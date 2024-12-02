package org.example.tripting.mission;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends MongoRepository<Mission, String> {
    @Aggregation(pipeline = {
            "{ $match:  { type: ?0, area: ?1 } }",
            "{ $sample: { size: 1 } }"
    })
    List<Mission> findRandomMissionByTypeAndArea(String type, String area);
}

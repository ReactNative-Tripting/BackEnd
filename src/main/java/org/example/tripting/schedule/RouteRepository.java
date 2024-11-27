package org.example.tripting.schedule;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
    Route findByRouteName(String routeName);
}

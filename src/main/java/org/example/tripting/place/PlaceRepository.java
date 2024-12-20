package org.example.tripting.place;

import org.example.tripting.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

    Place findByPlaceName(String placeName);
}

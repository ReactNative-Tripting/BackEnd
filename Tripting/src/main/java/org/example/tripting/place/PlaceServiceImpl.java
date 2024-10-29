package org.example.tripting.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    @Override
    public  Place add(Place place){
        return placeRepository.save(place);
    }

}

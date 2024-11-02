package org.example.tripting.place;

import org.example.tripting.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService){this.placeService = placeService;}

    @PostMapping("/add")
    public Place add(@RequestBody Place place){ return placeService.add(place); }

    @GetMapping("/placename/{placeName}")
    public String getPlaceByPlaceName(@PathVariable String placeName) { return placeService.getPlaceByPlaceName(placeName); }

}

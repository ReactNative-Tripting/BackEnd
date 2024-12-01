package org.example.tripting.route;

import org.example.tripting.Stroage.StorageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private final RouteRepository scheduleRepository;
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteRepository scheduleRepository, RouteService routeService){
        this.scheduleRepository = scheduleRepository;
        this.routeService = routeService;
    }

    //일정 이름으로 일정 조회
    @GetMapping("/type")
    public ArrayList<RouteDTO> getRouteByRouteName(@RequestParam String type) {
        return routeService.getRouteByRouteName(type);
    }
}

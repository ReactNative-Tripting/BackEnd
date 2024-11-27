package org.example.tripting.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{routeName}")
    public ArrayList getRouteByRouteName(@PathVariable String routeName) {
        return routeService.getRouteByRouteName(routeName);
    }
}

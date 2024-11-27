package org.example.tripting.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    // 일정 이름으로 일정 리스트 가져오는 메서드
    @Override
    public ArrayList getRouteByRouteName(String routeName) {
        Route route = routeRepository.findByRouteName(routeName);
        return route != null ? route.getRoute() : null;
    }
}

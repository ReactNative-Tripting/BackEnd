package org.example.tripting.route;

import java.util.ArrayList;

public interface RouteService {
    public ArrayList<RouteDTO> getRouteByRouteName(String type);
}

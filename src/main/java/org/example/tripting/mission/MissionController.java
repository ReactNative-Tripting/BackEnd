package org.example.tripting.mission;

import org.example.tripting.route.RouteDTO;
import org.example.tripting.route.RouteRepository;
import org.example.tripting.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {
    private final MissionRepository scheduleRepository;
    private final MissionService missionService;

    @Autowired
    public MissionController(MissionRepository scheduleRepository, MissionService missionService){
        this.scheduleRepository = scheduleRepository;
        this.missionService = missionService;
    }

    @GetMapping("/miss")
    public List<Mission> getMissionByMission(@RequestParam String type, @RequestParam String area) {
        return missionService.getMissionByMission(type, area);
    }
}

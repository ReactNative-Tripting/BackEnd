package org.example.tripting.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {
    private MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) { this.missionRepository = missionRepository; }

    @Override
    public List<Mission> getMissionByMission(String type, String area){
        List<Mission> mission = missionRepository.findRandomMissionByTypeAndArea(type, area);
        if(mission != null){
            System.out.println("Mission found : " + mission.toString());
        } else {
            System.out.println("NO misson found with type");
        }
        return mission;
    }
}

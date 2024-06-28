package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.services.OrientationMovementService;

import java.util.Map;

public class OrientationMovementServiceImpl implements OrientationMovementService {


    private static final Map<String, String> directionalMap = initializeDirectionalMap();

    public static Map<String, String> initializeDirectionalMap() {

        return Map.of("NR", "E",
                "NL", "W",
                "SR", "W",
                "SL", "E",
                "ER", "S",
                "EL", "N",
                "WR", "N",
                "WL", "S");
    }

    @Override
    public String getRobotNewOrientation(String currentOrientation, String expectedOrientationMovement) {
        String key = currentOrientation+expectedOrientationMovement;
        if (!directionalMap.containsKey(key)){
           throw new IllegalArgumentException("Provided illegal orientation data");
        }
        return directionalMap.get(key);
    }
}

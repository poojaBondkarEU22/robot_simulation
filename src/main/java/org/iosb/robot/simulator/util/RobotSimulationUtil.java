package org.iosb.robot.simulator.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iosb.robot.simulator.RobotSimulation;

public class RobotSimulationUtil {

    private static final Logger log  = LogManager.getLogger(RobotSimulation.class);

    public static int getIntValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            log.error("Invalid Input. Either Table size or initial position contains non-numeric value.");
        }
        return -1;
    }
}

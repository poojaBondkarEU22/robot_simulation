package org.iosb.robot.simulator.services;

import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.model.RobotPosition;

public interface GridMovementService {

    RobotPosition moveRobotToNewPosition(RobotPosition robotPosition, ParsedInputData parsedInputData);
}

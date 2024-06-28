package org.iosb.robot.simulator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iosb.robot.simulator.constants.ApplicationConstants;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.model.RobotPosition;
import org.iosb.robot.simulator.services.GridMovementService;
import org.iosb.robot.simulator.services.OrientationMovementService;
import org.iosb.robot.simulator.services.RobotSimulationService;

import java.util.ArrayList;
import java.util.Arrays;

public class RobotSimulationServiceImpl implements RobotSimulationService {

    private static final Logger log  = LogManager.getLogger(RobotSimulationServiceImpl.class);

    private final OrientationMovementService orientationMovementService;

    private final GridMovementService gridMovementService;

    private RobotPosition robotPosition;

    public RobotSimulationServiceImpl() {
        orientationMovementService =  new OrientationMovementServiceImpl();
        gridMovementService = new GridMovementServiceImpl();
    }

    @Override
    public String simulateRobotToNewPosition(ParsedInputData parsedInputData) {

        log.info("Simulating robot for commands :: {} ", parsedInputData.getMovementCommands());

        ArrayList<String> movements = new ArrayList<>(Arrays.asList(parsedInputData.getMovementCommands().split("")));

        initializeRobotPosition(parsedInputData);

        for (String move : movements) {
            if (isRotationCommand(move)) {
                rotateRobot(move);
            } else if (isMovementCommand(move)) {
                moveRobot(parsedInputData);
            }
        }
        return convertObjectToString(robotPosition);
    }

    private void rotateRobot(String move) {
        String newOrientation = orientationMovementService.getRobotNewOrientation(robotPosition.getOrientation(), move);
        robotPosition.setOrientation(newOrientation);
    }

    private void moveRobot(ParsedInputData parsedInputData) {
        robotPosition = gridMovementService.moveRobotToNewPosition(robotPosition, parsedInputData);
    }

    private boolean isRotationCommand(String move) {
        return move.equalsIgnoreCase(ApplicationConstants.ROBOT_LEFT_DIRECTION_MOVEMENT) ||
                move.equalsIgnoreCase(ApplicationConstants.ROBOT_RIGHT_DIRECTION_MOVEMENT);
    }

    private boolean isMovementCommand(String move) {
        return move.equalsIgnoreCase(ApplicationConstants.ROBOT_M_MOVEMENT);
    }


    private void initializeRobotPosition(ParsedInputData parsedInputData) {
        robotPosition = new RobotPosition(parsedInputData.getRobotInitialRowPosition(),
                parsedInputData.getRobotInitialColumnPosition(),
                parsedInputData.getRobotInitialOrientation());
    }

    private String convertObjectToString(RobotPosition robotPosition) {
        return String.format("%d %d %s", robotPosition.getRow(), robotPosition.getColumn(), robotPosition.getOrientation());
    }
}

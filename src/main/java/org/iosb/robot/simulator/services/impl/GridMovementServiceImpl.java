package org.iosb.robot.simulator.services.impl;


import org.iosb.robot.simulator.enums.Orientation;
import org.iosb.robot.simulator.exceptions.IllegalRobotMoveException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.model.RobotPosition;
import org.iosb.robot.simulator.services.GridMovementService;
import org.iosb.robot.simulator.services.RobotSimulationDataValidator;


public class GridMovementServiceImpl implements GridMovementService {

    private final RobotSimulationDataValidator validationService;

    public GridMovementServiceImpl() {
        validationService = new RobotSimulationDataValidatorImpl();
    }

    @Override
    public RobotPosition moveRobotToNewPosition(RobotPosition robotPosition, ParsedInputData parsedInputData) {
        switch (Orientation.valueOf(robotPosition.getOrientation())) {
            case N:
                updateRowPosition(robotPosition, robotPosition.getRow() - 1, parsedInputData);
                break;
            case S:
                updateRowPosition(robotPosition, robotPosition.getRow() + 1, parsedInputData);
                break;
            case W:
                updateColumnPosition(robotPosition, robotPosition.getColumn() - 1, parsedInputData);
                break;
            case E:
                updateColumnPosition(robotPosition, robotPosition.getColumn() + 1, parsedInputData);
                break;
        }
        return robotPosition;
    }

    private void updateRowPosition(RobotPosition robotPosition, int newPosition, ParsedInputData parsedInputData) {
        if (!validationService.isValidRowMovement(newPosition, parsedInputData.getTableRows())) {
            throw new IllegalRobotMoveException("Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns());
        }
        robotPosition.setRow(newPosition);
    }

    private void updateColumnPosition(RobotPosition robotPosition, int newPosition, ParsedInputData parsedInputData) {
        if (!validationService.isValidColumnMovement(newPosition, parsedInputData.getTableColumns())) {
            throw new IllegalRobotMoveException("Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns());
        }
        robotPosition.setColumn(newPosition);
    }
}

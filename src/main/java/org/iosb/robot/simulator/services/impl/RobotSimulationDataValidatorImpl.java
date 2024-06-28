package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.constants.ApplicationConstants;
import org.iosb.robot.simulator.enums.Orientation;
import org.iosb.robot.simulator.exceptions.InvalidRobotSimulationInputException;
import org.iosb.robot.simulator.services.RobotSimulationDataValidator;
import org.iosb.robot.simulator.util.RobotSimulationUtil;


public class RobotSimulationDataValidatorImpl implements RobotSimulationDataValidator {

    @Override
    public boolean isValidRowMovement(int inputRowPosition, int tableRows) {
        return inputRowPosition >= 0 && inputRowPosition < tableRows;
    }

    @Override
    public boolean isValidColumnMovement(int inputColumnPosition, int tableColumns) {
        return inputColumnPosition >= 0 && inputColumnPosition < tableColumns;
    }

    @Override
    public boolean isValidMovementCommands(String movementCommands) {
        return movementCommands.toUpperCase().matches("[" + ApplicationConstants.VALID_MOVEMENT_COMMANDS + "]*");
    }

    @Override
    public void validateInitialPositionAndOrientation(String[] robotInitialPositionData, int tableRows, int tableColumns) {

        if(!isValidRowMovement(RobotSimulationUtil.getIntValue(robotInitialPositionData[0]), tableRows)){
            throw new InvalidRobotSimulationInputException("Provided invalid initial row position.");
        }

        if(!isValidColumnMovement(RobotSimulationUtil.getIntValue(robotInitialPositionData[1]),tableColumns)) {
            throw new InvalidRobotSimulationInputException("Provided invalid initial column position.");
        }

        if (!Orientation.contains(robotInitialPositionData[2])) {
            throw new InvalidRobotSimulationInputException("Provided invalid initial orientation.");
        }
    }

    @Override
    public void validateTableSizeData(String[] tableSize){
        if (tableSize == null || tableSize.length !=2) {
            throw new InvalidRobotSimulationInputException("Provided invalid table size.");
        }

        if ( RobotSimulationUtil.getIntValue(tableSize[0]) <= 0 || RobotSimulationUtil.getIntValue(tableSize[1]) <= 0) {
            throw new InvalidRobotSimulationInputException("Value for row or column should be greater than 0 and numeric.");
        }
    }
}

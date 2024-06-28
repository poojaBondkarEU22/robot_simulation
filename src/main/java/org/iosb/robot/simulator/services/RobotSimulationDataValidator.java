package org.iosb.robot.simulator.services;


public interface RobotSimulationDataValidator {

    boolean isValidRowMovement(int inputRowPosition, int tableRows);

    boolean isValidColumnMovement(int inputColumnPosition, int tableColumns);

    boolean isValidMovementCommands(String movementCommands);

    void validateInitialPositionAndOrientation(String[] robotInitialPositionData, int tableRows, int tableColumns);

    void validateTableSizeData(String[] tableSize);
}

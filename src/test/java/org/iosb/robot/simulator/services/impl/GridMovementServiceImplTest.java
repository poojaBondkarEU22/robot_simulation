package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.exceptions.IllegalRobotMoveException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.model.RobotPosition;
import org.iosb.robot.simulator.services.RobotSimulationDataValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GridMovementServiceImplTest {

    private GridMovementServiceImpl gridMovementService;
    private RobotSimulationDataValidator validationService;

    private RobotPosition robotPosition;
    private final ParsedInputData parsedInputData = new ParsedInputData();

    @Mock
    private RobotSimulationDataValidatorImpl validator;



    @BeforeEach
    public void setup() {
        validationService = mock(RobotSimulationDataValidatorImpl.class);
        gridMovementService = new GridMovementServiceImpl();
    }

    void dataSetup(int tableRows, int tableColumns, int robotInitialRowPosition, int robotInitialColumnPosition, String initialOrientation) {
        robotPosition = new RobotPosition(robotInitialRowPosition,robotInitialColumnPosition,initialOrientation);
        parsedInputData.setTableRows(tableRows);
        parsedInputData.setTableColumns(tableColumns);
    }

    @Test
    void testRobotNewPosition_ValidNorthMovement() {

        //arrange
        dataSetup(5,5,2,2,"N");
        Mockito.when(validationService.isValidRowMovement(1,5)).thenReturn(true);

        RobotPosition newRobotPosition = gridMovementService.moveRobotToNewPosition(robotPosition,parsedInputData);

        assertEquals(1, newRobotPosition.getRow());
        assertEquals(2, newRobotPosition.getColumn());
        assertEquals("N", newRobotPosition.getOrientation());
    }

    @Test
    void testRobotNewPosition_ValidSouthMovement() {

        //arrange
        dataSetup(5,5,2,2,"S");
        Mockito.when(validationService.isValidRowMovement(3,5)).thenReturn(true);

        RobotPosition newRobotPosition = gridMovementService.moveRobotToNewPosition(robotPosition,parsedInputData);

        assertEquals(3, newRobotPosition.getRow());
        assertEquals(2, newRobotPosition.getColumn());
        assertEquals("S", newRobotPosition.getOrientation());
    }

    @Test
    void testRobotNewPosition_ValidEastMovement() {

        //arrange
        dataSetup(5,5,2,2,"E");
        Mockito.when(validationService.isValidColumnMovement(3,5)).thenReturn(true);

        RobotPosition newRobotPosition = gridMovementService.moveRobotToNewPosition(robotPosition,parsedInputData);

        assertEquals(2, newRobotPosition.getRow());
        assertEquals(3, newRobotPosition.getColumn());
        assertEquals("E", newRobotPosition.getOrientation());
    }

    @Test
    void testRobotNewPosition_ValidWestMovement() {

        //arrange
        dataSetup(5,5,2,2,"W");
        Mockito.when(validationService.isValidColumnMovement(1,5)).thenReturn(true);

        RobotPosition newRobotPosition = gridMovementService.moveRobotToNewPosition(robotPosition,parsedInputData);

        assertEquals(2, newRobotPosition.getRow());
        assertEquals(1, newRobotPosition.getColumn());
        assertEquals("W", newRobotPosition.getOrientation());
    }

    @Test
    void testRobotNewPosition_InvalidNorthMovement() {

        //arrange
        dataSetup(5,5,0,2,"N");
        Mockito.when(validationService.isValidRowMovement(-1,5)).thenReturn(false);

        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () -> {
            gridMovementService.moveRobotToNewPosition(robotPosition, parsedInputData);
        });

        assertEquals("Illegal Move. Robot is moving out of table size 5 X 5", exception.getMessage());
    }

    @Test
    void testRobotNewPosition_InvalidSouthMovement() {

        //arrange
        dataSetup(5,5,4,2,"S");
        Mockito.when(validationService.isValidRowMovement(5,5)).thenReturn(false);

        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () -> {
            gridMovementService.moveRobotToNewPosition(robotPosition, parsedInputData);
        });

        assertEquals("Illegal Move. Robot is moving out of table size 5 X 5", exception.getMessage());
    }

    @Test
    void testRobotNewPosition_InvalidEastMovement() {

        //arrange
        dataSetup(5,5,3,4,"E");
        Mockito.when(validationService.isValidColumnMovement(5,5)).thenReturn(false);

        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () -> {
            gridMovementService.moveRobotToNewPosition(robotPosition, parsedInputData);
        });

        assertEquals("Illegal Move. Robot is moving out of table size 5 X 5", exception.getMessage());
    }

    @Test
    void testRobotNewPosition_InvalidWestMovement() {

        //arrange
        dataSetup(5,5,2,0,"W");
        Mockito.when(validationService.isValidColumnMovement(5,5)).thenReturn(false);

        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () -> {
            gridMovementService.moveRobotToNewPosition(robotPosition, parsedInputData);
        });

        assertEquals("Illegal Move. Robot is moving out of table size 5 X 5", exception.getMessage());
    }

    @AfterEach
    void cleanUp() {
        if (gridMovementService !=null) {
            gridMovementService = null;
        }
    }

}
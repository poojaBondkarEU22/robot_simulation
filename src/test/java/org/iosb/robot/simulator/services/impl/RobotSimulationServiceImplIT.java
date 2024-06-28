package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.exceptions.IllegalRobotMoveException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.services.InputParserService;
import org.iosb.robot.simulator.services.RobotSimulationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotSimulationServiceImplIT {

    private RobotSimulationService robotSimulationService;
    private InputParserService inputParserService;



    @BeforeEach
    void setUp(){
        robotSimulationService = new RobotSimulationServiceImpl();
        inputParserService = new InputParserServiceImpl();
    }

    @Test
    void testRobotSimulation_whenRobotIsAt00NWithCommandsM_thenReturnException() {
        String input = "5 5: 0 0 N:M";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () ->
                robotSimulationService.simulateRobotToNewPosition(parsedInputData));
        String expectedExceptionMessage = "Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns();
        assertEquals(expectedExceptionMessage,exception.getMessage());
    }

    @Test
    void testRobotSimulation_whenRobotIsAt40SWithCommandsM_thenReturnException() {
        String input = "5 5: 4 0 S:M";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () ->
                robotSimulationService.simulateRobotToNewPosition(parsedInputData));
        String expectedExceptionMessage = "Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns();
        assertEquals(expectedExceptionMessage,exception.getMessage());
    }

    @Test
    void testRobotSimulation_whenRobotIsAt44ESWithCommandsM_thenReturnException() {
        String input = "5 5: 4 4 E:M";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () ->
                robotSimulationService.simulateRobotToNewPosition(parsedInputData));
        String expectedExceptionMessage = "Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns();
        assertEquals(expectedExceptionMessage,exception.getMessage());
    }

    @Test
    void testRobotSimulation_whenRobotIsAt12SWithCommandsMRLM_thenRobotNewPosiiton() {
        String input = "5 5: 1 2 S:MRMLM";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        String actualRobotPosition  = robotSimulationService.simulateRobotToNewPosition(parsedInputData);
        assertEquals("3 1 S",actualRobotPosition);
    }

    @Test
    void testRobotSimulation_whenRobotIsAt12SWithCommandsLMLMMRMMLRM_thenRobotNewPosiiton() {
        String input = "5 4: 1 2 N:LMLMMRMMLRM";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        IllegalRobotMoveException exception = assertThrows(IllegalRobotMoveException.class, () ->
                robotSimulationService.simulateRobotToNewPosition(parsedInputData));
        String expectedExceptionMessage = "Illegal Move. Robot is moving out of table size " + parsedInputData.getTableRows() + " X " + parsedInputData.getTableColumns();
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
    @AfterEach
    void cleanUp(){
        if ( robotSimulationService != null){
            robotSimulationService = null;
        }
    }

}
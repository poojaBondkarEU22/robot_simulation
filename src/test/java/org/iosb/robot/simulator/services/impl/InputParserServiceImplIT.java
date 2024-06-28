package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.exceptions.InvalidRobotSimulationInputException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.services.InputParserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserServiceImplIT {

    private InputParserService inputParserService;

    @BeforeEach
    void setup() {
        inputParserService = new InputParserServiceImpl();
    }

    @Test
    void testInputParserService_whenTableRowOrColumnValueIsLessThanOrEqualToZero_thenReturnException() {
        String input = "0 -1: 1 2 S:MRM";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));

        String expectedExceptionMessage = "Value for row or column should be greater than 0 and numeric.";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenEmptyStringPass_thenReturnNull() {
        String input = "";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        assertNull(parsedInputData);
    }

    @Test
    void testInputParserService_whenTableRowOrColumnValueIsNonNumeric_thenReturnException() {
        String input = "A 5: 1 2 S:MRM";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));
        String expectedExceptionMessage = "Value for row or column should be greater than 0 and numeric.";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenInitialPositionRowOrColumnIsLessThanZero_thenReturnException() {
        String input = "5 5: -1 3 S:MRM";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));
        String expectedExceptionMessage = "Provided invalid initial row position.";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenInitialPositionRowOrColumnHasValueGreaterThanTableSize_thenReturnException() {
        String input = "5 5: 2 6 S:MRM";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));
        String expectedExceptionMessage = "Provided invalid initial column position.";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenOrientationIsDifferentThanNSEW_thenReturnException() {
        String input = "5 5: 2 2 H:MRM";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));
        String expectedExceptionMessage = "Provided invalid initial orientation.";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenMovementCommandsContainsOtherThanMLR_thenReturnException() {
        String input = "5 5: 2 2 S:MRMO";
        InvalidRobotSimulationInputException exception = assertThrows(InvalidRobotSimulationInputException.class, () ->
                inputParserService.parseSimulationInputData(input));
        System.out.println(exception.getMessage());
        String expectedExceptionMessage = "Movement commands contains invalid characters. Valid characters are  M L R .";
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testInputParserService_whenCorrectSimulationDataGiven_thenReturnNonNullObject() {
        String input = "5 5: 2 2 S:MRM";
        ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);
        assertNotNull(parsedInputData);
        assertEquals(parsedInputData.getTableRows(),5);
        assertEquals(parsedInputData.getTableColumns(),5);
        assertEquals(parsedInputData.getRobotInitialRowPosition(),2);
        assertEquals(parsedInputData.getRobotInitialColumnPosition(),2);
        assertEquals(parsedInputData.getRobotInitialOrientation(),"S");
        assertEquals(parsedInputData.getMovementCommands(),"MRM");
    }

    @AfterEach
    void cleanUp() {
        if(inputParserService !=  null) {
            inputParserService = null;
        }
    }
}
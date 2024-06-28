package org.iosb.robot.simulator.services.impl;



import org.iosb.robot.simulator.exceptions.InvalidRobotSimulationInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class RobotSimulationDataValidatorImplTest {

    private RobotSimulationDataValidatorImpl validator;

    @BeforeEach
    public void setup() {
        validator = new RobotSimulationDataValidatorImpl();
    }

    @Test
    public void test_IsValidRowMovement() {
        assertTrue(validator.isValidRowMovement(0, 5));
        assertTrue(validator.isValidRowMovement(4, 5));
        assertFalse(validator.isValidRowMovement(-1, 5));
        assertFalse(validator.isValidRowMovement(5, 5));
    }

    @Test
    public void test_IsValidColumnMovement() {
        assertTrue(validator.isValidColumnMovement(0, 5));
        assertTrue(validator.isValidColumnMovement(4, 5));
        assertFalse(validator.isValidColumnMovement(-1, 5));
        assertFalse(validator.isValidColumnMovement(5, 5));
    }

    @Test
    void test_IsValidMovementCommands() {
        String validCommands = "RMLLLR";
        String invalidCommands = "RMLLLRS";

        assertTrue(validator.isValidMovementCommands(validCommands));
        assertFalse(validator.isValidMovementCommands(invalidCommands));
    }

    @Test
    void test_validateInitialPositionAndOrientation_validData() {
        String[] robotInitialPositionData = {"1", "2", "N"};
        int tableRows = 5;
        int tableColumns = 5;

        assertDoesNotThrow(() -> validator.validateInitialPositionAndOrientation(robotInitialPositionData,tableRows,tableColumns));
    }

    @Test
    void test_validateInitialPositionAndOrientation_invalidData() {
        String[] robotInitialPositionData = {"6", "2", "N"};
        int tableRows = 5;
        int tableColumns = 5;

        Exception exception = assertThrows(InvalidRobotSimulationInputException.class,
                () -> validator.validateInitialPositionAndOrientation(robotInitialPositionData, tableRows, tableColumns));

        assertEquals("Provided invalid initial row position.", exception.getMessage());
    }

    @Test
    void test_validateTableSize_validData() {
        String[] tableSize = {"5", "5"};
        assertDoesNotThrow(() -> validator.validateTableSizeData(tableSize));
    }

    @Test
    void test_validateTableSize_invalidData() {
        String[] tableSize = {"5", "-1"};


        Exception exception = assertThrows(InvalidRobotSimulationInputException.class,
                () -> validator.validateTableSizeData(tableSize));

        assertEquals("Value for row or column should be greater than 0 and numeric.", exception.getMessage());
    }

    @Test
    void test_validateTableSize_invalidDataNonNumericValue() {
        String[] tableSize = {"A", "4"};


        Exception exception = assertThrows(InvalidRobotSimulationInputException.class,
                () -> validator.validateTableSizeData(tableSize));

        assertEquals("Value for row or column should be greater than 0 and numeric.", exception.getMessage());
    }

    @Test
    void test_validateTableSize_invalidDataMoreValues() {
        String[] tableSize = {"4", "4", "3"};


        Exception exception = assertThrows(InvalidRobotSimulationInputException.class,
                () -> validator.validateTableSizeData(tableSize));

        assertEquals("Provided invalid table size.", exception.getMessage());
    }

}
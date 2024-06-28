package org.iosb.robot.simulator.services.impl;

import org.iosb.robot.simulator.constants.ApplicationConstants;
import org.iosb.robot.simulator.exceptions.InvalidRobotSimulationInputException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.services.InputParserService;
import org.iosb.robot.simulator.services.RobotSimulationDataValidator;
import org.iosb.robot.simulator.util.RobotSimulationUtil;


public class InputParserServiceImpl implements InputParserService {

    private final RobotSimulationDataValidator validationService;

    public InputParserServiceImpl() {
        validationService = new RobotSimulationDataValidatorImpl();
    }

    @Override
    public ParsedInputData parseSimulationInputData(String value) {

        if(value == null || value.isEmpty()) {
            return null;
        }

        ParsedInputData parsedInputData = new ParsedInputData();
        String[] simulationData = value.split(":");
        if (simulationData.length != 3){
            throw new InvalidRobotSimulationInputException("Invalid input data");
        }
        processAndAssignTableSizeData(simulationData[0],parsedInputData);
        processInitialPositionAndOrientationData(simulationData[1],parsedInputData);
        processMovementCommandsData(simulationData[2],parsedInputData);
        return parsedInputData;
    }


    private void processMovementCommandsData(String movementCommands, ParsedInputData parsedInputData) {
        movementCommands = movementCommands.trim().replaceAll(" ", "");

        if (!validationService.isValidMovementCommands(movementCommands)) {
            throw new InvalidRobotSimulationInputException("Movement commands contains invalid characters. Valid characters are "
                    + ApplicationConstants.VALID_MOVEMENT_COMMANDS.replace(""," ")+".");
        }
        parsedInputData.setMovementCommands(movementCommands.toUpperCase());
    }

    private void processAndAssignTableSizeData(String table, ParsedInputData parsedInputData) {
        String tableSize[] = table != null ? table.trim().replaceAll("\\s+", " ").split(" ") : null ;
        validationService.validateTableSizeData(tableSize);
        try{
            assert tableSize != null;
            parsedInputData.setTableRows(RobotSimulationUtil.getIntValue(tableSize[0]));
            parsedInputData.setTableColumns(RobotSimulationUtil.getIntValue(tableSize[1]));
        } catch (Exception exception) {
           throw new InvalidRobotSimulationInputException("Exception occur while parsing table size data.");
        }
    }


    private void processInitialPositionAndOrientationData(String initialPositionData, ParsedInputData parsedInputData) {
        String initialPosition[] = initialPositionData != null ? initialPositionData.trim().replaceAll("\\s+", " ").split(" ") : null ;
        if (initialPosition == null || initialPosition.length !=3) {
            throw new InvalidRobotSimulationInputException("Invalid initial position and orientation data.");
        }

        validationService.validateInitialPositionAndOrientation(initialPosition,parsedInputData.getTableRows(),parsedInputData.getTableColumns());
        try{
            parsedInputData.setRobotInitialRowPosition(RobotSimulationUtil.getIntValue(initialPosition[0]));
            parsedInputData.setRobotInitialColumnPosition(RobotSimulationUtil.getIntValue(initialPosition[1]));
            parsedInputData.setRobotInitialOrientation(initialPosition[2].toUpperCase());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Exception occur while parsing initial position orientation data.");
        }
    }

}

package org.iosb.robot.simulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iosb.robot.simulator.exceptions.IllegalRobotMoveException;
import org.iosb.robot.simulator.exceptions.InvalidRobotSimulationInputException;
import org.iosb.robot.simulator.model.ParsedInputData;
import org.iosb.robot.simulator.services.InputParserService;
import org.iosb.robot.simulator.services.RobotSimulationService;
import org.iosb.robot.simulator.services.impl.InputParserServiceImpl;
import org.iosb.robot.simulator.services.impl.RobotSimulationServiceImpl;

public class RobotSimulation {

    private static final Logger logger = LogManager.getLogger(RobotSimulation.class);

    public static void main(String[] args) {


        try {
            validateArgs(args);
            String input = args[0];
            logger.info("Request to process the simulation of robot for commands {} ", input);

            InputParserService inputParserService = new InputParserServiceImpl();
            ParsedInputData parsedInputData = inputParserService.parseSimulationInputData(input);

            RobotSimulationService robotSimulationService = new RobotSimulationServiceImpl();
            processSimulation(robotSimulationService, parsedInputData);

        } catch (IllegalArgumentException | InvalidRobotSimulationInputException | IllegalRobotMoveException exception) {
            logger.error(" Robot simulation failed with error: {}", exception.getMessage());
        } catch (Exception exception) {
            logger.error("Robot simulation failed. {} ", exception.getMessage() );
        }

    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No input provided.");
        }
    }

    private static void processSimulation(RobotSimulationService robotSimulationService, ParsedInputData parsedInputData) {
        String robotNewPosition = robotSimulationService.simulateRobotToNewPosition(parsedInputData);
        logger.info("Robot new position : {}", robotNewPosition);
    }
}
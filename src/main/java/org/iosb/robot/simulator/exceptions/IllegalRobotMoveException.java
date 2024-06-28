package org.iosb.robot.simulator.exceptions;

public class IllegalRobotMoveException extends RuntimeException {

    public IllegalRobotMoveException(String exceptionMessage) {
        super((exceptionMessage));
    }
}

package org.iosb.robot.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RobotPosition {

    private int row;
    private int column;
    private String orientation;
}

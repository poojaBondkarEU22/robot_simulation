package org.iosb.robot.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ParsedInputData {

    private int tableRows;
    private int tableColumns;
    private int robotInitialRowPosition;
    private int robotInitialColumnPosition;
    private String robotInitialOrientation;
    private String movementCommands;
}

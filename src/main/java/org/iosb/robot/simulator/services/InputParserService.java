package org.iosb.robot.simulator.services;

import org.iosb.robot.simulator.model.ParsedInputData;

public interface InputParserService {

    ParsedInputData parseSimulationInputData(String value);
}

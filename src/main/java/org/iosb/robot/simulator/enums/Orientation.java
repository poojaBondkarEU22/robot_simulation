package org.iosb.robot.simulator.enums;

public enum Orientation {

    N,S,E,W;

    public static boolean contains(String value) {
        for (Orientation orientation : Orientation.values()) {
            if(orientation.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}

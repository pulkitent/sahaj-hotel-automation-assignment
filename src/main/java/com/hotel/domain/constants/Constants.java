package com.hotel.domain.constants;

/* This class represents parameters which doesn't change it's value */
public class Constants {
    /* Made static to save memory and final to avoid mutation */
    public static final Integer AC_POWER_CONSUMPTION = 10;
    public static final Integer LIGHT_POWER_CONSUMPTION = 5;
    public static final Integer MAIN_CORRIDOR_RATE = 15;
    public static final Integer SUB_CORRIDOR_RATE = 10;
    public static final String INITIAL_STATE_OF_ALL_EQUIPMENTS = "Initial state of all equipments";
    public static final String EQUIPMENTS_STATE_AFTER_MOVEMENT = "Equipment's state after movement";
    public static final String EQUIPMENTS_STATE_AFTER_REST = "Equipment's state after rest";

    private Constants() {
        /* Private contr so as to stop any dev from making
        object of this class by default public contr  */
    }
}
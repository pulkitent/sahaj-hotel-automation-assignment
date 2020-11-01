package com.hotel.domain.constants;

/* This class represents parameters which doesn't change it's value */
public class Constants {
    /* Made static to save memory and final to avoid mutation */
    public static final Integer AC_POWER_CONSUMPTION = 10;
    public static final Integer LIGHT_POWER_CONSUMPTION = 5;
    public static final Integer MAIN_CORRIDOR_RATE = 15;
    public static final Integer SUB_CORRIDOR_RATE = 10;

    private Constants() {
        /* Private contr so as to stop any dev from making
        object of this class by default public contr  */
    }
}
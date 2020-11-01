package com.hotel.domain.entities;

import java.util.List;

/* This class represents a hotel with floors & a controller
for optimizing power consumption by equipments */
public class Hotel {
    private final List<Floor> floors;
    private final Controller controller;

    public Hotel(List<Floor> floors, Controller controller) {
        this.floors = floors;
        this.controller = controller;
    }

    public void startController() {
        controller.optimizePowerConsumptionForAllFloors(floors);
    }
}
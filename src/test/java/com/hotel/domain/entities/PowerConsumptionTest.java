package com.hotel.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PowerConsumptionTest {
    @Test
    @DisplayName("Should return correct value when power consumption object is created")
    void value_ShouldGiveCorrectValue() {
        //Arrange
        PowerConsumption powerConsumption = new PowerConsumption(10);

        //Action
        Integer value = powerConsumption.value();

        //Assert
        assertThat(value, is(10));
    }
}

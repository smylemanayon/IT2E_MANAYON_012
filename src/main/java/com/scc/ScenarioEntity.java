package com.expressway.model;

public record ScenarioEntity(
        int id,
        String plateNumber,
        String vehicleClass,
        double tollFee,
        String paymentMethod
) {}
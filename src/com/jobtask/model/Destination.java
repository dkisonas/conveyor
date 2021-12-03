package com.jobtask.model;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private final int destinationNumber;
    private final List<Load> successfullyDivertedLoads;
    private final List<Load> failedToDivertLoads;

    public Destination(int destinationNumber) {
        this.destinationNumber = destinationNumber;
        this.successfullyDivertedLoads = new ArrayList<>();
        this.failedToDivertLoads = new ArrayList<>();
    }

    public void addSuccessfulLoad(Load load) {
        successfullyDivertedLoads.add(load);
    }

    public void addFailedLoad(Load load) {
        failedToDivertLoads.add(load);
    }

    public int getSuccessfullyDivertedLoadsCount() {
        return successfullyDivertedLoads.size();
    }

    public int getFailedToDivertLoadsCount() {
        return failedToDivertLoads.size();
    }

    public int getDestinationNumber() {
        return destinationNumber;
    }
}

package com.jobtask.model;

import com.jobtask.model.enums.DestinationStrategy;

public class ConveyorConfiguration {
    private int destinationCount;
    private DestinationStrategy destinationStrategy;
    private int consecutiveLoadsToSingleDestination;
    private int failureToDivertPercentage;
    private int loadCount;


    public int getDestinationCount() {
        return destinationCount;
    }

    public void setDestinationCount(int destinationCount) {
        this.destinationCount = destinationCount;
    }

    public DestinationStrategy getDestinationStrategy() {
        return destinationStrategy;
    }

    public void setDestinationStrategy(DestinationStrategy destinationStrategy) {
        this.destinationStrategy = destinationStrategy;
    }

    public int getConsecutiveLoadsToSingleDestination() {
        return consecutiveLoadsToSingleDestination;
    }

    public void setConsecutiveLoadsToSingleDestination(int consecutiveLoadsToSingleDestination) {
        this.consecutiveLoadsToSingleDestination = consecutiveLoadsToSingleDestination;
    }

    public int getFailureToDivertPercentage() {
        return failureToDivertPercentage;
    }

    public void setFailureToDivertPercentage(int failureToDivertPercentage) {
        this.failureToDivertPercentage = failureToDivertPercentage;
    }

    public int getLoadCount() {
        return loadCount;
    }

    public void setLoadCount(int loadCount) {
        this.loadCount = loadCount;
    }
}

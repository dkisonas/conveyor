package com.jobtask.model.enums;

public enum DestinationStrategy {
    ROUND_ROBIN(1),
    RANDOM(2);

    public final int strategyId;

    DestinationStrategy(int strategyId) {
        this.strategyId = strategyId;
    }
}

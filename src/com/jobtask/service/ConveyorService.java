package com.jobtask.service;

import com.jobtask.model.ConveyorConfiguration;
import com.jobtask.model.Destination;
import com.jobtask.model.enums.DestinationStrategy;
import com.jobtask.model.Load;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConveyorService {
    private final ConveyorConfiguration conveyorConfiguration;
    private List<Load> loads;
    private List<Destination> destinations;

    public ConveyorService(ConveyorConfiguration conveyorConfiguration) {
        this.conveyorConfiguration = conveyorConfiguration;
        createLoads();
        createDestinations();
    }

    public List<Destination> sendLoadsToDestinations() {
        if (conveyorConfiguration.getDestinationStrategy() == DestinationStrategy.RANDOM) {
            sendLoadsUsingRandomStrategy();
        } else if (conveyorConfiguration.getDestinationStrategy() == DestinationStrategy.ROUND_ROBIN) {
            sendLoadsUsingRoundRobinStrategy();
        }
        return destinations;
    }

    private void sendLoadsUsingRandomStrategy() {
        int destinationCount = conveyorConfiguration.getDestinationCount();
        double probabilityWeight = 100d / destinationCount;
        Random r = new Random();

        for (int loadIndex = 0; loadIndex < loads.size(); loadIndex++) {
            double percentage = r.nextDouble(100);
            for (int destinationIndex = 1; destinationIndex <= destinationCount; destinationIndex++) {
                double probabilityWeightRange = probabilityWeight * destinationIndex;
                if (percentage < probabilityWeightRange) {
                    loadIndex = divertXConsecutiveLoadsToDestination(destinationIndex, loadIndex);
                    break;
                }
            }
        }
    }

    private void sendLoadsUsingRoundRobinStrategy() {
        int destinationIndex = 1;
        for (int i = 0; i < loads.size(); i++) {
            i = divertXConsecutiveLoadsToDestination(destinationIndex, i);
            destinationIndex = switchDestination(destinationIndex);
        }
    }

    private int divertXConsecutiveLoadsToDestination(int destinationIndex, int loadIndex) {
        for (int j = 0; j < conveyorConfiguration.getConsecutiveLoadsToSingleDestination(); j++) {
            tryToDivertLoadToDestination(loads.get(loadIndex), destinations.get(destinationIndex));
            loadIndex++;
        }
        loadIndex--;

        return loadIndex;
    }

    private int switchDestination(int destinationIndex) {
        if (destinationIndex == conveyorConfiguration.getDestinationCount()) {
            destinationIndex = 1;
        }
        destinationIndex++;
        return destinationIndex;
    }

    private void tryToDivertLoadToDestination(Load load, Destination destination) {
        Random r = new Random();
        int percentage = r.nextInt(100);
        if (percentage > conveyorConfiguration.getFailureToDivertPercentage()) {
            destination.addSuccessfulLoad(load);
            destinations.get(0).addFailedLoad(load);
        } else {
            destination.addFailedLoad(load);
            destinations.get(0).addSuccessfulLoad(load);
        }
    }

    private void createDestinations() {
        destinations = new ArrayList<>();
        for (int i = 0; i <= conveyorConfiguration.getDestinationCount(); i++) {
            destinations.add(new Destination(i));
        }
    }


    private void createLoads() {
        loads = new ArrayList<>();
        for (int i = 0; i < conveyorConfiguration.getLoadCount(); i++) {
            loads.add(new Load(i));
        }
    }
}

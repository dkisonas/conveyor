package com.jobtask.controller;

import com.jobtask.model.ConveyorConfiguration;
import com.jobtask.model.Destination;
import com.jobtask.model.enums.DestinationStrategy;
import com.jobtask.service.ConveyorService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConveyorController {

    private final ConveyorConfiguration conveyorConfiguration = new ConveyorConfiguration();

    public void runConveyor() {
        setConveyorConfigurationFromUserInput();
        ConveyorService conveyorService = new ConveyorService(conveyorConfiguration);
        List<Destination> destinations = conveyorService.sendLoadsToDestinations();
        printConveyorResults(destinations);
    }

    private void printConveyorResults(List<Destination> destinations) {
        for (Destination destination : destinations) {
            System.out.printf("Destination %s:\nSuccessfully diverted loads: %s\nFailed to divert loads: %s\n\n%n",
                    destination.getDestinationNumber(),
                    destination.getSuccessfullyDivertedLoadsCount(),
                    destination.getFailedToDivertLoadsCount());
        }
    }

    private void setConveyorConfigurationFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        setDestinationCount(scanner);
        setDestinationSelectionStrategy(scanner);
        setConsecutiveLoadsToSingleDestination(scanner);
        setFailureToDivertPercentage(scanner);
        setLoadCount(scanner);
    }

    private void setLoadCount(Scanner scanner) {
        System.out.println("Please enter the number of loads that the application should select a destination for: ");
        int loadCount = scanner.nextInt();
        conveyorConfiguration.setLoadCount(loadCount);
    }

    private void setFailureToDivertPercentage(Scanner scanner) {
        System.out.println("Please enter a percentage of failure for load to be diverted into its destination: ");
        int failureToDivertPercentage = scanner.nextInt();
        conveyorConfiguration.setFailureToDivertPercentage(failureToDivertPercentage);
    }

    private void setConsecutiveLoadsToSingleDestination(Scanner scanner) {
        System.out.println("Please enter the number of consecutive loads that upon arrival to X mark must get the same destination selected: ");
        int consecutiveLoadsToSingleDestination = scanner.nextInt();
        conveyorConfiguration.setConsecutiveLoadsToSingleDestination(consecutiveLoadsToSingleDestination);
    }

    private void setDestinationSelectionStrategy(Scanner scanner) {
        System.out.println("Please select a destination selection strategy (1 - Round Robin, 2 - Random): ");
        int strategy = scanner.nextInt();
        DestinationStrategy destinationStrategy = Arrays.stream(DestinationStrategy.values())
                .filter(s -> s.strategyId == strategy)
                .findFirst()
                .orElseThrow();
        conveyorConfiguration.setDestinationStrategy(destinationStrategy);
    }

    private void setDestinationCount(Scanner scanner) {
        System.out.println("Please enter number of available destinations (0-n): ");
        int destinationCount = scanner.nextInt();
        conveyorConfiguration.setDestinationCount(destinationCount);
    }


}

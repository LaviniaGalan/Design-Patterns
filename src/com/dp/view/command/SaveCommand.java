package com.dp.view.command;

import com.dp.domain.PredictiveReport;
import com.dp.repository.iterator.Iterator;
import com.dp.service.Service;

import java.util.Scanner;

public class SaveCommand extends Command{
    Service service;

    public SaveCommand(String key, String text, Service service) {
        super(key, text);
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Iterator reportsIterator = service.getReportsIterator();
        while(reportsIterator.hasNext()){
            PredictiveReport currentReport = (PredictiveReport) reportsIterator.getNext();

            System.out.println(currentReport);
            System.out.println("\nWant to save? [Y/N]: ");

            String response = scanner.nextLine();
            if(response.equalsIgnoreCase("Y")){
                System.out.println("Enter a file name: ");
                String fileName = scanner.nextLine();
                service.saveReport(fileName, currentReport);
                System.out.println("Saving...");
            }
        }
        System.out.println("No more reports.");
    }
}

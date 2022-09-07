package com.dp.view.command;

import com.dp.domain.PredictiveReport;
import com.dp.service.Service;

import java.util.Scanner;

public class PredictCommand extends Command{

    Service service;

    public PredictCommand(String key, String text, Service service) {
        super(key, text);
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Specify the report type [COMPLETE/EXCLUSIVE]: ");
        String reportType = scanner.nextLine();

        System.out.println("Specify the input type [SIMPLE/EXTENDED]: ");
        String inputType = scanner.nextLine();

        System.out.println("Gender [F/M]: ");
        String gender = scanner.nextLine();

        System.out.println("Age: ");
        int age = scanner.nextInt();

        System.out.println("Cholesterol: ");
        float cholesterol = scanner.nextFloat();

        System.out.println("BMI: ");
        float bmi = scanner.nextFloat();

        float urea = 0;
        int creatinine = 0;

        if (inputType.equalsIgnoreCase("EXTENDED")){

            System.out.println("Urea: ");
            urea = scanner.nextFloat();

            System.out.println("Creatinine: ");
            creatinine = scanner.nextInt();
        }


        PredictiveReport report = service.getPredictedReport(inputType, gender, age, urea, creatinine, cholesterol, bmi, reportType);

        System.out.println(report);
    }
}

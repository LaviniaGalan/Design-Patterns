package com.dp.service.strategy;

import com.dp.domain.DiabetesRecord;

public class EuclideanDistanceCalculator implements DistanceCalculator {

    @Override
    public float computeDistance(DiabetesRecord r1, DiabetesRecord r2) {
        float distance = 0;
        distance += Math.pow((r1.getAge() - r2.getAge()), 2);
        distance += Math.pow((r1.getCholesterol() - r2.getCholesterol()), 2);
        distance += Math.pow((r1.getBMI() - r2.getBMI()), 2);
        int genderDifference = r1.getGender().charAt(0) - r2.getGender().charAt(0);
        distance += Math.pow(genderDifference, 2);

        return (float) Math.sqrt(distance);
    }
}

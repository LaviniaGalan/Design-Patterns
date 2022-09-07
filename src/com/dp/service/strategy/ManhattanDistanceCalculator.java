package com.dp.service.strategy;

import com.dp.domain.DiabetesRecord;

public class ManhattanDistanceCalculator implements DistanceCalculator{

    @Override
    public float computeDistance(DiabetesRecord r1, DiabetesRecord r2) {
        float distance = 0;
        distance += Math.abs(r1.getAge() - r2.getAge());
        distance += Math.abs(r1.getCholesterol() - r2.getCholesterol());
        distance += Math.abs(r1.getBMI() - r2.getBMI());
        distance += Math.abs(r1.getUrea() - r2.getUrea());
        distance += Math.abs(r1.getCreatinine() - r2.getCreatinine());

        int genderDifference = r1.getGender().charAt(0) - r2.getGender().charAt(0);
        distance += Math.abs(genderDifference);

        return distance;
    }
}

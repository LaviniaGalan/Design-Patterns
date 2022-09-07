package com.dp.service.strategy;

import com.dp.domain.DiabetesRecord;

public interface DistanceCalculator {

    float computeDistance(DiabetesRecord r1, DiabetesRecord r2);

}

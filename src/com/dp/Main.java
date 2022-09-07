package com.dp;

import com.dp.repository.DiabetesRecordRepository;
import com.dp.repository.ReportsRepository;
import com.dp.service.Service;
import com.dp.view.View;


public class Main {

    public static void main(String[] args) {
        DiabetesRecordRepository recordRepository = new DiabetesRecordRepository();
        ReportsRepository reportsRepository = new ReportsRepository();
        Service service = new Service(recordRepository, reportsRepository);
        View view = new View(service);

        view.run();
    }
}

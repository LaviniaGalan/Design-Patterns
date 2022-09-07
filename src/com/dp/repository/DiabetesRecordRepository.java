package com.dp.repository;

import com.dp.domain.DiabetesRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiabetesRecordRepository implements IRepository<DiabetesRecord>{

    List<DiabetesRecord> allRecords = new ArrayList<>();

    public DiabetesRecordRepository() {
    }

    @Override
    public Iterable<DiabetesRecord> getAll() {
        if(allRecords.isEmpty()){
            Connection connection = DbConnection.getInstance();

            String query = "select * from records";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    DiabetesRecord record = new DiabetesRecord.RecordBuilder()
                            .setGender(resultSet.getString("gender"))
                            .setAge(Integer.valueOf(resultSet.getString("age")))
                            .setUrea(Float.valueOf(resultSet.getString("urea")))
                            .setCreatinine(Integer.valueOf(resultSet.getString("cr")))
                            .setCholesterol(Float.valueOf(resultSet.getString("chol")))
                            .setBMI(Float.valueOf(resultSet.getString("bmi")))
                            .setDiabetesClass(resultSet.getString("class"))
                            .build();

                    allRecords.add(record);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return allRecords;
    }
}

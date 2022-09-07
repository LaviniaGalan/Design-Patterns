package com.dp.utils;

import java.util.List;

public class HTMLTableFormatter {

    public static String format(DataTable dataTable){
        String table = "<table style=\"border: 1px solid black;\">";

        table += "<tr>";
        for (String element: dataTable.getHeaders()){
            table += "<td style=\"border: 1px solid black;\">" + element + "</td>";
        }
        table += "</tr>";

        for(List<String> row: dataTable.getRows()){
            table += "<tr>";
            for (String element: row){
                table += "<td style=\"border: 1px solid black;\">" + element + "</td>";
            }
            table += "</tr>";
        }

        table += "</table>";

        return table;
    }
}

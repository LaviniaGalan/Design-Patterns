package com.dp.utils;

import java.util.List;

public interface DataTable {
    List<String> getHeaders();
    List<List<String>> getRows();
}

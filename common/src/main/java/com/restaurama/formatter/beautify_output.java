package com.restaurama.formatter;
import java.util.*;

public class beautify_output {
    public static void beautify_table(List<Map<String, Object>> queryResults) {
        // Handling the case of empty results, no table at all
        if (queryResults == null || queryResults.isEmpty()) {
            System.out.println("Query results is null or empty, no data to display.");
        }
        // Extract headers from the first row of the Map
        Set<String> columnHeaders = queryResults.get(0).keySet();
        // Dynamically set the column width
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String column : columnHeaders) {
            int maxWidth = column.length();
            for (Map<String, Object> row : queryResults) {
                Object value = row.get(column);
                maxWidth = Math.max(maxWidth, (value != null ? value.toString().length() : 4)); // "null" is 4 chars
            }
            columnWidths.put(column, maxWidth);
        }
        // Create a formatted header row
        StringBuilder header = new StringBuilder();
        for (String column : columnHeaders) {
            header.append(String.format("%-" + (columnWidths.get(column) + 2) + "s", column));
        }
        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        // Create formatted data rows
        for (Map<String, Object> row : queryResults) {
            StringBuilder rowOutput = new StringBuilder();
            for (String column : columnHeaders) {
                Object value = row.get(column);
                rowOutput.append(String.format("%-" + (columnWidths.get(column) + 2) + "s",
                        value != null ? value : "null"));
            }
            System.out.println(rowOutput);
        }
    }
}
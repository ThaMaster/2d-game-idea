package se.gmail.game.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonLoader {

    public static ArrayList<ArrayList<String>> loadStockData(String path) {
        ArrayList<ArrayList<String>> stockData = new ArrayList<>();

        InputStream inputStream = JsonLoader.class.getResourceAsStream(path);
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        String jsonText = scanner.useDelimiter("\\A").next();
        JSONArray jsonArray = new JSONArray(jsonText);

        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<String> data = new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            data.add(String.valueOf(jsonObject.getInt("id")));
            data.add(jsonObject.getString("name"));
            data.add(jsonObject.getString("symbol"));
            data.add(jsonObject.getString("description"));
            data.add(jsonObject.getString("iconPath"));
            stockData.add(data);
        }

        scanner.close();
        return stockData;
    }
    
}

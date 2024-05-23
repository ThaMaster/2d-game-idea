package se.gmail.game.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonLoader {

    public static void loadStockData(String path) {

        InputStream inputStream = JsonLoader.class.getResourceAsStream(path);
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        String jsonText = scanner.useDelimiter("\\A").next();
        JSONArray jsonArray = new JSONArray(jsonText);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String symbol = jsonObject.getString("symbol");
            String desc = jsonObject.getString("description");
            String iconPath = jsonObject.getString("iconPath");

            System.out.println("Id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Symbol: " + symbol);
            System.out.println("Description: " + desc);
            System.out.println("Icon Path: " + iconPath);
        }

        scanner.close();
    }
    
}

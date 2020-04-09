package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String jsonString) {
        JSONObject sandwich = null;
        try {
            sandwich = new JSONObject(jsonString);
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsJsonArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownList = convertJSONArrayToList(alsoKnownAsJsonArray);
            String placeOfOrigin = sandwich.getString("placeOfOrigin");
            String description = sandwich.getString("description");
            String imageURL = sandwich.getString("image");
            JSONArray ingredientsJsonArray = sandwich.getJSONArray("ingredients");
            List<String> ingredientsList = convertJSONArrayToList(ingredientsJsonArray);
            return new Sandwich(mainName,alsoKnownList,placeOfOrigin,description,imageURL,ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static List<String> convertJSONArrayToList( JSONArray jsonArray) throws JSONException {
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i ++){
            resultList.add(jsonArray.getString(i));
        }
        return resultList;
    }

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;
}

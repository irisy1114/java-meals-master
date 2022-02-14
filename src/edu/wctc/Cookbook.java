package edu.wctc;

import java.util.*;

import static edu.wctc.MealType.*;

public class Cookbook {

    // Hold all the meals that are read in from the file
    private List<Meal> mealList = new ArrayList<>();

    public void addMeal(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

        try {
            mealType = MealType.valueOf(mealTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Meal Creation Error: Invalid meal type %s. Defaulted to Dinner.", mealTypeStr));
            mealType = DINNER;
        }


        int calories;

        try {
            calories = Integer.parseInt(caloriesStr);
        } catch (NumberFormatException nfe) {
            System.out.println(String.format("Meal Creation Error: Invalid calories %s. Defaulted to 100.", caloriesStr));
            calories = 100;
        }

        mealList.add(new Meal(mealType, mealNameStr, calories));
    }

    public String doControlBreak() {

        String breakfast = mealCaloriesInformation(BREAKFAST);
        String dessert = mealCaloriesInformation(DESSERT);
        String dinner = mealCaloriesInformation(DINNER);
        String lunch = mealCaloriesInformation(LUNCH);

        return breakfast + "\n" + dessert + "\n" + dinner + "\n" + lunch;
    }

    public String mealCaloriesInformation(MealType mealType) {

        List<Integer> list = new ArrayList<>();

        for(Meal item: mealList) {
            if(item.getMealType() == mealType) {
                list.add(item.getCalories());
            }
        }

        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        double average = sum / list.size();
        int min = Collections.min(list);
        int max = Collections.max(list);

        // get the median of the list
        Collections.sort(list);
        int median;
        if(list.size() % 2 == 1) {
            median = list.get((list.size() + 1) / 2 - 1);
        }
        else {
            median = ((list.get(list.size() / 2 - 1))) / 2 + (list.get(list.size() / 2)) / 2;
        }

        return "Meal Type: " + mealType + "Total Calories: " + sum + " Mean: " + average + " Min: " + min + " Max: " + max + " Median: " + median;
    }

    public String getAllMeals() {
        String result = "";
        for (Meal item : mealList) {
            result += item + "\n";
        }
        return result;
    }

    public String searchByName(String name) {
        String result = "";
        for (Meal item : mealList) {
            // Uppercase for case insensitivity
            if (item.getItem().contains(name)) {
                result += item + "\n";
            }
        }

        return result.isBlank() ? "No matches found" : result;
    }

    public String searchByType(MealType mealType) {
        String result = "";
        for (Meal item : mealList) {
            if (item.getMealType() == mealType) {
                result += item + "\n";
            }
        }

        return result.isBlank() ? "No matches found" : result;
    }
}

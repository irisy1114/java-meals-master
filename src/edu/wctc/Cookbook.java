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
        List<Integer> breakfastList = new ArrayList<>();
        List<Integer> dessertList = new ArrayList<>();
        List<Integer> dinnerList = new ArrayList<>();
        List<Integer> lunchList = new ArrayList<>();

        for(Meal item: mealList) {
            if(item.getMealType() == BREAKFAST) {
                breakfastList.add(item.getCalories());
            }
            else if(item.getMealType() == DESSERT) {
                dessertList.add(item.getCalories());
            }
            else if(item.getMealType() == DINNER) {
                dinnerList.add(item.getCalories());
            }
            else
                lunchList.add(item.getCalories());
        }

        String breakfast = mealCaloriesInfo("Breakfast", breakfastList);
        String dessert = mealCaloriesInfo("Dessert", dessertList);
        String dinner = mealCaloriesInfo("Dinner", dinnerList);
        String lunch = mealCaloriesInfo("Lunch", lunchList);

        return breakfast + "\n" + dessert + "\n" + dinner + "\n" + lunch;

    }

    public String mealCaloriesInfo(String mealType, List<Integer> mealTypeList) {

        int sum = 0;
        for (int i : mealTypeList) {
            sum += i;
        }
        double average = sum / mealTypeList.size();
        int min = Collections.min(mealTypeList);
        int max = Collections.max(mealTypeList);

        // get the median of the list
        Collections.sort(mealTypeList);
        int median;
        if(mealTypeList.size() % 2 == 1) {
            median = mealTypeList.get((mealTypeList.size() + 1) / 2 - 1);
        }
        else {
            median = ((mealTypeList.get(mealTypeList.size() / 2 - 1))) / 2 + (mealTypeList.get(mealTypeList.size() / 2)) / 2;
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

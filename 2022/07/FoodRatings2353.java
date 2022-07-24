class FoodRatings2353 {

    Map<String, Food> foodMap;
    
    Map<String, SortedSet> cuisineSet;
    
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>();
        cuisineSet = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(food.food, food);
            if (!cuisineSet.containsKey(food.cuisine)) {
                SortedSet<Food> set = new TreeSet<>((f1, f2) -> {
                    if (f1.rating.compareTo(f2.rating) == 0) {
                        return f1.food.compareTo(f2.food);
                    }
                    return -f1.rating.compareTo(f2.rating);
                });
                cuisineSet.put(food.cuisine, set);
            }
            cuisineSet.get(food.cuisine).add(food);
        }
        
    }
    
    public void changeRating(String name, int newRating) {
        Food food = foodMap.get(name);
        cuisineSet.get(food.cuisine).remove(food);
        food.rating = newRating;
        cuisineSet.get(food.cuisine).add(food);
    }
    
    public String highestRated(String cuisine) {
        SortedSet<Food> set = cuisineSet.get(cuisine);
        return set.first().food;
        // return cuisineSet.get(cuisine).first().food;
    }
}

class Food {
    String food;
    String cuisine;
    Integer rating;
    Food(String food, String cuisine, int rating) {
        this.food = food;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

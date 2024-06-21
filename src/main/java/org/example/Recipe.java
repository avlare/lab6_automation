package org.example;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final String name;
    private final List<String> ingredients;

    public Recipe(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        for (String ingredient : ingredients) {
            this.ingredients.add(ingredient.toLowerCase());
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}

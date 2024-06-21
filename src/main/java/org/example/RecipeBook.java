package org.example;
import java.util.ArrayList;
import java.util.List;

public class RecipeBook {
    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public boolean removeRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipes.remove(recipe);
                return true;
            }
        }
        return false;
    }

    public List<Recipe> findRecipesByIngredient(String ingredient) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }

    public boolean updateRecipe(String name, Recipe updatedRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equalsIgnoreCase(name)) {
                recipes.set(i, updatedRecipe);
                return true;
            }
        }
        return false;
    }

    public List<Recipe> findRecipesByIngredientCount(int count) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().size() == count) {
                result.add(recipe);
            }
        }
        return result;
    }

    public int getTotalRecipeCount() {
        return recipes.size();
    }
}
package io.cucumber.skeleton;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.example.Recipe;
import org.example.RecipeBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindRecipesByIngredientCountSteps {

    private RecipeBook recipeBook;
    private final List<Recipe> recipesToAdd = new ArrayList<>();

    @DataTableType
    public static recipeElements defineRecipeElements(List<String> elem) {
        return new recipeElements(elem.get(0), elem.get(1));
    }

    @Given("the following recipes exist:")
    public void the_following_recipes_exist(List<recipeElements> allRecipes) {
        recipeBook = new RecipeBook();
        for (recipeElements recipeData : allRecipes) {
            Recipe recipe = new Recipe(recipeData.name, Arrays.asList(recipeData.ingredients.split(", ")));
            recipesToAdd.add(recipe);
            recipeBook.addRecipe(recipe);
        }
    }

    @When("I search for recipes with {int} ingredients")
    public void i_search_for_recipes_with_ingredients(int numOfIngredients) {
        List<Recipe> foundRecipes = recipeBook.findRecipesByIngredientCount(numOfIngredients);
        recipesToAdd.clear();
        recipesToAdd.addAll(foundRecipes);
    }

    @Then("I should find {int} recipes")
    public void i_should_find_recipe(int expectedNumOfRecipes) {
        Assertions.assertThat(recipesToAdd.size()).isEqualTo(expectedNumOfRecipes);
    }

    static class recipeElements {
        String name;
        String ingredients;

        public recipeElements(String name, String ingredients) {
            this.name = name;
            this.ingredients = ingredients;
        }
    }
}
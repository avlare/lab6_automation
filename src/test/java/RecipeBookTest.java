import org.assertj.core.api.Assertions;
import org.example.Recipe;
import org.example.RecipeBook;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;


public class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeEach
    void init() {
        recipeBook = new RecipeBook();
    }

    //parametrized junit test
    @ParameterizedTest
    @ValueSource(strings = {"Salt", "Sugar"})
    void testAddingAndCheckingIngredients(String ingredient) {
        Recipe cake = new Recipe("Cake", Arrays.asList("magic flour", "purple eggs", "owl milk", "salt", "sugar"));
        Recipe baozi = new Recipe("Baozi", Arrays.asList("eggs", "flour", "milk", "meat", "salt"));

        recipeBook.addRecipe(cake);
        recipeBook.addRecipe(baozi);
        Assumptions.assumeTrue(recipeBook.getTotalRecipeCount() == 2);
        Assertions.assertThat(recipeBook.findRecipesByIngredient(ingredient))
                .contains(cake);
    }

    //junit test
    @Test
    void testRemovingRecipe() {
        Recipe baozi = new Recipe("Baozi", Arrays.asList("eggs", "flour", "milk", "meat", "salt"));
        recipeBook.addRecipe(baozi);
        Assumptions.assumeTrue(recipeBook.findRecipesByIngredient("baozi").contains(baozi));
        Assertions.assertThat(recipeBook.removeRecipe("baozi")).isTrue();
        Assertions.assertThat(recipeBook.getTotalRecipeCount()).isEqualTo(0);
    }
}

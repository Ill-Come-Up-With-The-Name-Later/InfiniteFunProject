package survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class CulminationRecipe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("culmination_sword"), ItemManager.culmination);

        recipe.shape(" S ",
                     " D ",
                     " B ");

        recipe.setIngredient('S', ItemManager.decrepitScroll.getData());
        recipe.setIngredient('D', ItemManager.dragonSword.getData());
        recipe.setIngredient('B', ItemManager.berserkSword.getData());

        Bukkit.getServer().addRecipe(recipe);
    }
}

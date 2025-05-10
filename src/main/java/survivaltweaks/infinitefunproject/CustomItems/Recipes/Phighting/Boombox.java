package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Boombox {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("boombox"), ItemManager.boombox);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.DISC_FRAGMENT_5);
        recipe.setIngredient('C', ItemManager.inphernalBase);
        recipe.setIngredient('S', Material.JUKEBOX);

        Bukkit.getServer().addRecipe(recipe);
    }
}

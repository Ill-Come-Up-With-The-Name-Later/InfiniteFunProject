package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Hyperlaser {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("hyperlaser"), ItemManager.hyperLaser);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('C', ItemManager.inphernalBase);
        recipe.setIngredient('S', Material.CROSSBOW);

        Bukkit.getServer().addRecipe(recipe);
    }
}

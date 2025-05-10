package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Sword {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("lost_temple_sword"), ItemManager.beamSword);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.IRON_INGOT);
        recipe.setIngredient('C', ItemManager.inphernalBase);
        recipe.setIngredient('S', Material.IRON_SWORD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

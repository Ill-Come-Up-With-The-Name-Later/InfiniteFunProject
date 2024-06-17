package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class RocketLauncher {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("rocket"), ItemManager.rocketLauncher);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.TNT);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.CROSSBOW);

        Bukkit.getServer().addRecipe(recipe);
    }
}

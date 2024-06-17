package survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class SuperHammerRecipe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("super_hammer"), ItemManager.superHammer);

        recipe.shape("DDD",
                     "DBD",
                     " B ");

        recipe.setIngredient('D', Material.IRON_BLOCK);
        recipe.setIngredient('B', ItemManager.weaponHandle.getData());

        Bukkit.getServer().addRecipe(recipe);
    }
}

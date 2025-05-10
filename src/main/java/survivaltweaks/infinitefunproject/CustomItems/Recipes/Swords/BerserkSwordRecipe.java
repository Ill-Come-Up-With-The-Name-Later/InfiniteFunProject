package survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class BerserkSwordRecipe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("berserk_sword"), ItemManager.berserkSword);

        recipe.shape(" I ",
                     " I ",
                     " B ");

        recipe.setIngredient('B', ItemManager.weaponHandle);
        recipe.setIngredient('I', Material.IRON_BLOCK);

        Bukkit.getServer().addRecipe(recipe);
    }
}

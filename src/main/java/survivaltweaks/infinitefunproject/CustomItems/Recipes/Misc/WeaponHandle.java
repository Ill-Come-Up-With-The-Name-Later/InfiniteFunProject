package survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class WeaponHandle {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("weapon_handle"), ItemManager.weaponHandle);

        recipe.shape(" B ",
                     " B ",
                     " B ");

        ItemStack blazeRod = new ItemStack(Material.BLAZE_ROD, 16);

        recipe.setIngredient('B', blazeRod);

        Bukkit.getServer().addRecipe(recipe);
    }
}

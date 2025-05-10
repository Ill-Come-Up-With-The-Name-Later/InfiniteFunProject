package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class BanHammer {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("ban_hammer"), ItemManager.banHammer);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.IRON_BARS);
        recipe.setIngredient('C', ItemManager.inphernalBase);
        recipe.setIngredient('S', Material.NETHERITE_AXE);

        Bukkit.getServer().addRecipe(recipe);
    }
}

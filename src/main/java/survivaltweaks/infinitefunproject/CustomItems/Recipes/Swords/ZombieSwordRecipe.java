package survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class ZombieSwordRecipe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("zombie_sword"), ItemManager.zombieSword);

        recipe.shape("RRR",
                     "RIR",
                     "RRR");

        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH, 2);

        recipe.setIngredient('R', flesh);
        recipe.setIngredient('I', Material.IRON_SWORD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

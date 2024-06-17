package survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class SpiderSwordRecipe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("spider_sword"), ItemManager.spiderSword);

        recipe.shape("EEE",
                "EIE",
                "EEE");

        ItemStack spiderEyes = new ItemStack(Material.SPIDER_EYE, 2);

        recipe.setIngredient('E', spiderEyes.getData());
        recipe.setIngredient('I', Material.IRON_SWORD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

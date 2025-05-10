package survivaltweaks.infinitefunproject.CustomItems.Recipes.Weapons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class BouncyBow {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("bouncy_bow"), ItemManager.bounceBow);

        recipe.shape("SSS",
                     "SBS",
                     "SSS");

        recipe.setIngredient('S', Material.SLIME_BALL);
        recipe.setIngredient('B', Material.BOW);

        Bukkit.getServer().addRecipe(recipe);
    }
}

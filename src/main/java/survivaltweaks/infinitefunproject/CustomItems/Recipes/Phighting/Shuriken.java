package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Shuriken {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("shuriken"), ItemManager.shuriken);

        recipe.shape("   ",
                "NCA",
                " S ");

        recipe.setIngredient('N', Material.SCUTE);
        recipe.setIngredient('A', Material.TORCHFLOWER_SEEDS);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.FLINT);

        Bukkit.getServer().addRecipe(recipe);
    }
}

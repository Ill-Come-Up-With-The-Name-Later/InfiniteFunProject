package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Medkit {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("medkit"), ItemManager.medkit);

        recipe.shape("   ",
                "ECF",
                " S ");

        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('F', Material.SPIDER_EYE);
        recipe.setIngredient('S', Material.WARPED_HANGING_SIGN);

        Bukkit.getServer().addRecipe(recipe);
    }
}

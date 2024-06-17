package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class BiograftSword {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("bio_sword"), ItemManager.biograftSword);

        recipe.shape("   ",
                "ECE",
                " S ");

        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.GOLDEN_SWORD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Scythe {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("scythe"), ItemManager.scythe);

        recipe.shape("   ",
                "NCA",
                " S ");

        recipe.setIngredient('N', Material.NETHER_STAR);
        recipe.setIngredient('A', Material.ARROW);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.DIAMOND_HOE);

        Bukkit.getServer().addRecipe(recipe);
    }
}

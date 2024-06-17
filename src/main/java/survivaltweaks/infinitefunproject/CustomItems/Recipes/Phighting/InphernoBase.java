package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class InphernoBase {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("inpherno_base"), ItemManager.inphernalBase);

        recipe.shape("BCB",
                     "SDS",
                     "OCO");

        recipe.setIngredient('B', Material.BLAZE_POWDER);
        recipe.setIngredient('C', Material.AMETHYST_SHARD);
        recipe.setIngredient('S', Material.NETHERITE_SCRAP);
        recipe.setIngredient('D', Material.END_CRYSTAL);
        recipe.setIngredient('O', Material.SCULK_CATALYST);

        Bukkit.getServer().addRecipe(recipe);
    }
}

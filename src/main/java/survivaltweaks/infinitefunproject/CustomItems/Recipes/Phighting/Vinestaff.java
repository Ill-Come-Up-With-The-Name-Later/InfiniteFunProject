package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Vinestaff {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("vine_staff"), ItemManager.vineStaff);

        recipe.shape("   ",
                "NCA",
                " S ");

        recipe.setIngredient('N', Material.TORCHFLOWER_SEEDS);
        recipe.setIngredient('A', Material.SPORE_BLOSSOM);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.DIAMOND_HOE);

        Bukkit.getServer().addRecipe(recipe);
    }
}

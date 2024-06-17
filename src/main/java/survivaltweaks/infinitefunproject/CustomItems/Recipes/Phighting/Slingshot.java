package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Slingshot {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("slingshot"), ItemManager.slingshot);

        recipe.shape("   ",
                "ECD",
                " S ");

        recipe.setIngredient('E', Material.STONE);
        recipe.setIngredient('D', Material.TORCHFLOWER_SEEDS);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.LEAD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

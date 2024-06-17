package survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Katana {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("katana"), ItemManager.katana);

        recipe.shape("   ",
                "ECS",
                " I ");

        recipe.setIngredient('E', Material.RED_DYE);
        recipe.setIngredient('C', ItemManager.inphernalBase.getData());
        recipe.setIngredient('S', Material.SKULL_POTTERY_SHERD);
        recipe.setIngredient('I', Material.IRON_SWORD);

        Bukkit.getServer().addRecipe(recipe);
    }
}

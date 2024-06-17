package survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class EndlessPearl {

    public static void createRecipes() {
        createEnchantedPearlRecipe();
        createEndlessPearlRecipe();
    }

    public static void createEndlessPearlRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("endless_pearl"), ItemManager.endlessPearl);

        recipe.shape(" E ",
                     "ENE",
                     " E ");

        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('N', ItemManager.enchantedPearl.getData());

        Bukkit.getServer().addRecipe(recipe);
    }

    public static void createEnchantedPearlRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("enchanted_pearl"), ItemManager.enchantedPearl);

        recipe.shape("PPP",
                     "PNP",
                     "PPP");

        recipe.setIngredient('P', Material.ENDER_PEARL);
        recipe.setIngredient('N', Material.NETHER_STAR);

        Bukkit.getServer().addRecipe(recipe);
    }
}

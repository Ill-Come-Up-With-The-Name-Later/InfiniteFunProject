package survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Vaccine {

    public static void createRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("covid_vaccine"), ItemManager.vaccine);

        recipe.shape("FBF",
                "GDG",
                "NFN");

        recipe.setIngredient('F', Material.CHORUS_FLOWER);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('B', Material.IRON_BARS);
        recipe.setIngredient('D', Material.DRAGON_BREATH);
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);

        Bukkit.getServer().addRecipe(recipe);
    }
}

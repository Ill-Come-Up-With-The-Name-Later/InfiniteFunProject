package survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class Medication {

    public static void createRecipes() {
        createSugarRecipe();
        createMedRecipe();
    }

    public static void createSugarRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("special_sugar"), ItemManager.specialSugar);

        recipe.shape("FPG",
                     "KCK",
                     "GPF");

        recipe.setIngredient('F', Material.FERN);
        recipe.setIngredient('P', Material.POPPY);
        recipe.setIngredient('G', Material.SHORT_GRASS);
        recipe.setIngredient('K', Material.KELP);
        recipe.setIngredient('C', Material.CHORUS_FLOWER);
        Bukkit.getServer().addRecipe(recipe);
    }

    public static void createMedRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("medication"), ItemManager.medication);

        recipe.shape("SPS",
                "PSP",
                "SPS");

        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', ItemManager.specialSugar.getData());
        Bukkit.getServer().addRecipe(recipe);
    }
}

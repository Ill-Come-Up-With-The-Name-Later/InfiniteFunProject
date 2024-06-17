package survivaltweaks.infinitefunproject.CustomItems.Recipes;

import survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc.EndlessPearl;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc.Medication;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc.Vaccine;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.Misc.WeaponHandle;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.Phighting.CreatePhightingRecipes;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.Swords.*;

public class InitRecipes {

    public static void init() {
        ZombieSwordRecipe.createRecipe();
        SpiderSwordRecipe.createRecipe();
        SuperHammerRecipe.createRecipe();
        BerserkSwordRecipe.createRecipe();
        EndlessPearl.createRecipes();
        WeaponHandle.createRecipe();
        CulminationRecipe.createRecipe();
        Vaccine.createRecipe();
        Medication.createRecipes();

        CreatePhightingRecipes.init();
    }
}

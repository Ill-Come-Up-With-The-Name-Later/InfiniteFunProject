package survivaltweaks.infinitefunproject.CustomItems;

import survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners.InitListeners;
import survivaltweaks.infinitefunproject.CustomItems.Recipes.InitRecipes;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager;

public class InitCustomItems {

    public static void init() {
        ItemManager.init();
        InitRecipes.init();
        InitAbilities.init();
        InitListeners.init();
        UnusualManager.init();
    }
}

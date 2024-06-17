package survivaltweaks.infinitefunproject.Mobs.FarmAnimals;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.Animals.DropBleachedFood;

public class InitFarmAnimals {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new Cows(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new Chickens(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SheepModifier(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new Pigs(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropBleachedFood(), InfiniteFunProject.plugin);
    }
}

package survivaltweaks.infinitefunproject.Combat;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.RandomCritProjectiles;
import survivaltweaks.infinitefunproject.Combat.Shield.ShieldRework;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class CombatInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new DealRandomCrit(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ShieldRework(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new RandomCritProjectiles(), InfiniteFunProject.plugin);
    }
}

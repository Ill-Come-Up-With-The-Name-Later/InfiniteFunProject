package survivaltweaks.infinitefunproject.World.TrialChamber;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.World.TrialChamber.Listeners.BuffTrialSpawned;
import survivaltweaks.infinitefunproject.World.TrialChamber.Listeners.EquipTrialSpawned;
import survivaltweaks.infinitefunproject.World.TrialChamber.Listeners.PreventBlockDuringOminous;

public class InitTrial {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PreventBlockDuringOminous(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BuffTrialSpawned(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipTrialSpawned(), InfiniteFunProject.plugin);
    }
}

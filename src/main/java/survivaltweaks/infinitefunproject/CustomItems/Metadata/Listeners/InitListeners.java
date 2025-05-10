package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class InitListeners {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ExplosiveProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SeekingProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveGroundedProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BackstabListener(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BounceArrowListener(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new MeteorListener(), InfiniteFunProject.plugin);

        ProjectileShieldManager.init();
    }
}

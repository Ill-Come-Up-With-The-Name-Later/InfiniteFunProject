package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners.OneHitModeListener;

public class InitListeners {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ExplosiveProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SeekingProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveGroundedProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BackstabListener(), InfiniteFunProject.plugin);

        ProjectileShieldManager.init();
    }
}

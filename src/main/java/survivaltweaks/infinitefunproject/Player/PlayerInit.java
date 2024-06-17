package survivaltweaks.infinitefunproject.Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.Events.*;
import survivaltweaks.infinitefunproject.Player.Marriage.MarriageManager;
import survivaltweaks.infinitefunproject.Player.Torches.BurnoutTorch;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;

import static survivaltweaks.infinitefunproject.Player.ClearItems.clearEffects;
import static survivaltweaks.infinitefunproject.Player.ClearItems.clearItems;

public class PlayerInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AttemptSleep(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GiveDeathPos(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new FallFar(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemCrafted(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BurnoutTorch(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new PlaceCrystal(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new OnRespawn(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileHits(), InfiniteFunProject.plugin);

        ClearItems.init();
        InitUpgrades.init();
        MarriageManager.init();

        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    //BindArmor.addBinding(player);
                    clearItems(player);
                    clearEffects(player);
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }
}

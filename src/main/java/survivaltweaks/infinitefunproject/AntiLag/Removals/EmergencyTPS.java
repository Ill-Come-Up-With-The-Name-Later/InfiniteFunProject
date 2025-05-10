package survivaltweaks.infinitefunproject.AntiLag.Removals;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class EmergencyTPS {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(Bukkit.getServer().getTPS()[0] <= InitAntiLag.minTPS) {
                    removeMonsters();
                    killProjectiles();
                    removeTextDisplays();
                    removeItems();

                    Bukkit.spigot().broadcast(new TextComponent(ChatColor.RED + "EMERGENCY NUKING OF ENTITIES."));
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

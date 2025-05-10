package survivaltweaks.infinitefunproject.AntiLag.Removals;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.AntiLag.InitAntiLag.getPrimedTNTCount;

public class RemovePrimeTNT {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(getPrimedTNTCount(world) >= InitAntiLag.maxTNTCount) {
                        for(Entity entity : world.getEntities()) {
                            if(entity instanceof TNTPrimed) {
                                entity.remove();
                            }
                        }

                        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GREEN + "Nuked prime TNT!"));
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

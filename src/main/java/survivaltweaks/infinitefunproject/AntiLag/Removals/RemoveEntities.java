package survivaltweaks.infinitefunproject.AntiLag.Removals;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.AntiLag.InitAntiLag.getEntityCount;

public class RemoveEntities {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(getEntityCount(world) >= InitAntiLag.maxEntityCount) {
                        for(Entity entity : world.getEntities()) {
                            if(!(entity instanceof Player)) {
                                entity.remove();
                            }
                        }

                        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GREEN + "Nuked entities!"));
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

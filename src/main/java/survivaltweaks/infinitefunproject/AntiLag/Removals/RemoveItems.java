package survivaltweaks.infinitefunproject.AntiLag.Removals;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.AntiLag.InitAntiLag.getItemCount;

public class RemoveItems {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(getItemCount(world) >= InitAntiLag.maxItemCount) {
                        for(Entity entity : world.getEntities()) {
                            if(entity instanceof Item) {
                                entity.remove();
                            }
                        }

                        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GREEN + "Nuked items!"));
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

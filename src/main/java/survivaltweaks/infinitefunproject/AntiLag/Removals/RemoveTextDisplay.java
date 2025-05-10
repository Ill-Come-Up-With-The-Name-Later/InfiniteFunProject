package survivaltweaks.infinitefunproject.AntiLag.Removals;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.AntiLag.InitAntiLag.getTextDisplayCount;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.removeTextDisplays;

public class RemoveTextDisplay {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(getTextDisplayCount(world) >= InitAntiLag.maxTextDisplayCount) {
                        removeTextDisplays();
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

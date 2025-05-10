package survivaltweaks.infinitefunproject.AntiLag;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class PreventVelocity {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    for(Entity entity : world.getEntities()) {
                        if(entity.getVelocity().lengthSquared() >= 40) {
                            entity.setVelocity(new Vector(0, 0, 0));
                        }
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }
}

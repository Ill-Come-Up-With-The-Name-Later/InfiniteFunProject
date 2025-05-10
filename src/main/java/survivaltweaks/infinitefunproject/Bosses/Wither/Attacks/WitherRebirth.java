package survivaltweaks.infinitefunproject.Bosses.Wither.Attacks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class WitherRebirth implements Listener {

    /**
     * Wither rebirth loop
     *
     * @param event: Entity spawn event
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Wither) {
            Wither wither = (Wither) entity;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(wither.isDead()) {
                        cancel();
                        return;
                    }

                    if(wither.hasAI()) {
                        wither.setInvulnerableTicks(300);
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 6000, 6000);
        }
    }
}

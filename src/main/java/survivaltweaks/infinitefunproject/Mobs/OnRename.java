package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.InitDragon;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;


@Deprecated
public class OnRename implements Listener {

    @EventHandler
    public void onRename(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();

        if(!entity.getCustomName().contains(fixCaps(entity.getType().toString()))) {
            return;
        }

        if(OnSpawn.getLevelers().contains(entity.getType())) {
            if(!entity.getCustomName().contains(fixCaps(entity.getType().toString()))) {
                return;
            }
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(InitDragon.dragonDead()) {
                    entity.setCustomName(color("&6[&rLevel &b" + OnSpawn.getLevel(entity) + "&6] &e" + entity.getCustomName()));
                } else {
                    entity.setCustomName(color("&7[&rLevel &b" + OnSpawn.getLevel(entity) + "&7] &e" + entity.getCustomName()));
                }
            }, 1);
        }
    }
}

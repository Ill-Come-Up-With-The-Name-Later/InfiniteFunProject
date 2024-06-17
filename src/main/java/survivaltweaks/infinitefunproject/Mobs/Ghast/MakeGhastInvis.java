package survivaltweaks.infinitefunproject.Mobs.Ghast;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class MakeGhastInvis implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawn = event.getEntity();

        if(spawn instanceof Ghast) {
            Ghast ghast = (Ghast) spawn;
            ghast.setInvisible(true);
            ghast.setCustomName(color("&7&lSpooky Scary Ghost"));
            ghast.setGlowing(true);
        }
    }
}

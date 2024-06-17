package survivaltweaks.infinitefunproject.Mobs.Warden;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MakeInvisible implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawn = event.getEntity();

        if(spawn instanceof Warden) {
            Warden warden = (Warden) spawn;
            warden.setInvisible(true);
            warden.setGlowing(true);
        }
    }
}

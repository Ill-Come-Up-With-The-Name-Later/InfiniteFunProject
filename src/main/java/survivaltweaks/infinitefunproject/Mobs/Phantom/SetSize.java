package survivaltweaks.infinitefunproject.Mobs.Phantom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import survivaltweaks.infinitefunproject.Mobs.OnSpawn;

public class SetSize implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Phantom) {
            Phantom phantom = (Phantom) spawned;

            phantom.setSize(1 + (OnSpawn.getLevel(phantom) / 5));
        }
    }
}

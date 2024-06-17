package survivaltweaks.infinitefunproject.Mobs.IronGolem;

import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.List;

public class SetAggro implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof IronGolem) {
            IronGolem golem = (IronGolem) entity;

            List<Entity> entityList = golem.getNearbyEntities(50, 50 , 50);

            for(Entity e : entityList) {
                if(e instanceof Player) {
                    golem.setTarget((LivingEntity) e);
                    break;
                }
            }
        }
    }
}

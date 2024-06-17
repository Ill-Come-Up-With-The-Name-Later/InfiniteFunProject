package survivaltweaks.infinitefunproject.Mobs.Bat;

import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.World.Infection.InfectionManager.infectEntity;

public class BatDisease implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if(event.getEntity().getType() == EntityType.BAT) {
            if(new Random().nextInt(0, 10) == 1) {
                Bat bat = (Bat) event.getEntity();
                infectEntity(bat);
            }
        }
    }

    @EventHandler
    public void onKilled(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.BAT) {
            ArrayList<Entity> entities =
                    (ArrayList<Entity>) circularNearbyEntities(event.getEntity().getLocation(), 4);

            for(Entity entity : entities) {
                if(entity instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) entity;

                    infectEntity(living);
                }
            }
        }
    }
}

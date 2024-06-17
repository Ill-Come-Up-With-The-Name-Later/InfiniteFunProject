package survivaltweaks.infinitefunproject.Mobs.FarmAnimals;

import org.bukkit.Bukkit;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class Pigs implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Pig) {
            Pig pig = (Pig) entity;

            pig.setCustomName("Dinnerbone");
            pig.addPotionEffect(PotionEffectType.LEVITATION.createEffect(500, 1));

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> pig.getWorld().createExplosion(pig.getLocation(), 1.5f, false, false), 400);
        }
    }
}

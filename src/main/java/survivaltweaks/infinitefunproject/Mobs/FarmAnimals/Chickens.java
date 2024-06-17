package survivaltweaks.infinitefunproject.Mobs.FarmAnimals;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;

public class Chickens implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Chicken) {
            Chicken chicken = (Chicken) entity;

            chicken.addPotionEffect(PotionEffectType.SPEED.createEffect(-1, 9));
        }
    }
}

package survivaltweaks.infinitefunproject.Mobs.Spider;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class GiveSpiderEffects implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Spider) {
            Spider spider = (Spider) entity;
            if(new Random().nextInt(0, 4) == 1) {
                spider.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(-1, 0));
            }
            if(new Random().nextInt(0, 6) == 1) {
                spider.addPotionEffect(PotionEffectType.SPEED.createEffect(-1, 0));
            }
            if(new Random().nextInt(0, 12) == 1) {
                spider.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(-1, 0));
            }
            if(new Random().nextInt(0, 8) == 1) {
                spider.addPotionEffect(PotionEffectType.REGENERATION.createEffect(-1, 0));
            }
        }
    }
}

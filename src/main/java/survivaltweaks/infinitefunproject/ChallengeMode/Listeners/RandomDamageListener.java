package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class RandomDamageListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager.hasMetadata("Lucky Numbers")) {
            event.setDamage(event.getDamage() * new Random().nextDouble(0.1, 10));
        }
    }
}

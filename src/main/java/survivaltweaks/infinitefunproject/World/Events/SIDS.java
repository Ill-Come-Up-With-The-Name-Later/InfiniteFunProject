package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Random;

public class SIDS implements Listener {

    @EventHandler
    public void onBreed(EntityBreedEvent event) {
        LivingEntity offspring = event.getEntity();

        if(new Random().nextInt(0, 30) == 1) {
            offspring.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1 * new Random().nextDouble(0.4, 0.75));
            offspring.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(offspring.getHealth() / 2);

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!offspring.isDead()) {
                    offspring.damage(1000, DamageSource.builder(DamageType.MAGIC).build());
                }
            }, new Random().nextLong(40, 1000));
        }
    }
}

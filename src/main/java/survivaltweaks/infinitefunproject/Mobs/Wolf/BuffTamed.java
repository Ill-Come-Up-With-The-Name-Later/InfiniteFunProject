package survivaltweaks.infinitefunproject.Mobs.Wolf;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class BuffTamed implements Listener {

    @EventHandler
    public void onTame(EntityTameEvent event) {
        LivingEntity entity = event.getEntity();
        AnimalTamer t = event.getOwner();

        if(t instanceof Player) {
            if(entity instanceof Wolf) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    Wolf wolf = (Wolf) entity;

                    wolf.getAttribute(Attribute.MAX_HEALTH).setBaseValue(1500);
                    wolf.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(120);
                    wolf.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(
                            wolf.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue() * 1.33);

                    wolf.setHealth(wolf.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
                }, 1);
            }
        }
    }
}

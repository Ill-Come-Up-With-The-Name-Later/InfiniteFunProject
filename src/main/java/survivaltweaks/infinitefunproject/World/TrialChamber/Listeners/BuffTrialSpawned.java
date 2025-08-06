package survivaltweaks.infinitefunproject.World.TrialChamber.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.TrialSpawnerSpawnEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.AddModifiers.etherealAugmentation;

public class BuffTrialSpawned implements Listener {

    @EventHandler
    public void onSpawn(TrialSpawnerSpawnEvent event) {
        Entity entity = event.getEntity();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                living.getAttribute(Attribute.MAX_HEALTH).setBaseValue(living.getHealth() * 1.2);
                living.setHealth(living.getAttribute(Attribute.MAX_HEALTH).getBaseValue());

                living.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(
                        living.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue() * 1.25);

                etherealAugmentation(living);
            }
        }, 2);
    }
}

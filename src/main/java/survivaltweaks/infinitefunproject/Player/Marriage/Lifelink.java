package survivaltweaks.infinitefunproject.Player.Marriage;

import org.bukkit.Bukkit;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Player.Marriage.MarriageManager.getSpouse;

public class Lifelink implements Listener {

    @EventHandler
    public void onDeath(EntityDamageByEntityEvent event) {
        Entity killed = event.getEntity();

        if(!(killed instanceof LivingEntity)) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
           if(killed.isDead()) {
               LivingEntity entity = (LivingEntity) killed;

               if(getSpouse(entity) != null) {
                    LivingEntity spouse = getSpouse(entity);

                    spouse.damage(Integer.MAX_VALUE, DamageSource.builder(DamageType.MAGIC).build());
               }
           }
        }, 1);
    }
}

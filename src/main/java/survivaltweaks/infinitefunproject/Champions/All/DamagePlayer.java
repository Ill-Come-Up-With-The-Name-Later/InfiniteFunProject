package survivaltweaks.infinitefunproject.Champions.All;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DamagePlayer implements Listener {

    @EventHandler
    public void onHitPlayer(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if(damaged instanceof Player) {
            if(damager.hasMetadata("Champion") && new Random().nextInt(0, 5) == 1) {
                Monster monster = (Monster) damager;

                drawCircle(monster.getLocation(), 1.5, Particle.SOUL_FIRE_FLAME, 90);

                monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(
                        monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 1.01);
                monster.setHealth(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            }
        }
    }

    @EventHandler
    public void onProjectileHitPlayer(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if(damaged instanceof Player) {
            if(damager instanceof Projectile) {
                if(((Projectile) damager).getShooter() instanceof Monster) {
                    Monster m = (Monster) ((Projectile) damager).getShooter();

                    if(m.hasMetadata("Champion") && new Random().nextInt(0, 5) == 1) {
                        Monster monster = (Monster) ((Projectile) damager).getShooter();

                        drawCircle(monster.getLocation(), 1.5, Particle.SOUL_FIRE_FLAME, 90);

                        monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(
                                monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 1.01);
                        monster.setHealth(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    }
                }
            }
        }
    }
}

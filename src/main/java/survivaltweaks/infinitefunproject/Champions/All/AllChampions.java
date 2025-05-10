package survivaltweaks.infinitefunproject.Champions.All;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class AllChampions implements Listener {

    @EventHandler
    public void spawnBackup(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(damaged.hasMetadata("Champion")) {
            if(new Random().nextInt(0, 7) == 1) {
                Monster monster = (Monster) damaged.getWorld().spawnEntity(damaged.getLocation(), damaged.getType());
                monster.setTarget(((Monster) (damaged)).getTarget());
            }
        }
    }

    @EventHandler
    public void awardXP(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(damaged instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) damaged;

                if(living.hasMetadata("Champion")) {
                    if(living.isDead() && damager instanceof Player) {
                        Player player = (Player) damager;

                        player.giveExp(250, true);
                    }
                }
            }
        }, 2);
    }

    public static void buffSameType(Monster monster, double radius) {
        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(monster, radius);
        drawCircle(monster.getLocation(), radius, Particle.SOUL, 90);

        for(Entity entity : nearby) {
            if(entity instanceof Monster && entity.getType() == monster.getType()) {
                Monster m = (Monster) entity;
                if(!m.hasMetadata("Champion")) {
                    if(!m.hasPotionEffect(PotionEffectType.SPEED)) {
                        drawCircle(m.getLocation(), 1.5, Particle.ENCHANTED_HIT, 90);
                        m.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 300, 0, false, false, false));
                        m.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1, false, false, false));
                        m.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1, false, false, false));
                    }
                }
            }
        }
    }

    @EventHandler
    public void dropUpgradeCatalyst(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity.hasMetadata("Champion")) {
            event.getDrops().add(ItemManager.upgradeCatalyst);
        }
    }
}

package survivaltweaks.infinitefunproject.Combat.MultiHits;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.GiveBonusMultiHits;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.Upgrades.PlayerUpgrade;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;
import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.getUpgradeLevel;

public class DealMultiHit implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity damaged = event.getEntity();

        Player damager;

        if(!(damaged instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) damaged;

        if(attacker instanceof Projectile) {
            Projectile projectile = (Projectile) attacker;

            if(projectile.getShooter() instanceof Player) {
                damager = (Player) projectile.getShooter();
            } else {
                return;
            }
        }

        if(attacker instanceof Player) {
            damager = (Player) attacker;
        } else {
            return;
        }

        int chance = getUpgradeLevel(damager, PlayerUpgrade.MULTI_HIT) * 10;
        int multiHits = (int) Math.floor((double) chance / 100) + getExtraMultiHits(damager);

        int finalHitChance = getUpgradeLevel(damager, PlayerUpgrade.MULTI_HIT) % 10 == 0 ? 0 :
                (getUpgradeLevel(damager, PlayerUpgrade.MULTI_HIT) % 10) * 10;

        if(new Random().nextInt(0, 100) < finalHitChance) {
            multiHits++;
        }

        dealMultiHits(damager, entity, multiHits, true, false, event);
    }

    public static void dealMultiHits(Player attacker, LivingEntity attacked, int hits, boolean disableMH, boolean bypass, EntityDamageByEntityEvent event) {
        if(!bypass) {
            if(attacked.hasMetadata("MultiHit")) {
                return;
            }
        }

        if(disableMH) {
            setHasBeenMultiHit(attacked, hits * 17);
        }

        for(int i = 1; i <= hits; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!attacked.isDead()) {
                    attacked.damage(event.getDamage(), attacker);
                    drawExpandingCircle(attacked, 1.1, 4, 0.4, 3, Particle.ENCHANTED_HIT);
                }
            }, 15L * i);
        }
    }

    public static void setHasBeenMultiHit(LivingEntity entity, int duration) {
        entity.setMetadata("MultiHit", new HasBeenMultiHit());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.removeMetadata("MultiHit", InfiniteFunProject.plugin), duration);
    }

    public static int getExtraMultiHits(Player player) {
        int hits = 0;

        if(player.hasMetadata("AddMultiHits")) {
            GiveBonusMultiHits multiHits = (GiveBonusMultiHits) player.getMetadata("AddMultiHits").get(0);

            hits += multiHits.getHits();
        }

        return hits;
    }
}

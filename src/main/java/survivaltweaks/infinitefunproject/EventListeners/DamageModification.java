package survivaltweaks.infinitefunproject.EventListeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.distanceBetween;

public class DamageModification implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();
        Entity hit = event.getEntity();
        LivingEntity damager;

        double damage = event.getDamage();

        if(entity instanceof Projectile) {
            Projectile projectile = (Projectile) entity;
            if(!(projectile.getShooter() instanceof LivingEntity)) {
                return;
            }

            damager = (LivingEntity) projectile.getShooter();
        } else {
            if(entity instanceof LivingEntity) {
                damager = (LivingEntity) entity;
            } else {
                return;
            }
        }

        if(damager == null) {
            return;
        }

        if(damager.hasMetadata("NoDamageModification")) {
            return;
        }

        if(!(damager instanceof Player)) {
            return;
        }

        double dmgModPercent = getDamageModification(distanceBetween(damager, hit), entity);
        double newDamage = Math.max(1, damage * dmgModPercent);

        /*
        damager.sendMessage(ChatColor.GRAY + "Percent of Base Hit: "
                + ChatColor.RED + String.format("%.2f", dmgModPercent * 100) + "%"
                + ChatColor.GRAY + ".\n" + ChatColor.GRAY + "Raw Change: "
                + ChatColor.RED + String.format("%.2f", -(damage - newDamage))
                + ChatColor.GRAY + " damage.\nDistance: "
                + ChatColor.RED + String.format("%.2f", distanceBetween(damager, hit))
                + ChatColor.GRAY + " blocks.");
        */
        event.setDamage(newDamage);
    }

    private double getDamageModification(double distance, Entity damager) {
        if(damager instanceof Projectile) {
            return Math.min(Math.max(-(0.000019 * Math.pow(distance, 3)) + 1.5, 0.75), 1.5);
        }

        return Math.min(Math.max(-(0.005 * Math.pow(distance, 4)) + 1.5, 0.5), 1.33);
    }
}

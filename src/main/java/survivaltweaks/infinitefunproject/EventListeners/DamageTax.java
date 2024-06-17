package survivaltweaks.infinitefunproject.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.TaxExemptMetadata;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;
import static survivaltweaks.infinitefunproject.Player.Events.PlayerJoin.getBaseDamage;

public class DamageTax implements Listener {

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource source = event.getEntity().getShooter();

        if(source instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) source;

            if(entity.hasMetadata("TaxExempt")) {
                projectile.setMetadata("TaxExempt", new TaxExemptMetadata());
            }
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        double originalDamage = event.getDamage();

        if(damager.hasMetadata("TaxExempt") || WorldModInit.modifierActive(WorldModifier.TAX_BREAK)) {
            return;
        }

        if(!(damaged instanceof LivingEntity)) {
            return;
        }

        double taxedDamage = getTaxedDamage(originalDamage);
        event.setDamage(Math.max(originalDamage - taxedDamage, getBaseDamage()));

        String taxMessage = ChatColor.GRAY + "Deduction from taxes: " + ChatColor.RED + String.format("%.2f", taxedDamage)
                + ChatColor.GRAY + " damage.";

        if(damager instanceof Projectile) {
            if(((Projectile) damager).getShooter() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) ((Projectile) damager).getShooter();

                if(taxedDamage > 0) {
                    entity.sendMessage(taxMessage);
                }
            }
        }

        if(damager instanceof LivingEntity && taxedDamage > 0) {
            damager.sendMessage(taxMessage);
        }
    }

    public static double getTaxedDamage(double damage) {
        int level = getLevel();
        double taxedDamage = 0;

        int doubleLevel = level * 2;
        int tripleLevel = level * 3;
        int quadLevel = level * 4;
        int fiveLevel = level * 5;
        int nineLevel = level * 9;
        int tenLevel = level * 10;

        if(damage <= level) {
            taxedDamage += (double) level / 20;
        }
        if(damage > level) {
            taxedDamage += (double) level / 3;
        }
        if(damage > doubleLevel) {
            taxedDamage += (double) level * 0.8;
        }
        if(damage > tripleLevel) {
            taxedDamage += level + (damage * 0.05);
        }
        if(damage > quadLevel) {
            taxedDamage += doubleLevel + (damage * 0.1);
        }
        if(damage > fiveLevel) {
            taxedDamage += tripleLevel + (damage * 0.2);
        }
        if(damage > tenLevel) {
            taxedDamage += nineLevel + (damage * 0.5);
        }

        if(WorldModInit.modifierActive(WorldModifier.TAXMAN)) {
            taxedDamage = damage * 0.95;
        }

        return taxedDamage;
    }
}

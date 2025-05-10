package survivaltweaks.infinitefunproject.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;
import survivaltweaks.infinitefunproject.Player.Upgrades.PlayerUpgrade;

import static survivaltweaks.infinitefunproject.Player.Events.PlayerJoin.getBaseDamage;
import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.getUpgradeLevel;

public class DamageTax implements Listener {

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

        double taxedDamage = getTaxedDamage(originalDamage, damager);

        if(damager.hasMetadata("RandomDamage")) {
            taxedDamage /= 2;
        }

        event.setDamage(Math.max(originalDamage - taxedDamage, getBaseDamage()));
        double finalTaxedDamage = taxedDamage;

        String taxMessage = ChatColor.GRAY + "Deduction from taxes: " + ChatColor.RED + String.format("%.2f", finalTaxedDamage)
                + ChatColor.GRAY + " damage.";

        if(damager instanceof Projectile) {
            if(((Projectile) damager).getShooter() instanceof LivingEntity) {
                damager = (LivingEntity) ((Projectile) damager).getShooter();
            }
        }

        /*
        if(damager instanceof LivingEntity && finalTaxedDamage > 0.07) {
            damager.sendMessage(taxMessage);
        }
         */
    }

    public static double getTaxedDamage(double damage, Entity entity) {
        if(entity instanceof Player) {
            damage -= ((double) getUpgradeLevel((Player) entity, PlayerUpgrade.DAMAGE) * 0.7);
        }

        if(WorldModInit.modifierActive(WorldModifier.TAXMAN)) {
            return damage * 0.95;
        }

        double baseFormula = 16 * Math.sqrt(Math.max((0.4 * damage) - 47, 0));
        double tanCurve = tanCurve(damage) * damage;
        double fullFormula = baseFormula + tanCurve;

        if(entity instanceof Enemy && !(entity instanceof Boss)) {
            return Math.min(fullFormula * 2, 0.85 * damage);
        }

        return Math.min(fullFormula, 0.75 * damage);
    }

    public static double tanCurve(double damage) {
        return Math.tanh(0.000000025 * damage);
    }
}

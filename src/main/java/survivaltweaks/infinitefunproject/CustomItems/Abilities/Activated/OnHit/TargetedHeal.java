package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class TargetedHeal implements AttackAbility {
    @Override
    public void activate(Player player, LivingEntity entity) {
        entity.setHealth(Math.min(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue(), entity.getHealth() + 8));

        drawCircle(entity.getLocation(), 1.3, Particle.HAPPY_VILLAGER, 90);
        entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation().add(new Vector(0, 1, 0)),
                8, 0.2, 0.2, 0.2, 0.03);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        event.setDamage(0);
        event.setCancelled(true);
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Heals the entity you hit.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

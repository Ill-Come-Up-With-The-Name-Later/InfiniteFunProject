package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class MagicLifesteal implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        double damage = event.getDamage();

        drawCircle(player, 1.1, Particle.SCULK_SOUL, 45);
        player.setHealth(Math.min(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue(), player.getHealth() + (damage * 0.1)));
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Gain back 10% of the damage\nyou deal as health.";
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

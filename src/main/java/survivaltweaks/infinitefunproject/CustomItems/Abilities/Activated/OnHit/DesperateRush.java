package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

public class DesperateRush implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {}

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        if(player.getHealth() < player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) {
            event.setDamage(event.getDamage() * 1.4);
        }
    }

    @Override
    public void activate(Player player) {}

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Deal 40% more damage when\nbelow half health.";
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

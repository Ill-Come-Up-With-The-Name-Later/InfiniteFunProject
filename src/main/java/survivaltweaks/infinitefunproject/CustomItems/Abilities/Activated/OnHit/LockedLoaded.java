package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

public class LockedLoaded implements AttackAbility {

    @Override
    public void activate(Player player) {}

    @Override
    public void activate(Player player, LivingEntity entity) {}

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Headshots deal 2x damage and\ncreate an area of effect that\ndamages other enemies.";
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

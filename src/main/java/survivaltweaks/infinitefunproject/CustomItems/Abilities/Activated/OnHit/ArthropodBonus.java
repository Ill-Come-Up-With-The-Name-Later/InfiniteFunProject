package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

public class ArthropodBonus implements AttackAbility {
    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof Spider || damaged instanceof Silverfish || damaged instanceof Endermite) {
            event.setDamage(event.getDamage() * 1.66);
        }
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
        return "Deal 66% more damage to\narthropods.";
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

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.doMiniCrit;

public class DirectHitDamageBoost implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if(!entity.isOnGround()) {
            doMiniCrit(event);
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
        return "Mini-Crits airborne targets.";
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

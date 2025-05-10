package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnAttackedAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.applyDOT;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;

public class FireThorns implements OnAttackedAbility {

    @Override
    public void activate(Player player, LivingEntity attacker) {
        applyDOT(player, attacker, 40, 6, 15);
        drawExpandingCircle(attacker, 1.2, 6, 0, 15, Particle.FLAME);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Sets enemies alight\nwhen they attack.";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}

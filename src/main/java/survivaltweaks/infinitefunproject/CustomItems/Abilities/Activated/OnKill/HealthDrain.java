package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;

public class HealthDrain implements KillAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        player.setHealth(Math.min(player.getHealth() + entity.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.1,
                player.getAttribute(Attribute.MAX_HEALTH).getBaseValue()));

        drawExpandingCircle(player.getLocation(), 1, 3, 0.75, 10, Particle.FLAME);
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Heal 10% of the slain entity's\nhealth.";
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

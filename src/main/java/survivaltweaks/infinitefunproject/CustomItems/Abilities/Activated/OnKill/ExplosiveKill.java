package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;

public class ExplosiveKill implements KillAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        drawExpandingCircle(entity.getLocation(), 2, 4, 0.75, 6, Particle.FLAME);
        entity.getWorld().createExplosion(entity.getLocation(), 5.5f, false, false, player);
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
        return "Enemies explode on death.";
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

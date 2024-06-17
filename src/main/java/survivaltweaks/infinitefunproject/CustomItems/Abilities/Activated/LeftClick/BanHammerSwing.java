package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class BanHammerSwing implements ActivatedAbility {
    double radius = 4.5;

    @Override
    public void activate(Player player) {
        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        drawCircle(player.getLocation(), radius, Particle.CRIT_MAGIC, 90);
        drawCircle(player.getLocation(), radius, Particle.SPELL_WITCH, 90);
        drawCircle(player.getLocation(), radius, Particle.SWEEP_ATTACK, 45);

        for (Entity entity : nearby) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                living.damage(30, player);

                living.setVelocity(living.getLocation().getDirection().multiply(-1).multiply(2));
            }
        }
    }

    @Override
    public int getCooldown() {
        return 25;
    }

    @Override
    public String getDescription() {
        return "Swing your hammer around,\ndealing massive damage.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

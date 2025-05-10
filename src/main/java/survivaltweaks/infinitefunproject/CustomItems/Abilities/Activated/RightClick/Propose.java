package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createRay;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;
import static survivaltweaks.infinitefunproject.Player.Marriage.MarriageManager.marry;

public class Propose implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.HEART);
                add(Particle.HAPPY_VILLAGER);
            }
        };

        drawCircle(player.getLocation(), 1.1, Particle.HEART, 90);

        ArrayList<LivingEntity> entities = createRay(player, 5, false, particles);
        Optional<LivingEntity> marriageTarget = entities.stream().min(Comparator.comparing(x ->
                x.getLocation().distanceSquared(player.getLocation())));

        if(marriageTarget.isPresent()) {
            LivingEntity entity = marriageTarget.get();

            drawCircle(entity.getLocation(), 1.1, Particle.HEART, 90);

            marry(player, entity);
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Will you marry me?";
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

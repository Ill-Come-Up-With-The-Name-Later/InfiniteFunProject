package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ArrowFlurry implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        player.setVelocity(player.getVelocity().multiply(6).setY((player.getVelocity().getY() / 6) + 0.95));
        drawCircle(player, 1.1, Particle.ANGRY_VILLAGER, 45);

        ArrayList<LivingEntity> nearby = nearbyLivingEntities(player, 24);

        for(LivingEntity living : nearby) {
            if(living.isDead() || living.getTicksLived() < 15) {
                continue;
            }

            if(!living.hasMetadata("BeenHit")) {
                if(player.hasLineOfSight(living) && !living.equals(player)) {
                    Arrow arrow = player.launchProjectile(Arrow.class);
                    arrow.setVelocity(living.getBoundingBox().getCenter().subtract(player.getBoundingBox().getCenter()));
                    arrow.setDamage(27);
                    arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
                    arrow.setGlowing(true);

                    arrow.setVelocity(arrow.getVelocity().multiply(0.7));

                    //setHasBeenHit(living, 40);
                    living.setNoDamageTicks(3);
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 480;
    }

    @Override
    public String getDescription() {
        return "Throws you into the air and\nlaunches arrows at nearby\nentities.";
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

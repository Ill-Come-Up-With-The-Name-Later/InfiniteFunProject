package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class InterceptMeteors implements ActivatedAbility {

    final int maxIntercepts = 12;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> missileTrail = new ArrayList<>() {
            {
                add(Particle.SMOKE);
                add(Particle.CRIT);
                add(Particle.FLAME);
            }
        };

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 70);
        int intercepts = 0;

        for(Entity entity : entities) {
            if(intercepts >= maxIntercepts) {
                return;
            }

            if(entity.hasMetadata("Meteor")) {
                Arrow missile = player.launchProjectile(Arrow.class);
                missile.setRotation(missile.getYaw(), 90);
                missile.getLocation().add(new Vector(0, 5, 0));

                faceEntityToAnother(missile, entity);
                createRay(missile, (int) (distanceBetween(missile, entity) + 1), false, missileTrail);

                missile.setVelocity(entity.getLocation().toVector().subtract(missile.getLocation().toVector()));
                missile.setInvisible(true);

                player.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, entity.getLocation(), 1, 0.1, 0.1, 0.1, 0.02);
                player.getWorld().createExplosion(entity.getLocation(), 1, true, true);

                entity.remove();
                missile.remove();

                if(new Random().nextInt(0, 10) == 1) {
                    Item item = player.getWorld().spawn(entity.getLocation(), Item.class);
                    item.setItemStack(ItemManager.meteorFragment);
                    item.setGlowing(true);
                    item.setInvulnerable(true);
                }

                intercepts++;
            }
        }
    }

    @Override
    public int getCooldown() {
        return 80;
    }

    @Override
    public String getDescription() {
        return "Intercepts up to " + maxIntercepts + " incoming\nmeteors.";
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

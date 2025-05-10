package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class Supernova implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 40);

        player.getWorld().spawnParticle(Particle.EXPLOSION, player.getLocation(), 9, 0.3, 0.3, 0.3, 32);

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                drawCircle(living.getLocation(), 1, Particle.FLAME, 90);
                living.damage(2_000_000_000, player);
            }

            if(entity instanceof Item) {
                Item item = (Item) entity;

                drawCircle(item.getLocation(), 1, Particle.FLAME, 90);
                item.remove();
            }
        }

        player.damage(2_000_000_000, DamageSource.builder(DamageType.ON_FIRE).build());
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Explodes, instantly killing\nnearby enemies and the\nuser.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ImpalingAbility implements AttackAbility {
    int range = 10;

    @Override
    public void activate(Player player) {}

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @Override
    public void activate(Player player, LivingEntity entity) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(entity.hasMetadata("BeenHit")) {
                return;
            }

            if(!entity.isDead()) {
                entity.setNoDamageTicks(0);
            }

            if(player.isInWater()) {
                ArrayList<Particle> particles = new ArrayList<>() {
                    {
                        add(Particle.WATER_BUBBLE);
                        add(Particle.WATER_WAKE);
                        add(Particle.CRIT_MAGIC);
                        add(Particle.END_ROD);
                    }
                };

                for(int i = 1; i <= 5; i++) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        HashMap<LivingEntity, Boolean> entities =
                                createSpreadDamageRay(player, 0.75, range, 25, 1000,
                                        true, true, false, particles, false);

                        entities.keySet().forEach(x -> setHasBeenHit(x, 4));
                    }, i * 5);
                }
            }
        }, 1);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 30;
    }

    @Override
    public String getDescription() {
        return "If you are in the water, slash\nyour trident forward multiple\ntimes.\n\nCan destroy projectile shields.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}

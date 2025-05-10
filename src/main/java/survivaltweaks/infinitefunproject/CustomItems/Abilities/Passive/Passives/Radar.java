package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class Radar implements Passive {
    int searchRadius = 16;

    @Override
    public void activate(Player player) {
        for(int i = 1; i < searchRadius; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, finalI);
                drawCircle(player.getLocation(), finalI, Particle.ENCHANTED_HIT, 90);

                for(Entity entity : entities) {
                    if(entity instanceof LivingEntity && !entity.equals(player)) {
                        if (!((LivingEntity) entity).hasPotionEffect(PotionEffectType.GLOWING)) {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(
                                    PotionEffectType.GLOWING, 100, 0, false, false, false));
                        }
                    }
                }
            }, i * 3L);
        }
    }

    @Override
    public int getCooldown() {
        return 110;
    }

    @Override
    public String getDescription() {
        return "Highlights entities in a " + searchRadius + " block\ndistance";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

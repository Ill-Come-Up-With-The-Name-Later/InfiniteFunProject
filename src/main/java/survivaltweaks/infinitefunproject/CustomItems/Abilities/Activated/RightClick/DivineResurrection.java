package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DivineResurrection implements ActivatedAbility {
    int radius = 12;

    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), radius, Particle.HAPPY_VILLAGER, 90);
        drawCircle(player.getLocation(), radius, Particle.FIREWORK, 90);

        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);
        player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 4, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 0, false, false, false));

        for(Entity entity : nearby) {
            if(entity instanceof Player) {
                Player other = (Player) entity;

                other.setHealth(other.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
                other.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 4, false, false, false));
                other.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 0, false, false, false));
            }
        }
    }

    @Override
    public int getCooldown() {
        return 1200;
    }

    @Override
    public String getDescription() {
        return "Fully heals nearby players and\ngrants a large absorption shield.";
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

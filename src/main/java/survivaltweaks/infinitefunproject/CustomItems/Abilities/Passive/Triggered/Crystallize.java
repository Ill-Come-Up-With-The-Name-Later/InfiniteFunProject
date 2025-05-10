package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Triggered;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggerAbility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class Crystallize implements TriggerAbility {

    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), 1.4, Particle.HAPPY_VILLAGER, 90);

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 10);
        Optional<Entity> lowestPlayer = entities.stream().filter(x -> x instanceof Player)
                .min(Comparator.comparing(x -> ((Player) x).getHealth()));

        if(lowestPlayer.isPresent()) {
            if(lowestPlayer.equals(player)) {
                player.setHealth(Math.min(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), player.getHealth() + 20));
            } else {
                Player other = (Player) lowestPlayer.get();
                other.setHealth(Math.min(other.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), other.getHealth() + 25));

                drawCircle(other.getLocation(), 1.4, Particle.HAPPY_VILLAGER, 90);
            }
            return;
        }
        player.setHealth(Math.min(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), player.getHealth() + 20));
    }

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "Headshotting enemies heals you for\n20 health and the lowest health\nplayer nearby for 25 health.\n" +
                "\nIf that is you, heal for 20 health.";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

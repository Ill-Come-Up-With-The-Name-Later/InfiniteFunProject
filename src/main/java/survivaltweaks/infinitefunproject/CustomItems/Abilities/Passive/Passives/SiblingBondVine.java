package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class SiblingBondVine implements Passive {
    int radius = 10;

    @Override
    public void activate(Player player) {
        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        for(Entity entity : nearby) {
            if(entity instanceof Player) {
                Player other = (Player) entity;

                if(other.getInventory().contains(ItemManager.shuriken)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20, 0, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 20, 0, false, false, false));
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Grants a small boost if a shuriken\nis nearby.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

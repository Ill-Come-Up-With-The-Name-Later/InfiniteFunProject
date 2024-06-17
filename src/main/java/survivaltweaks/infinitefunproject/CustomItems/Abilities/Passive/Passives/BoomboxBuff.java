package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.BoomboxRadial.getCharges;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.BoomboxRadial.setCharges;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class BoomboxBuff implements Passive {
    @Override
    public void activate(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.hasItemMeta()) {
            if(item.getItemMeta().getDisplayName().equals(ItemManager.boombox.getItemMeta().getDisplayName())) {
                drawCircle(player.getLocation(), 6, Particle.NOTE, 90);
                drawCircle(player.getLocation(), 6, Particle.VILLAGER_HAPPY, 90);

                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 0, false, false, false));

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, 6);

                for(Entity entity : nearby) {
                    if(entity instanceof Player) {
                        Player other = (Player) entity;

                        other.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0, false, false, false));
                        other.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 0, false, false, false));
                    }
                }

                setCharges(player, getCharges(player) + 1);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 180;
    }

    @Override
    public String getDescription() {
        return "Buffs you and nearby players\nfor 4 seconds with Strength\nand Speed I.";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

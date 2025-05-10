package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class DualSwing implements ActivatedAbility {
    int range = 5;

    @Override
    public void activate(Player player) {
        ItemStack item = player.getInventory().getItemInOffHand();
        if(item.getType() == Material.AIR) {
            return;
        }

        if(item.equals(ItemManager.biograftSword)) {
            ArrayList<Particle> particles = new ArrayList<>() {
                {
                    add(Particle.SWEEP_ATTACK);
                    add(Particle.WITCH);
                    add(Particle.ENCHANTED_HIT);
                }
            };

            createDamageRay(player, range, 18, 1000, true, false, false, particles,
                    false);
        }
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Swing your blades and\nhit enemies in front of you.";
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

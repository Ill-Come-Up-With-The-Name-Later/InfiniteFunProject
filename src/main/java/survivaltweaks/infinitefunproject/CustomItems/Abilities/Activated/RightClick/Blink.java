package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createRay;

public class Blink implements ActivatedAbility {
    int range = 10;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SPELL_WITCH);
                add(Particle.CRIT_MAGIC);
            }
        };

        ItemStack item = player.getInventory().getItemInOffHand();

        if(item.getType() == Material.AIR) {
            return;
        }

        if(item.equals(ItemManager.biograftSword)) {
            ArrayList<LivingEntity> entities = createRay(player, range, true, particles);

            for(LivingEntity entity : entities) {
                entity.damage(20, player);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Teleport forward and damage\nentities.";
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

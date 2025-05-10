package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

public class BreadSlice implements KillAbility {
    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player) {
        player.getInventory().addItem(new ItemStack(Material.BREAD, 1));
    }

    @Override
    public int getCooldown() {
        return 150;
    }

    @Override
    public String getDescription() {
        return "Gives you a slice of bread.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

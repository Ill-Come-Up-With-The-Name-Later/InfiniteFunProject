package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RCAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;

public class SwapGunToScythe implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> player.getInventory().setItemInMainHand(ItemManager.scythe), 1);

        setCooldown(player, RCAbility.SWAP_SCYTHE_TO_GUN, 1);
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Converts your gun into\na high-damage scythe.";
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

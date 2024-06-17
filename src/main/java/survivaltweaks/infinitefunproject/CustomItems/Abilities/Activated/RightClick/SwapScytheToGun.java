package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RCAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;

public class SwapScytheToGun implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> player.getInventory().setItemInMainHand(ItemManager.gun), 1);

        setCooldown(player, RCAbility.SWAP_GUN_TO_SCYTHE, 1);
    }

    @Override
    public int getCooldown() { return 50; }

    @Override
    public String getDescription() {
        return "Converts your scythe into\na fast firing gun.";
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

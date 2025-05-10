package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class Medication implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        if(player.hasMetadata("Cancer")) {
            player.removeMetadata("Cancer", InfiniteFunProject.plugin);
            player.sendMessage(ChatColor.GREEN + "Your super lung cancer is in remission!");
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 400, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 400, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 400, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1, false, false, false));
    }

    @Override
    public int getCooldown() {
        return 2000;
    }

    @Override
    public String getDescription() {
        return "Cures super lung cancer,\nsomehow...";
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

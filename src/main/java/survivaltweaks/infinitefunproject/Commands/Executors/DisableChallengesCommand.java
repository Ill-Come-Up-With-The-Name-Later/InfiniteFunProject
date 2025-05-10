package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.ChallengeMode.ChallengeMode;

import static survivaltweaks.infinitefunproject.ChallengeMode.ChallengeModeManager.save;
import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.applyUpgrades;

public class DisableChallengesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        for(ChallengeMode mode : ChallengeMode.values()) {
            if(player.hasMetadata(mode.getDataString())) {
                player.removeMetadata(mode.getDataString(), InfiniteFunProject.plugin);
                save(player, mode);
            }
        }

        applyUpgrades(player);
        player.sendMessage(ChatColor.GREEN + "Deactivated challenges.");

        return true;
    }
}

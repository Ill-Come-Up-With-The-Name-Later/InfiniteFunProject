package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeMode;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.playerCooldowns;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager.applyChallenges;
import static survivaltweaks.infinitefunproject.StatusMeters.StatusInit.setupMeters;

public class PlayerJoin implements Listener {

    public static double baseDamage = 2.0;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            player.getInventory().addItem(ItemManager.bat);
            player.getInventory().addItem(ItemManager.challengeModeBrowser);
        }

        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDamage);
        player.sendTitle(color("&a&lWelcome to: Project Infinite Fun!"), color("&c&l&kZ &aGood Luck! &c&l&kZ"), 20, 40, 20);

        setupMeters(player);
        InitUpgrades.loadData(player);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            applyChallenges(player);
        }, 2);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Cancer")) {
            player.removeMetadata("Cancer", InfiniteFunProject.plugin);
        }

        if(player.hasMetadata("ProjShield")) {
            player.removeMetadata("ProjShield", InfiniteFunProject.plugin);
        }

        if(player.isInvulnerable()) {
            player.setInvulnerable(false);
        }

        InitUpgrades.saveData(player);

        for(ChallengeMode mode : ChallengeMode.values()) {
            ChallengeModeManager.save(player, mode);
        }

        playerCooldowns.remove(player);
    }

    public static double getBaseDamage() {
        return baseDamage;
    }
}

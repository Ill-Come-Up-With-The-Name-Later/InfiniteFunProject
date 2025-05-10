package survivaltweaks.infinitefunproject.Player.Upgrades.Upgrade;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.Upgrades.Metadata.DoubleJumpMeta;
import survivaltweaks.infinitefunproject.Player.Upgrades.PlayerUpgrade;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.hasCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility.triggerAbility;
import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.getUpgradeLevel;

public class DoubleJump implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            Player player = event.getPlayer();

            if(getUpgradeLevel(player, PlayerUpgrade.DOUBLE_JUMP) > 0) {
                player.setAllowFlight(true);
                player.setMetadata("DoubleJump", new DoubleJumpMeta());
            }
        }, 2);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
          if(getUpgradeLevel(player, PlayerUpgrade.DOUBLE_JUMP) > 0) {
              if(!player.hasMetadata("DoubleJump")) {
                  player.setMetadata("DoubleJump", new DoubleJumpMeta());
              }
              player.setAllowFlight(true);
          }
        }, 4);
    }

    @EventHandler
    public void onGameModeSwitch(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            GameMode gameMode = player.getGameMode();

            if(!(gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR)) {
                if(player.hasMetadata("DoubleJump")) {
                    player.setAllowFlight(true);
                }
            }
        }, 2);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if(hasCooldown(player, TriggeredAbility.DOUBLE_JUMP)) {
            event.setCancelled(true);
            return;
        }

        if(player.hasMetadata("DoubleJump") && !(hasCooldown(player, TriggeredAbility.DOUBLE_JUMP) ||
                (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR))) {
            event.setCancelled(true);
            Block block = player.getWorld().getBlockAt(player.getLocation().subtract(0, 2, 0));

            if(!(block.getType() == Material.AIR)) {
                Vector v = player.getLocation().getDirection().multiply(0.5).setY(0.5);
                player.setVelocity(v);

                triggerAbility(player, TriggeredAbility.DOUBLE_JUMP);
                player.setAllowFlight(false);

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(player.hasMetadata("DoubleJump")) {
                        player.setAllowFlight(true);
                    }
                }, (long) TriggeredAbility.DOUBLE_JUMP.getCooldown());
            }
        }
    }
}

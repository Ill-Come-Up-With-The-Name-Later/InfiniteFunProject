package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class MakePlayersInvincible implements RandomEvent {
    int duration = 200;

    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            grantInvulnerability(player, duration);
        }
    }

    public static void grantInvulnerability(LivingEntity entity, int duration) {
        entity.setInvulnerable(true);
        entity.sendMessage(ChatColor.GREEN + "You are now invulnerable!");

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            entity.setInvulnerable(false);
            entity.sendMessage(ChatColor.RED + "You are no longer invulnerable!");
        }, duration);
    }
}

package survivaltweaks.infinitefunproject.StatusMeters;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit;
import survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit;
import survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit;

import static survivaltweaks.infinitefunproject.Player.Weight.WeightManager.applyWeightEffects;
import static survivaltweaks.infinitefunproject.Player.Weight.WeightManager.calculateWeight;
import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.getCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.setupCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.getHeat;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.setupHeat;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.getThirst;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.setupThirst;

public class StatusInit {

    public static void init() {
        ThirstInit.init();
        HeatInit.init();
        ColdInit.init();

        for(Player player : Bukkit.getOnlinePlayers()) {
            setupMeters(player);
        }
    }

    public static void setupMeters(Player player) {
        setupThirst(player);
        setupCold(player);
        setupHeat(player);

        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin,
                () -> {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        new TextComponent("Thirst: " + ChatColor.BLUE + getThirst(player) +
                                ChatColor.RESET + "   |   Heat: " + ChatColor.RED + getHeat(player) + ChatColor.RESET + "   |   Cold: " +
                                ChatColor.AQUA + getCold(player) + ChatColor.RESET + "   |   Weight: " + ChatColor.GOLD +
                                String.format("%.2f", calculateWeight(player)) + ChatColor.RESET + " lbs."));

                    applyWeightEffects(player);
            }, 1, 1);
    }
}

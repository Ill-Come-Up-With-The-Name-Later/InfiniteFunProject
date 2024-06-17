package survivaltweaks.infinitefunproject.CustomItems.Abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivateAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.FallenStar;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.RejuvenationRing;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.Bleach;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.Flashbang;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.ActivatePassive;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives.CallistoArrows;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

import java.util.HashMap;

public class InitAbilities {

    public static HashMap<Player, HashMap<Ability, Integer>> playerCooldowns = new HashMap<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ActivateAbility(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ActivatePassive(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new Bleach(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new Flashbang(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new FallenStar(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new RejuvenationRing(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new CallistoArrows(), InfiniteFunProject.plugin);

        ActivatePassive.activateIntervalAbilities();
    }

    public static void reset() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            playerCooldowns.remove(player);
        }
    }

    public static void setCooldown(Player player, Ability ability, int cd) {
        if(playerCooldowns.containsKey(player)) {
            playerCooldowns.get(player).put(ability, cd);
        } else {
            playerCooldowns.put(player, new HashMap<>());
            playerCooldowns.get(player).put(ability, cd);
        }

        tickCooldown(player, ability);
    }

    private static void tickCooldown(Player player, Ability ability) {
        new BukkitRunnable() {
            @Override
            public void run() {
                int newCd = getCooldown(player, ability) - 1;

                if(newCd < 0) {
                    playerCooldowns.get(player).remove(ability);
                    cancel();
                    return;
                }

                if(playerCooldowns.get(player).containsKey(ability)) {
                    playerCooldowns.get(player).put(ability, newCd);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    public static Integer getCooldown(Player player, Ability ability) {
        if(playerCooldowns.containsKey(player)) {
            if(playerCooldowns.get(player) == null) {
                return 0;
            }
            if(playerCooldowns.get(player).containsKey(ability)) {
                return playerCooldowns.get(player).get(ability);
            }
            return 0;
        }

        return 0;
    }

    public static boolean hasCooldown(Player player, Ability ability) {
        if(!playerCooldowns.containsKey(player)) return false;
        if(playerCooldowns.get(player) == null) return false;
        if(!playerCooldowns.get(player).containsKey(ability)) return false;
        if(playerCooldowns.get(player).get(ability) == null) return false;

        return getCooldown(player, ability) > 0 || !playerCooldowns.get(player).containsKey(ability);
    }
}

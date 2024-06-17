package survivaltweaks.infinitefunproject.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static survivaltweaks.infinitefunproject.Champions.ChampionInit.makeChampion;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class SpawnChampionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 1) {
            player.sendMessage(ChatColor.RED + "An entity type must be provided");
            return false;
        }

        String entityType = strings[0];

        for(EntityType type : EntityType.values()) {
            if(entityType.equalsIgnoreCase(type.toString())) {
                EntityType typeToSpawn = type;

                Entity spawned = player.getWorld().spawnEntity(player.getLocation().add(1, 0, 1),
                        typeToSpawn);

                if(!(spawned instanceof Monster)) {
                    spawned.remove();
                    player.sendMessage(ChatColor.RED + "Entity type not a monster");
                    return true;
                }

                makeChampion((Monster) spawned);
                player.sendMessage(ChatColor.GREEN + fixCaps(typeToSpawn.toString()) + " Champion spawned");
                return true;
            }
        }

        player.sendMessage(ChatColor.RED + "Could not find entity type");
        return true;
    }
}

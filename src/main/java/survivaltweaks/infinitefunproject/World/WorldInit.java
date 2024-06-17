package survivaltweaks.infinitefunproject.World;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.World.Events.CoalMining;
import survivaltweaks.infinitefunproject.World.Events.DestroyProjectiles;
import survivaltweaks.infinitefunproject.World.Events.PlayerJoinsEvent;
import survivaltweaks.infinitefunproject.World.Events.SpawnTermites;
import survivaltweaks.infinitefunproject.World.Infection.InfectionManager;
import survivaltweaks.infinitefunproject.World.PillagerRaid.AddExtraEnemies;

public class WorldInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinsEvent(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnTermites(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DestroyProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AddExtraEnemies(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new CoalMining(), InfiniteFunProject.plugin);

        InfectionManager.init();
    }
}

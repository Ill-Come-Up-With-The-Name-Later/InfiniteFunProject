package survivaltweaks.infinitefunproject.World;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.World.Events.*;
import survivaltweaks.infinitefunproject.World.Infection.InfectionManager;
import survivaltweaks.infinitefunproject.World.PillagerRaid.AddExtraEnemies;
import survivaltweaks.infinitefunproject.World.TrialChamber.InitTrial;
import survivaltweaks.infinitefunproject.World.Village.BlackMarketTrader;
import survivaltweaks.infinitefunproject.World.Village.SuperTrader;

public class WorldInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinsEvent(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnTermites(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DestroyProjectiles(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AddExtraEnemies(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new CoalMining(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SIDS(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SuperTrader(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BlackMarketTrader(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ObtainSpecialDirt(), InfiniteFunProject.plugin);

        InfectionManager.init();
        InitTrial.init();
    }
}

package survivaltweaks.infinitefunproject.Noise;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Noise.Events.PlayerMakesNoise;

public class NoiseInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMakesNoise(), InfiniteFunProject.plugin);
    }
}

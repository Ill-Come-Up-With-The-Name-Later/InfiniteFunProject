package survivaltweaks.infinitefunproject.Player.ChallengeMode;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.UI.ChallengeModeUI;

public class InitListeners {

    public static void init() {
        for(ChallengeMode mode : ChallengeMode.values()) {
            Bukkit.getServer().getPluginManager().registerEvents(mode.getModeListener(), InfiniteFunProject.plugin);
        }

        Bukkit.getServer().getPluginManager().registerEvents(new ChallengeModeUI(), InfiniteFunProject.plugin);
    }
}

package survivaltweaks.infinitefunproject.ChallengeMode;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.io.File;
import java.util.UUID;

public class ChallengeModeManager {

    public static void init() {
        InitListeners.init();

        for(Player player : Bukkit.getOnlinePlayers()) {
            applyChallenges(player);
        }
    }

    public static void save(Player player, ChallengeMode mode) {
        UUID uuid = player.getUniqueId();

        try {
            if(!InfiniteFunProject.plugin.getDataFolder().exists()) {
                System.out.println("Creating data folder");
                InfiniteFunProject.plugin.getDataFolder().mkdir();
            }
            File file = new File(InfiniteFunProject.plugin.getDataFolder(), File.separator  + mode.getName() + ".yml");

            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if(!file.exists()) {
                System.out.println("Created new file for special mode.");
                file.createNewFile();
            }

            config.createSection("mode");

            if(player.hasMetadata(mode.getDataString())) {
                config.set("mode." + uuid, true);
            } else {
                config.set("mode." + uuid, false);
            }

            config.save(file);
        } catch (Exception ignored) {}
    }

    public static boolean load(Player player, ChallengeMode mode) {
        UUID uuid = player.getUniqueId();

        try {
            if(!InfiniteFunProject.plugin.getDataFolder().exists()) {
                System.out.println("Creating data folder");
                InfiniteFunProject.plugin.getDataFolder().mkdir();
            }
            File file = new File(InfiniteFunProject.plugin.getDataFolder(), File.separator + mode.getName() + ".yml");

            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if(!file.exists()) {
                System.out.println("Created new file for special mode.");
                file.createNewFile();
            }

            if(config.get("mode." + uuid) == null) {
                return false;
            }

            config.save(file);
            return config.getBoolean("mode." + uuid);
        } catch (Exception ignored) {}

        return false;
    }

    public static void applyChallenges(Player player) {
        for(ChallengeMode challengeMode : ChallengeMode.values()) {
            if(load(player, challengeMode)) {
                challengeMode.getManager().apply(player);
            }
        }
    }

    public static boolean challengeActive(Player player, ChallengeMode mode) {
        return load(player, mode);
    }
}

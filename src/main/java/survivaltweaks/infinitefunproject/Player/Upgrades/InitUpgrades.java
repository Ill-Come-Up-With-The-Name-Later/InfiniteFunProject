package survivaltweaks.infinitefunproject.Player.Upgrades;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.Events.PlayerJoin;
import survivaltweaks.infinitefunproject.Player.Upgrades.Metadata.DoubleJumpMeta;
import survivaltweaks.infinitefunproject.Player.Upgrades.Upgrade.DoubleJump;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class InitUpgrades {

    public static HashMap<Player, HashMap<PlayerUpgrade, Integer>> upgrades = new HashMap<>();

    public static int damageUpgradeCost = 35;
    public static int armorUpgradeCost = 35;
    public static int attackSpeedUpgradeCost = 40;
    public static int knockbackResistanceUpgradeCost = 33;
    public static int healthUpgradeCost = 40;
    public static int doubleJumpCost = 200;

    public static void init() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            loadData(player);
        }

        Bukkit.getServer().getPluginManager().registerEvents(new UpgradeGUI(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DoubleJump(), InfiniteFunProject.plugin);
    }

    public static void setUpgradeLevel(Player player, PlayerUpgrade upgrade, int level) {
        if(!upgrades.containsKey(player)) {
            upgrades.put(player, new HashMap<>());
            upgrades.get(player).put(upgrade, Math.min(level, upgrade.getMaxLevel()));
            System.out.println("Creating fresh entry for " + player.getName());
            return;
        }
        if(upgrades.get(player) == null) {
            upgrades.put(player, new HashMap<>());
            upgrades.get(player).put(upgrade, Math.min(level, upgrade.getMaxLevel()));
            System.out.println("Creating new upgrade list " + player.getName());
            return;
        }
        if(!upgrades.get(player).containsKey(upgrade)) {
            upgrades.get(player).put(upgrade, Math.min(level, upgrade.getMaxLevel()));
            System.out.println("Creating new upgrade key " + player.getName());
            return;
        }
        if(upgrades.get(player).get(upgrade) == null) {
            upgrades.get(player).put(upgrade, Math.min(level, upgrade.getMaxLevel()));
            System.out.println("Fixing missing upgrade level for " + player.getName());
            return;
        }

        upgrades.get(player).put(upgrade, Math.min(level, upgrade.getMaxLevel()));
    }

    public static int getUpgradeLevel(Player player, PlayerUpgrade upgrade) {
        if(!upgrades.containsKey(player)) {
            System.out.println(player.getName() + " not in upgrade list");
            return 0;
        }
        if(upgrades.get(player) == null) {
            System.out.println(player.getName() + "'s upgrade not in upgrade list");
            return 0;
        }
        if(!upgrades.get(player).containsKey(upgrade)) {
            System.out.println(player.getName() + "'s upgrade not in upgrade list");
            return 0;
        }
        if(upgrades.get(player).get(upgrade) == null) {
            System.out.println(player.getName() + "'s upgrade not in upgrade list");
            return 0;
        }

        return upgrades.get(player).get(upgrade);
    }

    public static int nextLevel(Player player, PlayerUpgrade upgrade) {
        if(!upgrades.containsKey(player)) {
            return 1;
        }
        if(upgrades.get(player) == null) {
            return 1;
        }
        if(!upgrades.get(player).containsKey(upgrade)) {
            return 1;
        }
        if(upgrades.get(player).get(upgrade) == null) {
            return 1;
        }

        return upgrades.get(player).get(upgrade) + 1;
    }

    public static HashMap<PlayerUpgrade, Integer> getUpgrades(Player player) {
        if(!upgrades.containsKey(player)) {
            HashMap<PlayerUpgrade, Integer> blank = new HashMap<>();

            for(PlayerUpgrade upgrade : PlayerUpgrade.values()) {
                blank.put(upgrade, 0);
            }

            return blank;
        }

        return upgrades.get(player);
    }

    public static void saveData(Player player) {
        try {
            if(!InfiniteFunProject.plugin.getDataFolder().exists()) {
                System.out.println("Creating data folder");
                InfiniteFunProject.plugin.getDataFolder().mkdir();
            }

            File file = new File(InfiniteFunProject.plugin.getDataFolder(), File.separator + player.getUniqueId() + "_upgrades.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                System.out.println("Created new file for " + player.getName());
                file.createNewFile();
            }

            config.createSection("upgrades");
            for(PlayerUpgrade upgrade : PlayerUpgrade.values()) {
                config.set("upgrades." + upgrade.getUpgradeName(), getUpgradeLevel(player, upgrade));
            }

            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Player player) {
        try {
            File file = new File(InfiniteFunProject.plugin.getDataFolder(), File.separator + player.getUniqueId() + "_upgrades.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                System.out.println(player.getName() + " does not have a file");
                return;
            }

            for(PlayerUpgrade upgrade : PlayerUpgrade.values()) {
                int lvl = config.getInt("upgrades." + upgrade.getUpgradeName());

                setUpgradeLevel(player, upgrade, lvl);
            }

            applyUpgrades(player);

            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void applyUpgrades(Player player) {
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(PlayerJoin.getBaseDamage() + (getUpgradeLevel(player, PlayerUpgrade.DAMAGE) * 0.5));

        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 + (getUpgradeLevel(player, PlayerUpgrade.ATTACK_SPEED) * 0.32));

        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0 + (getUpgradeLevel(player, PlayerUpgrade.ARMOR) * 0.35));
        player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0 + (getUpgradeLevel(player, PlayerUpgrade.ARMOR) * 0.35));

        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0 + (getUpgradeLevel(player, PlayerUpgrade.KNOCKBACK_RESISTANCE) * 0.02));

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + (getUpgradeLevel(player, PlayerUpgrade.HEALTH) * 0.5));

        if(getUpgradeLevel(player, PlayerUpgrade.DOUBLE_JUMP) > 0) {
            player.setMetadata("DoubleJump", new DoubleJumpMeta());
            player.setAllowFlight(true);
        }
    }
}

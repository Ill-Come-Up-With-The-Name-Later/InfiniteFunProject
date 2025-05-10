package survivaltweaks.infinitefunproject.Periodic.WorldModifiers;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class WorldModInit {

    private static final int[] countdown = { 99999 };
    public static int delay = 500;

    public static WorldModifier activeModifier = WorldModifier.NONE;
    public static ArrayList<WorldModifier> bonusModifiers = new ArrayList<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new AddModifiers(), plugin);
        boolean played = true;

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.hasPlayedBefore()) {
                played = false;
            }
        }

        if(!played) {
            setActiveModifier(WorldModifier.CALM_BEFORE_THE_STORM);
            Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The modifier is now " +
                    InfiniteFunProject.fixCaps(getActiveModifier().toString()) + "!"));
            Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + getActiveModifier().getDescription()));
            getCountdown()[0] = 300;
        } else {
            pickModifier();
            getCountdown()[0] = delay;
        }

        BossBar modifiers = Bukkit.createBossBar(NamespacedKey.minecraft("modifiers"), "", BarColor.PURPLE, BarStyle.SOLID);
        modifiers.setVisible(true);
        modifiers.setProgress(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(modifierCountActive()) {
                    getCountdown()[0]--;
                }
                if(!modifierCountActive()) {
                    setActiveModifier(WorldModifier.NONE);
                }

                if(getCountdown()[0]  > 0 && getCountdown()[0] <= delay) {
                    modifiers.setProgress((double) getCountdown()[0] / delay);
                } else {
                    modifiers.setProgress(1);
                }

                List<Player> visible = modifiers.getPlayers();

                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(!modifierCountActive()) {
                        modifiers.setColor(BarColor.BLUE);
                        modifiers.setTitle(color("&b&lPAUSED"));
                    } else if(getCountdown()[0] > 30) {
                        modifiers.setColor(BarColor.PURPLE);
                        modifiers.setTitle(color("&dNext Modifier In: &r&l" + getCountdown()[0] + " &dSeconds &r- &dCurrent&r: &d" +
                                getModifierTitle()));
                    } else if(getCountdown()[0] > 10) {
                        modifiers.setColor(BarColor.YELLOW);
                        modifiers.setTitle(color("&eNext Modifier In: &r&l" + getCountdown()[0] + " &eSeconds &r- &dCurrent&r: &d" +
                                getModifierTitle()));
                    } else if(getCountdown()[0] > 5) {
                        modifiers.setColor(BarColor.RED);
                        modifiers.setTitle(color("&cNext Modifier In: &r&l" + getCountdown()[0] + " &cSeconds &r- &dCurrent&r: &d" +
                                getModifierTitle()));
                    } else {
                        if(getCountdown()[0] % 2 == 1) {
                            modifiers.setColor(BarColor.RED);
                        } else {
                            modifiers.setColor(BarColor.WHITE);
                        }

                        modifiers.setTitle(color("&cNext Modifier In: &r&l" + getCountdown()[0] + " &cSeconds &r- &dCurrent&r: &d" +
                                getModifierTitle()));
                    }

                    if(!visible.contains(player)) {
                        modifiers.addPlayer(player);
                    }
                }

                if(getCountdown()[0] < 1) {
                    getCountdown()[0] = delay;
                    pickModifier();
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 20, 20);
    }

    public static String getModifierTitle() {
        StringBuilder title = new StringBuilder();
        title.append(fixCaps(getActiveModifier().toString()));

        for(int i = 0; i < bonusModifiers.size(); i++) {
            if(i + 1 <= bonusModifiers.size()) {
                title.append("&r, &6");
            }
            title.append(fixCaps(bonusModifiers.get(i).toString()));
        }

        return title.toString();
    }

    public static void pickModifier() {
        int random = new Random().nextInt(0, WorldModifier.values().length - 3);

        while(modifierActive(WorldModifier.values()[random])) {
            random = new Random().nextInt(0, WorldModifier.values().length - 3);
        }

        setActiveModifier(WorldModifier.values()[random]);
        bonusModifiers.clear();

        Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The modifier is now " +
                InfiniteFunProject.fixCaps(getActiveModifier().toString()) + "!"));
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + getActiveModifier().getDescription()));

        if(new Random().nextInt(0, 33) == 1) {
            pickBonusModifiers();
        }
    }

    public static void pickBonusModifiers() {
        int amount = new Random().nextInt(1, 4);

        for(int i = 0; i < amount; i++) {
            int random = new Random().nextInt(0, WorldModifier.values().length - 3);

            if(modifierActive(WorldModifier.values()[random]) ||
                    (cooldownModifiers().contains(WorldModifier.values()[random]) && cooldownModifierActive())) {
                while(modifierActive(WorldModifier.values()[random]) ||
                        (cooldownModifiers().contains(WorldModifier.values()[random]) && cooldownModifierActive())) {
                    random = new Random().nextInt(0, WorldModifier.values().length - 3);
                }
            }

            WorldModifier modifier = WorldModifier.values()[random];

            addBonusModifier(modifier);
            Bukkit.spigot().broadcast(new TextComponent(color("&d&lBonus Modifier: &6" + fixCaps(modifier.toString())
            + "&d&l!")));
            Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + modifier.getDescription()));
        }
    }

    public static WorldModifier getActiveModifier() {
        return activeModifier;
    }

    public static ArrayList<WorldModifier> getBonusModifiers() {
        return bonusModifiers;
    }

    public static void setActiveModifier(WorldModifier activeModifier) {
        WorldModInit.activeModifier = activeModifier;
    }

    public static void addBonusModifier(WorldModifier modifier) {
        bonusModifiers.add(modifier);
    }

    public static void removeBonusModifier(WorldModifier modifier) {
        bonusModifiers.remove(modifier);
    }

    public static int[] getCountdown() {
        return countdown;
    }

    public static boolean modifierActive(WorldModifier modifier) {
        return activeModifiers().contains(modifier);
    }

    public static WorldModifier getCooldownModifier() {
        if(modifierActive(WorldModifier.ANOMALOUS)) {
            return WorldModifier.ANOMALOUS;
        }
        if(modifierActive(WorldModifier.INSTANT_COOLDOWNS)) {
            return WorldModifier.INSTANT_COOLDOWNS;
        }
        if(modifierActive(WorldModifier.TRIPLE_COOLDOWNS)) {
            return WorldModifier.TRIPLE_COOLDOWNS;
        }

        return WorldModifier.NONE;
    }

    public static boolean cooldownModifierActive() {
        return modifierActive(WorldModifier.INSTANT_COOLDOWNS) || modifierActive(WorldModifier.TRIPLE_COOLDOWNS)
                || modifierActive(WorldModifier.ANOMALOUS);
    }

    public static ArrayList<WorldModifier> cooldownModifiers() {
        return new ArrayList<>() {
            {
                add(WorldModifier.INSTANT_COOLDOWNS);
                add(WorldModifier.ANOMALOUS);
                add(WorldModifier.TRIPLE_COOLDOWNS);
            }
        };
    }

    public static ArrayList<WorldModifier> activeModifiers() {
        return new ArrayList<>() {
            {
                add(getActiveModifier());
                addAll(getBonusModifiers());
            }
        };
    }
}

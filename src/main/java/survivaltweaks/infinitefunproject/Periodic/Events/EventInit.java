package survivaltweaks.infinitefunproject.Periodic.Events;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.Periodic.Events.Anomalies.*;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.*;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.List;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class EventInit {

    private static final int[] countdown = { 240 };
    public static int delay = 240;

    public static void init() {
        BossBar events = Bukkit.createBossBar(NamespacedKey.minecraft("events"), "", BarColor.GREEN, BarStyle.SOLID);
        events.setVisible(true);
        events.setProgress(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(eventCountActive()) {
                    if(WorldModInit.modifierActive(WorldModifier.SPEEDY_EVENTS) || WorldModInit.modifierActive(WorldModifier.ANOMALOUS)) {
                        getCountdown()[0] -= 2;
                    } else {
                        getCountdown()[0]--;
                    }
                }

                if(getCountdown()[0] > 0 && getCountdown()[0] <= delay) {
                    events.setProgress((double) getCountdown()[0] / delay);
                } else {
                    events.setProgress(1);
                }

                List<Player> visible = events.getPlayers();

                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(!eventCountActive()) {
                        events.setColor(BarColor.BLUE);
                        events.setTitle(color("&b&lPAUSED"));
                    } else if(getCountdown()[0] > 30) {
                        events.setColor(BarColor.GREEN);
                        events.setTitle(color("&aNext Event In: &r&l" + getCountdown()[0] + " &aSeconds"));
                    } else if(getCountdown()[0] > 10) {
                        events.setColor(BarColor.YELLOW);
                        events.setTitle(color("&eNext Event In: &r&l" + getCountdown()[0] + " &eSeconds"));
                    } else if(getCountdown()[0] > 5) {
                        events.setColor(BarColor.RED);
                        events.setTitle(color("&cNext Event In: &r&l" + getCountdown()[0] + " &cSeconds"));
                    } else {
                        if(getCountdown()[0] % 2 == 1) {
                            events.setColor(BarColor.RED);
                        } else {
                            events.setColor(BarColor.WHITE);
                        }

                        events.setTitle(color("&cNext Event In: &r&l" + getCountdown()[0] + " &cSeconds"));
                    }

                    if(!visible.contains(player)) {
                        events.addPlayer(player);
                    }
                }
                if(getCountdown()[0] < 1) {
                    getCountdown()[0] = delay;
                    pickEvent();
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 20, 20);
    }

    public static void pickEvent() {
        int random = new Random().nextInt(0, 19);

        if(WorldModInit.modifierActive(WorldModifier.LUCK_MANIPULATION)) {
            pickAnomaly();
            return;
        }

        switch (random) {
            case 0:
                new SpawnZombies().trigger();
                break;
            case 1:
                new GiveLoot().trigger();
                break;
            case 2:
                new GiveArmor().trigger();
                break;
            case 3:
                new GiveExperience().trigger();
                break;
            case 4:
                new SpawnPhantoms().trigger();
                break;
            case 5:
                new ClearPlayerEffects().trigger();
                break;
            case 6:
                new SpawnSpiders().trigger();
                break;
            case 7:
                new SwapSpots().trigger();
                break;
            case 8:
                new GiveDirt().trigger();
                break;
            case 9:
                new SpawnAnvil().trigger();
                break;
            case 10:
                new GiveFood().trigger();
                break;
            case 11:
                new Restoration().trigger();
                break;
            case 12:
                new ExplodingAxolotl().trigger();
                break;
            case 13:
                new InvisEntities().trigger();
                break;
            case 14:
                new SpawnMagmaCubes().trigger();
                break;
            case 15:
                Bukkit.spigot().broadcast(new TextComponent(ChatColor.AQUA + "THREE-IN-ONE SPECIAL! LIMITED TIME OFFER!"));
                for(int i = 1; i <= 3; i++) {
                    Bukkit.getScheduler().runTaskLater(plugin, EventInit::pickEvent, 20 * i);
                }
                break;
            case 16:
                new ResetMeters().trigger();
                break;
            case 17:
                pickAnomaly();
                break;
            case 18:
                new GiveRandomSpecialItem().trigger();
                break;
        }
    }

    public static void pickAnomaly() {
        int anomaly = new Random().nextInt(0, 15);
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "An anomaly has occurred!"));

        switch (anomaly) {
            case 0:
                new ChangeModifier().trigger();
                break;
            case 1:
                new AlterStatusMeters().trigger();
                break;
            case 2:
                new SpawnBlazes().trigger();
                break;
            case 3:
                new SetSpecialModifier().trigger();
                break;
            case 4:
                new SetNoModifier().trigger();
                break;
            case 5:
                new SpawnStrays().trigger();
                break;
            case 6:
                new GiveSuperStrength().trigger();
                break;
            case 7:
                new MakePlayersInvincible().trigger();
                break;
            case 8:
                new BreakStorm().trigger();
                break;
            case 9:
                new AlterEventMeter().trigger();
                break;
            case 10:
                new AlterModifierMeter().trigger();
                break;
            case 11:
                new ExtendModifier().trigger();
                break;
            case 12:
                new ExtendEventTimer().trigger();
                break;
            case 13:
                new BonusModifiers().trigger();
                break;
            case 14:
                new GiveSpecialItems().trigger();
                break;
        }
    }

    public static int[] getCountdown() {
        return countdown;
    }
}

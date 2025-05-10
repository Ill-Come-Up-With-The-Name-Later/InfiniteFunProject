package survivaltweaks.infinitefunproject.TimeEvents.MeteorShower;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.Meteor;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.addProjectileTrail;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class RainMeteors {

    public static HashMap<World, Boolean> meteorShower = new HashMap<>();

    public static void init() {
        for(World world : Bukkit.getWorlds()) {
            meteorShower.put(world, false);
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(world.getEnvironment() == World.Environment.NORMAL || world.getEnvironment() == World.Environment.THE_END) {
                        if(world.getPlayers().isEmpty()) {
                            continue;
                        }

                        boolean day = world.isDayTime();

                        if(!(day || hasMeteorShower(world))) {
                            if(new Random().nextInt(0, 1200) == 1) {
                                startShower(world);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 20, 20);
    }

    public static boolean hasMeteorShower(World world) {
        return meteorShower.get(world);
    }

    public static void startShower(World world) {
        meteorShower.put(world, true);
        meteorShower(world);
    }

    public static void endShower(World world) {
        meteorShower.put(world, false);
    }

    public static void meteorShower(World world) {
        if(world.getPlayers().isEmpty()) {
            meteorShower.put(world, false);
            return;
        }

        Bukkit.spigot().broadcast(new TextComponent(color("&cA meteor shower is inbound!")));

        for(Player player : world.getPlayers()) {
            player.sendTitle(color("&cA meteor shower is inbound!"),
                    color("&cSeek shelter immediately!"), 10, 25, 10);
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                if(world.isDayTime() || world.getPlayers().isEmpty() || !meteorShower.get(world)) {
                    cancel();
                    endShower(world);
                    Bukkit.spigot().broadcast(new TextComponent(color("&aThe meteor shower is over!")));
                    return;
                }

                if(new Random().nextBoolean()) {
                    for(Player player : world.getPlayers()) {
                        rainMeteors(player);
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 15, 15);
    }

    public static void rainMeteors(Player player) {
        World world = player.getWorld();

        ArrayList<Particle> trail = new ArrayList<>() {
            {
                add(Particle.FLAME);
                add(Particle.SMOKE);
                add(Particle.WHITE_SMOKE);
                add(Particle.LARGE_SMOKE);
                add(Particle.EXPLOSION);
            }
        };

        int meteors = new Random().nextInt(7, 16);

        for(int i = 0; i < meteors; i++) {
            Location location = player.getLocation().add(new Vector(
                    new Random().nextInt(-75, 75),
                    0,
                    new Random().nextInt(-75, 75)
            ));

            location.setY(new Random().nextInt(295, 325));

            LargeFireball meteor = world.spawn(location, LargeFireball.class);
            addProjectileTrail(meteor, trail);
            meteor.setMetadata("Meteor", new Meteor());

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                Vector velocity = player.getBoundingBox().getCenter().add(new Vector(
                        new Random().nextInt(-25, 25), 0, new Random().nextInt(-25, 25)))
                        .subtract(meteor.getLocation().toVector());

                velocity.normalize().multiply(35);

                meteor.setIsIncendiary(true);
                meteor.setDisplayItem(ItemManager.meteorFragment);
                meteor.setYield(3.6f);

                meteor.setAcceleration(velocity.normalize().multiply(5));
                meteor.setDirection(velocity.normalize());
                meteor.setGlowing(true);

                meteor.setVelocity(velocity);
                meteor.setInvulnerable(true);
                meteor.setGravity(true);
                meteor.setHasLeftShooter(true);
                meteor.setHasBeenShot(true);
            }, 1);

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, meteor::remove, 600);
        }
    }
}

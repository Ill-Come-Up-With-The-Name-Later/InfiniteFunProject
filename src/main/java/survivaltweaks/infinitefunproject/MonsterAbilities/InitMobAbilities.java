package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class InitMobAbilities {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ActivateEnemyAbilities(), InfiniteFunProject.plugin);
        showParticles();
    }

    /**
     * Shows particles to indicate
     * an enemy has an ability
     */
    public static void showParticles() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    for(Monster monster : world.getEntitiesByClass(Monster.class)) {
                        if(OnMobAttackedAbility.hasAbility(monster) || OnMobAttacksAbility.hasAbility(monster)) {
                            ArrayList<Particle> particles = new ArrayList<>() {
                                {
                                    add(Particle.WITCH);
                                    add(Particle.OMINOUS_SPAWNING);
                                }
                            };

                            drawCircle(monster.getLocation(), 1.5, particles, 60);
                            drawCircle(monster.getLocation().add(new Vector(0, 0.6, 0)), 1.5, particles, 60);
                            drawCircle(monster.getLocation().add(new Vector(0, 1.2, 0)), 1.5, particles, 60);
                        }
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 15);
    }
}

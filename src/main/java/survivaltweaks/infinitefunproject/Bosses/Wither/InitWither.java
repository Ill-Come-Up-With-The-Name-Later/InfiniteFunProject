package survivaltweaks.infinitefunproject.Bosses.Wither;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Bosses.Wither.Attacks.WitherAttacks;
import survivaltweaks.infinitefunproject.Bosses.Wither.Attacks.WitherRebirth;
import survivaltweaks.infinitefunproject.Bosses.Wither.Attacks.WitherSecondaryAttacks;
import survivaltweaks.infinitefunproject.Bosses.Wither.Attacks.WitherUltimateAttack;

public class InitWither {

    /**
     * Establish event listeners
     */
    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new WitherAttacks(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WitherRebirth(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WitherSecondaryAttacks(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ControlWitherSpawn(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WitherUltimateAttack(), InfiniteFunProject.plugin);
    }

    /**
     * Set enemy as wither spawn
     * @param entity: An entity
     */
    public static void setWitherSpawn(LivingEntity entity) {
        entity.setMetadata("WitherSpawn", new WitherSpawn());
    }
}

package survivaltweaks.infinitefunproject.Bosses.EnderDragon;

import org.bukkit.Bukkit;
import org.bukkit.World;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks.*;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.CrystalCallHelp;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.ProjectileImmunity;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal.AttackPlayers;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal.BlockDragonPerch;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal.SpawnCrystal;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class InitDragon {

    /**
     * Establish event listeners
     */
    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new SelfDefenseBlast(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileImmunity(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DragonAttacks(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnCrystal(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AttackPlayers(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileShield(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new CrystalCallHelp(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DragonSecondaryAttacks(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockDragonPerch(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DragonUltimateAttack(), InfiniteFunProject.plugin);
    }

    /**
     * Find if the dragon has been defeated
     *
     * @return If the dragon has been defeated
     */
    public static boolean dragonDead() {
        for(World world : Bukkit.getWorlds()) {
            try {
                if(world.getEnderDragonBattle().hasBeenPreviouslyKilled()) {
                    return true;
                }
            } catch(NullPointerException ignored) {}
        }
        return false;
    }
}

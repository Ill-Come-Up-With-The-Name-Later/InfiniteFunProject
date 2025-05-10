package survivaltweaks.infinitefunproject.AntiLag;

import org.bukkit.World;
import org.bukkit.entity.*;
import survivaltweaks.infinitefunproject.AntiLag.Removals.*;

public class InitAntiLag {

    public static int maxItemCount = 600;
    public static int maxProjectileCount = 175;
    public static int maxTextDisplayCount = 100;
    public static int maxEntityCount = 1800;
    public static int maxTNTCount = 200;
    public static int maxArmorStandCount = 300;
    public static int minTPS = 12;

    public static void init() {
        RemoveItems.tick();
        RemoveProjectiles.tick();
        RemoveTextDisplay.tick();
        RemoveEntities.tick();
        UnloadChunks.tick();
        PreventVelocity.tick();
        RemovePrimeTNT.tick();
        RemoveArmorStands.tick();

        EmergencyTPS.tick();
    }

    /**
     * Gets the item count in a world
     *
     * @param world: A world
     * @return The item count
     */
    public static int getItemCount(World world) {
        return world.getEntitiesByClass(Item.class).size();
    }

    /**
     * Gets the projectile count in a world
     *
     * @param world: A world
     * @return The projectile count
     */
    public static int getProjectileCount(World world) {
        return world.getEntitiesByClass(Projectile.class).size();
    }

    /**
     * Gets the text display count in a world
     *
     * @param world: A world
     * @return The text display count
     */
    public static int getTextDisplayCount(World world) {
        return world.getEntitiesByClass(TextDisplay.class).size();
    }

    /**
     * Gets the entity count in a world
     *
     * @param world: A world
     * @return The entity count
     */
    public static int getEntityCount(World world) {
        return world.getEntitiesByClass(Entity.class).size();
    }

    /**
     * Gets the prime TNT count in a world
     *
     * @param world: A world
     * @return The prime TNT count
     */
    public static int getPrimedTNTCount(World world) {
        return world.getEntitiesByClass(TNTPrimed.class).size();
    }

    /**
     * Gets the armor stand count in a world
     *
     * @param world: A world
     * @return The armor stand count
     */
    public static int getArmorStandCount(World world) {
        return world.getEntitiesByClass(ArmorStand.class).size();
    }
}

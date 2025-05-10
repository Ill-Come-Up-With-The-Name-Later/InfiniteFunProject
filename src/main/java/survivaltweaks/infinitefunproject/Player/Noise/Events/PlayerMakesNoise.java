package survivaltweaks.infinitefunproject.Player.Noise.Events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.distanceBetween;

public class PlayerMakesNoise implements Listener {

    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location noiseSource = block.getLocation();

        float noise = block.getBlockData().getMaterial().getHardness() * 10;
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity entity : entities) {
            if(entity instanceof Monster) {
                Monster monster = (Monster) entity;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 4;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location noiseSource = block.getLocation();

        float noise = block.getBlockData().getMaterial().getHardness() * 15;
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity entity : entities) {
            if(entity instanceof Monster) {
                Monster monster = (Monster) entity;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 3;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void bucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        Location noiseSource = player.getLocation();

        float noise = 12;
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 2;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Location noiseSource = player.getLocation();

        float noise = 14;
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 2;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        Location noiseSource = player.getLocation();

        float noise = item.getAmount();
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 5;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Player) {
            Player player = (Player) projectile.getShooter();
            Location noiseSource = player.getLocation();

            float noise = (float) projectile.getVelocity().lengthSquared();
            ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

            for(Entity ent : entities) {
                if(ent instanceof Monster) {
                    Monster monster = (Monster) ent;

                    if(monster.getTarget() == null) {
                        double chance = distanceBetween(noiseSource, monster.getLocation()) * 5;

                        if(new Random().nextInt(0, 100) <= chance) {
                            monster.setTarget(player);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void takeDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        double damage = event.getDamage();

        if(!(entity instanceof Player)) {
            return;
        }

        Player player = (Player) entity;
        Location noiseSource = player.getLocation();

        float noise = (float) (damage * 2.5);
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 3;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerAttackEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        double damage = event.getDamage();

        if(!(entity instanceof Player)) {
            return;
        }

        Player player = (Player) entity;
        Location noiseSource = player.getLocation();

        float noise = (float) (damage * 2.5);
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 2.5;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Vector velocity = player.getVelocity();
        double movement = velocity.lengthSquared();
        Location noiseSource = player.getLocation();

        float noise = (float) movement * 2;

        if(player.isSprinting()) {
            noise *= 3;
        }
        if(player.isSneaking()) {
            noise *= 0.5f;
        }

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 7;

                    if (new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        Vector velocity = player.getVelocity();
        double movement = velocity.lengthSquared();
        Location noiseSource = player.getLocation();

        float noise = (float) movement * 3.6f;

        if(player.isSprinting()) {
            noise *= 3;
        }
        if(player.isSneaking()) {
            noise *= 0.5f;
        }

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(noiseSource, noise);

        for(Entity ent : entities) {
            if(ent instanceof Monster) {
                Monster monster = (Monster) ent;

                if(monster.getTarget() == null) {
                    double chance = distanceBetween(noiseSource, monster.getLocation()) * 7;

                    if(new Random().nextInt(0, 100) <= chance) {
                        monster.setTarget(player);
                    }
                }
            }
        }
    }
}

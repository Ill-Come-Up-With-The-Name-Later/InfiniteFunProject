package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Scan implements Passive {

    int startRadius = 3;
    int expansions = 8;
    int expandAmount = 2;
    int delay = 2;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
            }
        };

        drawExpandingCircle(player.getLocation(), startRadius, expansions, expandAmount, delay, particles);
        drawExpandingCircle(player.getLocation().add(0, 0.5, 0), startRadius, expansions, expandAmount, delay, particles);
        drawExpandingCircle(player.getLocation().add(0, 0.75, 0), startRadius, expansions, expandAmount, delay, particles);
        drawExpandingCircle(player.getLocation().add(0, 1, 0), startRadius, expansions, expandAmount, delay, particles);
        drawExpandingCircle(player.getLocation().add(0, 1.25, 0), startRadius, expansions, expandAmount, delay, particles);

        ArrayList<Entity> scanned = new ArrayList<>();

        for(int i = 0; i < expansions; i++) {
            int finalI = i;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, startRadius + (finalI * expandAmount));
                entities.removeIf(scanned::contains);

                for(Entity entity : entities) {
                    if(!(entity instanceof LivingEntity || entity instanceof Item)) {
                        continue;
                    }

                    if(!scanned.contains(entity)) {
                        scanned.add(entity);
                        glow(entity, 30);

                        Location spawnLoc = entity.getLocation();
                        spawnLoc.add(new Vector(
                                new Random().nextDouble(-0.6, 0.6),
                                0.55 + new Random().nextDouble(0.1, 0.3),
                                new Random().nextDouble(-0.6, 0.6)
                        ));

                        TextDisplay textDisplay = entity.getWorld().spawn(spawnLoc, TextDisplay.class);
                        textDisplay.setCustomNameVisible(false);
                        textDisplay.setGravity(false);
                        textDisplay.setInvulnerable(true);
                        textDisplay.setBillboard(Display.Billboard.CENTER);

                        Bukkit.getScheduler().runTaskLater(plugin, textDisplay::remove, 30);

                        if(entity instanceof Item) {
                            Item item = (Item) entity;

                            textDisplay.setBackgroundColor(Color.OLIVE);
                            textDisplay.setText(color("&a" + fixCaps(item.getItemStack().getType().toString())));
                        } else {
                            textDisplay.setBackgroundColor(Color.fromRGB(255, 57, 43));
                            textDisplay.setText(color("&0" + fixCaps(entity.getType().toString())));

                            if(entity.hasMetadata("Infected")) {
                                textDisplay.setText(textDisplay.getText() + color(" &r- &0&lInfected"));
                            }
                        }
                    }
                }
            }, (long) i * delay);
        }
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Scans for nearby entities\nand indicates them to\nthe player.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

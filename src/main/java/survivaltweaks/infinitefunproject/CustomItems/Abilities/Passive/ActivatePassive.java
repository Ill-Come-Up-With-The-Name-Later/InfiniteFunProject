package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.hasCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;

public class ActivatePassive implements Listener {

    @EventHandler
    public void activateOnDamagedAbilities(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(!(damager instanceof LivingEntity)) {
            return;
        }

        if(damaged instanceof Player) {
            Player player = (Player) damaged;
            LivingEntity entity = (LivingEntity) damager;

            if(player.isDead()) {
                return;
            }

            for(ItemStack item : player.getInventory()) {
                if(item == null) {
                    continue;
                }

                if(OnDamagedAbility.hasAbility(item)) {
                    ArrayList<OnDamagedAbility> abilities = OnDamagedAbility.getAbilities(item);

                    for(OnDamagedAbility ability : abilities) {
                        if(hasCooldown(player, ability)) {
                            continue;
                        }

                        ability.getAbility().activate(player, entity);
                        ability.getAbility().activate(player);

                        switch (WorldModInit.getActiveModifier()) {
                            case INSTANT_COOLDOWNS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    if (ability.getCooldown() > 20) {
                                        setCooldown(player, ability, 20);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                    }
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            case TRIPLE_COOLDOWNS:
                                setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                break;
                            case ANOMALOUS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            default:
                                setCooldown(player, ability, ability.getAbility().getCooldown());
                                break;
                        }

                        switch (WorldModInit.getCooldownModifier()) {
                            case INSTANT_COOLDOWNS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    if(ability.getCooldown() > 20) {
                                        setCooldown(player, ability, 20);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                    }
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            case TRIPLE_COOLDOWNS:
                                setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                break;
                            case ANOMALOUS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            default:
                                setCooldown(player, ability, ability.getAbility().getCooldown());
                                break;
                        }

                        player.sendMessage(ChatColor.GREEN + "Activated " + ChatColor.YELLOW + ability.getAbilityName() + ChatColor.GREEN + "!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void activateOnDamagedAbilitiesFromProjectile(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(!(damager instanceof Projectile)) {
            return;
        }

        if(damaged instanceof Player) {
            Player player = (Player) damaged;
            Projectile projectile = (Projectile) damager;

            if(!(projectile.getShooter() instanceof LivingEntity)) {
                return;
            }

            LivingEntity entity = (LivingEntity) projectile.getShooter();

            if(player.isDead()) {
                return;
            }

            for(ItemStack item : player.getInventory()) {
                if(item == null) {
                    continue;
                }

                if(OnDamagedAbility.hasAbility(item)) {
                    ArrayList<OnDamagedAbility> abilities = OnDamagedAbility.getAbilities(item);

                    for(OnDamagedAbility ability : abilities) {
                        if(hasCooldown(player, ability)) {
                            continue;
                        }

                        ability.getAbility().activate(player, entity);
                        ability.getAbility().activate(player);

                        switch (WorldModInit.getActiveModifier()) {
                            case INSTANT_COOLDOWNS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    if (ability.getCooldown() > 20) {
                                        setCooldown(player, ability, 20);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                    }
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            case TRIPLE_COOLDOWNS:
                                setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                break;
                            case ANOMALOUS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            default:
                                setCooldown(player, ability, ability.getAbility().getCooldown());
                                break;
                        }

                        switch (WorldModInit.getCooldownModifier()) {
                            case INSTANT_COOLDOWNS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    if(ability.getCooldown() > 20) {
                                        setCooldown(player, ability, 20);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                    }
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            case TRIPLE_COOLDOWNS:
                                setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                break;
                            case ANOMALOUS:
                                if(ability.getAbility().cooldownModifiable()) {
                                    setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                } else {
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                }
                                break;
                            default:
                                setCooldown(player, ability, ability.getAbility().getCooldown());
                                break;
                        }

                        player.sendMessage(ChatColor.GREEN + "Activated " + ChatColor.YELLOW + ability.getAbilityName() + ChatColor.GREEN + "!");
                    }
                }
            }
        }
    }

    public static void activateIntervalAbilities() {
        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.isDead()) {
                    continue;
                }

                for(ItemStack item : player.getInventory()) {
                    if(item == null) {
                        continue;
                    }

                    if(PassiveAbility.hasAbility(item)) {
                        ArrayList<PassiveAbility> abilities = PassiveAbility.getAbilities(item);

                        for(PassiveAbility ability : abilities) {
                            if(hasCooldown(player, ability)) {
                                continue;
                            }

                            ability.getAbility().activate(player);

                            switch (WorldModInit.getActiveModifier()) {
                                case INSTANT_COOLDOWNS:
                                    if(ability.getAbility().cooldownModifiable()) {
                                        if (ability.getCooldown() > 20) {
                                            setCooldown(player, ability, 20);
                                        } else {
                                            setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                        }
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown());
                                    }
                                    break;
                                case TRIPLE_COOLDOWNS:
                                    setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                    break;
                                case ANOMALOUS:
                                    if(ability.getAbility().cooldownModifiable()) {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown());
                                    }
                                    break;
                                default:
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                    break;
                            }

                            switch (WorldModInit.getCooldownModifier()) {
                                case INSTANT_COOLDOWNS:
                                    if(ability.getAbility().cooldownModifiable()) {
                                        if(ability.getCooldown() > 20) {
                                            setCooldown(player, ability, 20);
                                        } else {
                                            setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                                        }
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown());
                                    }
                                    break;
                                case TRIPLE_COOLDOWNS:
                                    setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                                    break;
                                case ANOMALOUS:
                                    if(ability.getAbility().cooldownModifiable()) {
                                        setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                                    } else {
                                        setCooldown(player, ability, ability.getAbility().getCooldown());
                                    }
                                    break;
                                default:
                                    setCooldown(player, ability, ability.getAbility().getCooldown());
                                    break;
                            }

                            if(ability.getCooldown() > 40) {
                                player.sendMessage(ChatColor.GREEN + "Activated " + ChatColor.YELLOW +
                                        ability.getAbilityName() + ChatColor.GREEN + "!");
                            }
                        }
                    }
                }
            }
        }, 1, 1);
    }
}

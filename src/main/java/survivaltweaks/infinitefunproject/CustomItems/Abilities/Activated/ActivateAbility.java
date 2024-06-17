package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.*;

public class ActivateAbility implements Listener {

    @EventHandler
    public void activateOnHitAbilities(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if(!(damaged instanceof LivingEntity)) {
            return;
        }

        if(damager instanceof Player) {
            Player player = (Player) damager;
            ItemStack item = player.getInventory().getItemInMainHand();
            LivingEntity entity = (LivingEntity) damaged;

            if (!OnHitAbility.hasAbility(item)) return;

            ArrayList<OnHitAbility> abilities = OnHitAbility.getAbilities(item);

            for(OnHitAbility ability : abilities) {
                if(hasCooldown(player, ability)) {
                    continue;
                }

                ability.getAbility().activate(player, entity);
                ability.getAbility().activate(player);
                ability.getAbility().activate(player, event);

                switch (WorldModInit.getActiveModifier()) {
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
            }

            for(OnHitAbility ability : abilities) {
                if(player.getGameMode() == GameMode.CREATIVE) {
                    break;
                }

                if(hasCooldown(player, ability)) {
                    continue;
                }

                if(ability.getAbility().oneTimeUse()) {
                    ItemStack abilityItem = player.getInventory().getItemInMainHand();

                    if (abilityItem.getAmount() > 1) {
                        abilityItem.setAmount(abilityItem.getAmount() - 1);
                    } else {
                        player.getInventory().remove(abilityItem);
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void activateOnHitAbilitiesFromProjectile(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if(!(damaged instanceof LivingEntity)) {
            return;
        }

        if(damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;

            if(!(projectile.getShooter() instanceof Player)) {
                return;
            }

            Player player = (Player) projectile.getShooter();
            ItemStack item = player.getInventory().getItemInMainHand();
            LivingEntity entity = (LivingEntity) damaged;

            if (!OnHitAbility.hasAbility(item)) return;

            ArrayList<OnHitAbility> abilities = OnHitAbility.getAbilities(item);

            for(OnHitAbility ability : abilities) {
                if(hasCooldown(player, ability)) {
                    continue;
                }

                ability.getAbility().activate(player, entity);
                ability.getAbility().activate(player);

                switch (WorldModInit.getActiveModifier()) {
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
            }

            for(OnHitAbility ability : abilities) {
                if(player.getGameMode() == GameMode.CREATIVE) {
                    break;
                }

                if(hasCooldown(player, ability)) {
                    continue;
                }

                if(ability.getAbility().oneTimeUse()) {
                    ItemStack abilityItem = player.getInventory().getItemInMainHand();

                    if (abilityItem.getAmount() > 1) {
                        abilityItem.setAmount(abilityItem.getAmount() - 1);
                    } else {
                        player.getInventory().remove(abilityItem);
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void activateRCAbilities(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = event.getAction();

        if(!RCAbility.hasAbility(item)) return;

        if((action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))) {
            ArrayList<RCAbility> abilities = RCAbility.getAbilities(item);
            event.setCancelled(true);

            for(RCAbility ability : abilities) {
                if(hasCooldown(player, ability)) {
                    player.sendMessage(ChatColor.RED + ability.getAbilityName() + " is on cooldown for " +
                            String.format("%.2f", (float) getCooldown(player, ability) / 20) + " seconds.");
                    continue;
                }

                ability.getAbility().activate(player);

                switch (WorldModInit.getActiveModifier()) {
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

            for(RCAbility ability : abilities) {
                if(player.getGameMode() == GameMode.CREATIVE) {
                    break;
                }

                if(hasCooldown(player, ability)) {
                    continue;
                }

                if(ability.getAbility().oneTimeUse()) {
                    ItemStack abilityItem = player.getInventory().getItemInMainHand();

                    if (abilityItem.getAmount() > 1) {
                        abilityItem.setAmount(abilityItem.getAmount() - 1);
                    } else {
                        player.getInventory().remove(abilityItem);
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void activateLCAbilities(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = event.getAction();

        if(!LCAbility.hasAbility(item)) return;

        if((action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_AIR))) {
            ArrayList<LCAbility> abilities = LCAbility.getAbilities(item);
            event.setCancelled(true);

            for(LCAbility ability : abilities) {
                if(hasCooldown(player, ability)) {
                    player.sendMessage(ChatColor.RED + ability.getAbilityName() + " is on cooldown for " +
                            String.format("%.2f", (float) getCooldown(player, ability) / 20) + " seconds.");
                    continue;
                }

                ability.getAbility().activate(player);

                switch (WorldModInit.getActiveModifier()) {
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

            for(LCAbility ability : abilities) {
                if(player.getGameMode() == GameMode.CREATIVE) {
                    break;
                }

                if(hasCooldown(player, ability)) {
                    continue;
                }

                if(ability.getAbility().oneTimeUse()) {
                    ItemStack abilityItem = player.getInventory().getItemInMainHand();

                    if (abilityItem.getAmount() > 1) {
                        abilityItem.setAmount(abilityItem.getAmount() - 1);
                    } else {
                        player.getInventory().remove(abilityItem);
                    }
                    break;
                }
            }
        }
    }
}

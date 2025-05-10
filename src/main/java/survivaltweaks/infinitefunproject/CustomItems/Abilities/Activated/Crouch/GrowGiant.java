package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.CrouchAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;

public class GrowGiant implements CrouchAbility {

    @Override
    public void activate(Player player) {
        double scale = player.getAttribute(Attribute.GENERIC_SCALE).getBaseValue();
        double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double damage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        double knockBack = player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).getBaseValue() + 1;
        double blockRange = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).getBaseValue();
        double entityRange = player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).getBaseValue();
        double jumpHeight = player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).getBaseValue();
        double moveSpeed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
        double stepHeight = player.getAttribute(Attribute.GENERIC_STEP_HEIGHT).getBaseValue();

        player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(scale * 3);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health * 3);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage * 3);
        player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockBack * 3);
        player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).setBaseValue(blockRange * 6);
        player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).setBaseValue(entityRange * 6);
        player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(jumpHeight * 1.7);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(moveSpeed * 1.6);
        player.getAttribute(Attribute.GENERIC_STEP_HEIGHT).setBaseValue(stepHeight * 7);

        player.setHealth(health * 3);
        giveCrits(player, 200);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(scale);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
            player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockBack - 1);
            player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).setBaseValue(blockRange);
            player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).setBaseValue(entityRange);
            player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(jumpHeight);
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(moveSpeed);
            player.getAttribute(Attribute.GENERIC_STEP_HEIGHT).setBaseValue(stepHeight);
        }, 200);
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Grow to triple size for 10\nseconds and deal more\ndamage.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

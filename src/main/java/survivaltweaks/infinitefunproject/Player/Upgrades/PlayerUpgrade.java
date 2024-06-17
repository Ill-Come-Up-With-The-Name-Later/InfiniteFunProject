package survivaltweaks.infinitefunproject.Player.Upgrades;

import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public enum PlayerUpgrade {

    DAMAGE("damage", false, 100, InitUpgrades.damageUpgradeCost, ItemManager.createDamageUpgradeIcon()), // 0.5 damage per level
    ARMOR("armor", false, 50, InitUpgrades.armorUpgradeCost, ItemManager.createArmorUpgradeIcon()), // 0.35 armor per level
    ATTACK_SPEED("attack_speed", false, 50, InitUpgrades.attackSpeedUpgradeCost,
            ItemManager.createAttackSpeedUpgradeIcon()), // 0.32 attack speed per level
    KNOCKBACK_RESISTANCE("knockback_resistance", false, 50, InitUpgrades.knockbackResistanceUpgradeCost,
            ItemManager.createKBResUpgradeIcon()), // 0.02 knockback resistance per level
    HEALTH("health", false, 60, InitUpgrades.healthUpgradeCost,
            ItemManager.createHealthUpgradeIcon()), // 0.5 health per level
    DOUBLE_JUMP("double_jump", true, 1, InitUpgrades.doubleJumpCost,
            ItemManager.createDoubleJumpUpgradeIcon()), // allows a double jump
    ;

    private final String upgradeName;
    private final boolean oneTimeUnlock;
    private final int maxLevel;
    private final int upgradeCost;
    private final ItemStack upgradeIcon;

    PlayerUpgrade(String upgradeName, boolean oneTimeUnlock, int maxLevel, int upgradeCost, ItemStack upgradeIcon) {
        this.upgradeName = upgradeName;
        this.oneTimeUnlock = oneTimeUnlock;
        this.upgradeCost = upgradeCost;
        this.upgradeIcon = upgradeIcon;
        int maxLvl = maxLevel;

        if(isOneTimeUnlock()) {
            maxLvl = 1;
        }
        this.maxLevel = maxLvl;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public boolean isOneTimeUnlock() {
        return oneTimeUnlock;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public ItemStack getUpgradeIcon() {
        return upgradeIcon;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }
}

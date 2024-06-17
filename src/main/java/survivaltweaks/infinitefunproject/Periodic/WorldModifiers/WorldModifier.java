package survivaltweaks.infinitefunproject.Periodic.WorldModifiers;

import org.bukkit.ChatColor;

public enum WorldModifier {

    SHARD_OF_GLASS("Attacks insta-kill."), // Damage from attacks will instantly kill a non-boss entity
    SHIELDED_MOBS("Mobs gain Resistance I."), // New mobs get resistance 1 on spawn for 10 minutes
    WARP_SPEED("Mobs gain Speed II."), // New mobs get speed 2 on spawn for 2 minutes
    SLASHED_HEALTH("Mob health reduced by half."), // New mobs have half the normal health
    EMPOWERED_ENEMIES("Mobs get Strength I."), // New mobs get strength 1 for 4 minutes
    DOUBLE_DOWN("Attacks deal double damage."), // Double damage attacks
    CRIT_FRENZY("Random critical hits are guaranteed."), // 100% random critical hits
    DAMAGE_BARGAIN("Attacks are now half-off."), // Attacks do half damage
    SUPER_GEARED("Some enemies have enhanced gear."), // Zombies and piglins have sharpness 3 diamond swords and skeletons have power 3 bows
    THORNS("Attacks damage the attacker."), // When an entity is attacked, the attacker takes half damage
    EXTREME_FORTUNE("Double block drops."), // Blocks broken drop double the items
    SHADOW_CLONES("Mobs spawn a clone of themselves."), // When a mob spawns, a second one spawns
    NECROMANCY("Mobs revive on death."), // Mobs revive themselves on death
    SPEEDY_EVENTS("Events occur much faster."), // Random events have their cooldown reduced by half
    SUPER_LOOTING("Double mob loot."), // Mob loot doubled
    BLOCK_PROTECTION("Breaking blocks deals damage."), // Breaking blocks damages the player
    TOUGHENED_ELEMENTS("Status meters increase more."), // Thirst and heat increase by 2 each cooldown cycle
    LUCK_MANIPULATION("All events are anomalies."), // All events are now anomalies
    EXPLOSIVE_CORPSES("Mobs have a 25% chance to explode on death."), // Mobs have a 25% chance to explode on death
    GOO("Mobs have a 25% chance to ride a slime."), // Mobs have a 50% chance to spawn riding a slime
    INSTANT_COOLDOWNS("Ability cooldowns. What are those?"), // Abilities have a 1-second cooldown
    TRIPLE_COOLDOWNS("Waiting for abilities was fun, right?"), // Ability cooldowns tripled
    SICKNESS("Mobs have a 3.33% chance to spawn infected with Coronavirus."), // 3.33% chance to spawn infected mobs
    ULTIMATE_POWER("Double chance for mobs to spawn as Champions."), // 1/135 chance for a champion
    BLOCK_SENTIENCE("Blocks fight back on being broken."), // Blocks will fight back on breaking
    TAX_BREAK("No damage tax will be deducted from attacks."), // No damage tax
    TAXMAN(ChatColor.ITALIC + "\"There's one for you, nineteen for me.\""), // Increase damage tax to 95%
    BOOM_SHOT("All projectiles become explosive."), // Projectiles explode when hitting something
    LIMP_SHOT("Projectiles' velocity is halved on firing."), // Projectiles have half velocity once shot
    INACCURACY("Projectiles now have inaccuracy."), // Projectiles are given spread similar to shotguns
    AIMBOT("Projectiles automatically aim at nearby targets."),
    CALM_BEFORE_THE_STORM("Peace... somewhat."), // Downgrades enemies and is the first modifier
    ANOMALOUS("???"), // Special modifier from only the anomaly, combines a lot of modifiers
    NONE("Nothing happens.") // Nothing changes
    ;

    private final String description;

    WorldModifier(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }
}

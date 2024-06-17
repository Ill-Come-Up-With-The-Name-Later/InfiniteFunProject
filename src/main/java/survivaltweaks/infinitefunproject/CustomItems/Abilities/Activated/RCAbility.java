package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.*;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum RCAbility implements Ability {

    VOID_ENERGY(new DragonSword(), "Void Blast", new DragonSword().getDescription(), new DragonSword().getCooldown()),
    HAMMER_THROW(new SuperHammer(), "Super Throw", new SuperHammer().getDescription(), new SuperHammer().getCooldown()),
    RAGE(new BerserkSword(), "Rage", new BerserkSword().getDescription(), new BerserkSword().getCooldown()),
    THROW_PEARL(new EndlessPearl(), "Pearl Throw", new EndlessPearl().getDescription(), new EndlessPearl().getCooldown()),
    SCROLL_POWER(new DecrepitScroll(), "Darkness' Calling", new DecrepitScroll().getDescription(), new DecrepitScroll().getCooldown()),
    CALL_OF_THE_VOID(new Culmination(), "Call of the Void", new Culmination().getDescription(), new Culmination().getCooldown()),
    LIFE_DRAIN_BUFF(new VampiricFang(), "Bloody Sacrifice", new VampiricFang().getDescription(), new VampiricFang().getCooldown()),
    CANCER_CURE(new Medication(), "Panacea", new Medication().getDescription(), new Medication().getCooldown()),
    CLEAR_BAD_EFFECTS(new Tea(), "Refreshment", new Tea().getDescription(), new Tea().getCooldown()),
    INTOXICATED(new Alcohol(), "Intoxication", new Alcohol().getDescription(), new Alcohol().getCooldown()),
    CLEANER(new Bleach(), "Cleansing", new Bleach().getDescription(), new Bleach().getCooldown()),
    EXPLODING_EGG(new EasterEgg(), "Explosive Yolk", new EasterEgg().getDescription(), new EasterEgg().getCooldown()),
    FLASHBANG(new Flashbang(), "Flashbang Out!", new Flashbang().getDescription(), new Flashbang().getCooldown()),
    SWAP_SCYTHE_TO_GUN(new SwapScytheToGun(), "Switcheroo", new SwapScytheToGun().getDescription(), new SwapScytheToGun().getCooldown()),
    SWAP_GUN_TO_SCYTHE(new SwapGunToScythe(), "Switcheroo", new SwapGunToScythe().getDescription(), new SwapGunToScythe().getCooldown()),
    SWORD_BEAM(new SwordBeam(), "Blade Beam", new SwordBeam().getDescription(), new SwordBeam().getCooldown()),
    BOOMBOX_SHIELD(new BoomboxShield(), "Protecting Overtone", new BoomboxShield().getDescription(), new BoomboxShield().getCooldown()),
    ROCKET_BARRAGE(new RocketBarrage(), "Rocket Barrage", new RocketBarrage().getDescription(), new RocketBarrage().getCooldown()),
    GRENADE_THROW(new ThrowGrenade(), "Toss", new ThrowGrenade().getDescription(), new ThrowGrenade().getCooldown()),
    EVISCERATION_BEAM(new EviscerateRay(), "Eviscerating Ray", new EviscerateRay().getDescription(), new EviscerateRay().getCooldown()),
    NOXIOUS_VOID(new NoxiousVoid(), "Noxious Void", new NoxiousVoid().getDescription(), new NoxiousVoid().getCooldown()),
    LITIGATION(new Litigation(), "Litigation", new Litigation().getDescription(), new Litigation().getCooldown()),
    SOUL_SPLICER(new SoulSplicer(), "Soul Splicer", new SoulSplicer().getDescription(), new SoulSplicer().getCooldown()),
    HEAVY_HURLER(new HeavyHurler(), "Heavy Hurler", new HeavyHurler().getDescription(), new HeavyHurler().getCooldown()),
    DIVINE_RESURRECTION(new DivineResurrection(), "Divine Resurrection", new DivineResurrection().getDescription(), new DivineResurrection().getCooldown()),
    BLINK(new Blink(), "Blink", new Blink().getDescription(), new Blink().getCooldown()),
    LOTUS_ABILITY(new LotusAbility(), "Lotus of the Den", new LotusAbility().getDescription(), new LotusAbility().getCooldown()),
    SHURIKEN_STAB(new ShurikenStab(), "Stab of the Betrayer", new ShurikenStab().getDescription(), new ShurikenStab().getCooldown()),
    DRINK_CRIT_SODA(new DrinkCritSoda(), "Drink", new DrinkCritSoda().getDescription(), new DrinkCritSoda().getCooldown()),
    DAMAGE_TAX_EVASION(new DamageTaxExemption(), "Evasion", new DamageTaxExemption().getDescription(), new DamageTaxExemption().getCooldown()),
    OPEN_UPGRADE_UI(new OpenUpgradeUI(), "Buy Upgrades", new OpenUpgradeUI().getDescription(), new OpenUpgradeUI().getCooldown()),
    CLOAK(new Cloak(), "Cloak", new Cloak().getDescription(), new Cloak().getCooldown()),
    FAN_THE_HAMMER(new RevolverRapidShot(), "Fan the Hammer", new RevolverRapidShot().getDescription(), new RevolverRapidShot().getCooldown()),
    PROPOSE(new Propose(), "Propose", new Propose().getDescription(), new Propose().getCooldown()),
    ETHERWARP(new Etherwarp(), "Ether Warp", new Etherwarp().getDescription(), new Etherwarp().getCooldown()),
    ENABLE_ONE_HIT_MODE(new EnableOneHitMode(), "Armageddon", new EnableOneHitMode().getDescription(), new EnableOneHitMode().getCooldown()),
    BROWSE_CHALLENGES(new BrowseChallenges(), "Browse", new BrowseChallenges().getDescription(), new BrowseChallenges().getCooldown()),
    ;

    private final ActivatedAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    RCAbility(ActivatedAbility a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public ActivatedAbility getAbility() {
        return ability;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public static boolean hasAbility(ItemStack item, RCAbility ability) {
        if(item == null) {
            return false;
        }

        if(!item.hasItemMeta()) {
            return false;
        }

        if(item.getItemMeta() == null) {
            return false;
        }

        if(!item.getItemMeta().hasLore()) {
            return false;
        }

        ArrayList<String> lore = (ArrayList<String>) item.getItemMeta().getLore();

        for(String s : lore) {
            if(s.contains(ChatColor.YELLOW + "(Right-Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAbility(ItemStack item) {
        if(item == null) {
            return false;
        }

        if(!item.hasItemMeta()) {
            return false;
        }

        if(item.getItemMeta() == null) {
            return false;
        }

        if(!item.getItemMeta().hasLore()) {
            return false;
        }

        ArrayList<String> lore = (ArrayList<String>) item.getItemMeta().getLore();

        for(String s : lore) {
            if(s.contains(ChatColor.YELLOW + "(Right-Click) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<RCAbility> getAbilities(ItemStack item) {
        ArrayList<RCAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (RCAbility a : RCAbility.values()) {
                if (RCAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, RCAbility ability) {
        if(item == null) {
            return;
        }

        if(!item.hasItemMeta()) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(Right-Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
        String[] description = ability.getDescription().split("\n");

        for(String s : description) {
            lore.add(ChatColor.GRAY + s);
        }

        if(ability.getCooldown() > 0) {
            lore.add("");
            lore.add(color("&7- &eCooldown&7: &b" + String.format("%.2f", ability.getCooldown() / 20) + " Second(s)"));
        }

        if(!ability.getAbility().cooldownModifiable() && ability.getCooldown() > 0) {
            lore.add(color("&7- &cNon-Reducible Cooldown"));
        }
        if(ability.getAbility().oneTimeUse()) {
            lore.add(color("&7- &cOne Time Use"));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}

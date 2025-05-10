package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.InterceptMeteors;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives.*;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum PassiveAbility implements Ability {

    RADAR(new Radar(), "Enemy Spotted!", new Radar().getDescription(), new Radar().getCooldown()),
    BOOMBOX_BUFF(new BoomboxBuff(), "Charging Blitz", new BoomboxBuff().getDescription(), new BoomboxBuff().getCooldown()),
    SHURIKEN_BUFF(new ShurikenPassive(), "As Fast as the Wind", new ShurikenPassive().getDescription(), new ShurikenPassive().getCooldown()),
    SIBLING_BOND_VINE(new SiblingBondVine(), "Sibling Bond (Vine Staff)", new SiblingBondVine().getDescription(), new SiblingBondVine().getCooldown()),
    SIBLING_BOND_SHURIKEN(new SiblingBondShuriken(), "Sibling Bond (Shuriken)", new SiblingBondShuriken().getDescription(), new SiblingBondShuriken().getCooldown()),
    WAIT(new Wait(), "Passage of Time", new Wait().getDescription(), new Wait().getCooldown()),
    AQUATIC_ADAPTATION(new AquaticSustain(), "Aquatic Adaptation", new AquaticSustain().getDescription(), new AquaticSustain().getCooldown()),
    CALLISTO_ARROWS(new CallistoArrows(), "Powerful Arrows", new CallistoArrows().getDescription(), new CallistoArrows().getCooldown()),
    SCAN(new Scan(), "Echo Scan", new Scan().getDescription(), new Scan().getCooldown()),
    WIND_DEFLECT(new WindDeflect(), "Air Shot", new WindDeflect().getDescription(), new WindDeflect().getCooldown()),
    SEEKING_ARROWS(new SeekingArrows(), "Homing Arrows", new SeekingArrows().getDescription(), new SeekingArrows().getCooldown()),
    SCORCH_SURROUNDING(new Scorch(), "Radial Scorch", new Scorch().getDescription(), new Scorch().getCooldown()),
    FIRE_MEMBRANE(new FireMembrane(), "Fiery Membrane", new FireMembrane().getDescription(), new FireMembrane().getCooldown()),
    BOUNCING_ARROWS(new BouncingArrows(), "Bouncy Arrows", new BouncingArrows().getDescription(), new BouncingArrows().getCooldown()),
    EXPLOSIVE_ARROW(new ExplosiveArrows(), "Explosive Arrows", new ExplosiveArrows().getDescription(), new ExplosiveArrows().getCooldown()),
    AT_ONE_SOULS(new AtOne(), "At One With Souls", new AtOne().getDescription(), new AtOne().getCooldown()),
    SOUL_DRAIN(new SoulDrain(), "Soul Drain", new SoulDrain().getDescription(), new SoulDrain().getCooldown()),
    STOMP(new Stomp(), "Stomp", new Stomp().getDescription(), new Stomp().getCooldown()),
    DETARGET_PLAYER(new DetargetPlayer(), "Mind Manipulation", new DetargetPlayer().getDescription(), new DetargetPlayer().getCooldown()),
    SPEED_BOOST(new SpeedBoost(), "Speed Boost", new SpeedBoost().getDescription(), new SpeedBoost().getCooldown()),
    VIGOR(new Vigor(), "Vigor", new Vigor().getDescription(), new Vigor().getCooldown()),
    ;

    private final Passive ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    PassiveAbility(Passive a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public Passive getAbility() {
        return ability;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public static boolean hasAbility(ItemStack item, PassiveAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(Passive) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(Passive) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<PassiveAbility> getAbilities(ItemStack item) {
        ArrayList<PassiveAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (PassiveAbility a : PassiveAbility.values()) {
                if (PassiveAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, PassiveAbility ability) {
        if(item == null) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(Passive) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
        String[] description = ability.getDescription().split("\n");

        for(String s : description) {
            lore.add(ChatColor.GRAY + s);
        }

        if(ability.getCooldown() > 0) {
            lore.add("");
            lore.add(color("&7- &eCooldown&7: &b" + String.format("%.2f", ability.getCooldown() / 20) + " Second(s)"));
        }
        if(!ability.getAbility().cooldownModifiable() && ability.getCooldown() > 0) {
            lore.add(color("&7- &cNot affected by modifier."));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}

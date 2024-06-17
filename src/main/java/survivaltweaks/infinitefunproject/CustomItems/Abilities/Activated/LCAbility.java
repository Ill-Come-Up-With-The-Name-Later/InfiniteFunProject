package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Vine;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick.*;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.Flashbang;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.RocketBarrage;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick.SoulSplicer;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives.CallistoArrows;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives.ShurikenPassive;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum LCAbility implements Ability {

    VACCINE(new Vaccine(), "Inoculate", new Vaccine().getDescription(), new Vaccine().getCooldown()),
    SCYTHE_SWING(new ScytheSwing(), "Harvest", new ScytheSwing().getDescription(), new ScytheSwing().getCooldown()),
    SCYTHE_GUN_SHOOT(new ShootScytheGun(), "Let 'Er Rip", new ShootScytheGun().getDescription(), new ShootScytheGun().getCooldown()),
    BOOMBOX_RADIAL(new BoomboxRadial(), "Pulse Wave", new BoomboxRadial().getDescription(), new BoomboxRadial().getCooldown()),
    BAN_HAMMER_SWING(new BanHammerSwing(), "Prosecution", new BanHammerSwing().getDescription(), new BanHammerSwing().getCooldown()),
    SHOOT_ROCKET(new ShootRocketLauncher(), "Rocket Shot", new ShootRocketLauncher().getDescription(), new ShootRocketLauncher().getCooldown()),
    SHOOT_HYPER_LASER(new HyperLaserShoot(), "Hyper Ray", new HyperLaserShoot().getDescription(), new HyperLaserShoot().getCooldown()),
    FALLEN_STAR(new FallenStar(), "Fallen Star", new FallenStar().getDescription(), new FallenStar().getCooldown()),
    SWORD_SLASH(new SwordSlash(), "Slash", new SwordSlash().getDescription(), new SwordSlash().getCooldown()),
    CALAMITY_SLICE(new CalamitySlice(), "Calamity Slice", new CalamitySlice().getDescription(), new CalamitySlice().getCooldown()),
    SLING_SHOT(new SlingShot(), "Sling Shot", new SlingShot().getDescription(), new SlingShot().getCooldown()),
    REJUVENATING_RING(new RejuvenationRing(), "Rejuvenation Ring", new RejuvenationRing().getDescription(), new RejuvenationRing().getCooldown()),
    DUAL_SWING(new DualSwing(), "Dual Swing", new DualSwing().getDescription(), new DualSwing().getCooldown()),
    SHOOT_PETALS(new VinestaffShoot(), "A Thousand Petals", new VinestaffShoot().getDescription(), new VinestaffShoot().getCooldown()),
    SHURIKEN_THROW(new ShurikenThrow(), "Stars of the Storm", new ShurikenThrow().getDescription(), new ShurikenThrow().getCooldown()),
    SHOTGUN_FIRE(new ShootDoubleBarrel(), "Shoot", new ShootDoubleBarrel().getDescription(), new ShootDoubleBarrel().getCooldown()),
    EXECUTION_THREAD(new ExecutionThread(), "Executioner's Thread", new ExecutionThread().getDescription(), new ExecutionThread().getCooldown()),
    SHOOT_REVOLVER(new ShootRevolver(), "Fire", new ShootRevolver().getDescription(), new ShootRevolver().getCooldown()),
    SHOOT_MEDKIT_REVOLVER(new MedkitRevolver(), "Curing Cartridge", new MedkitRevolver().getDescription(), new MedkitRevolver().getCooldown()),
    SHOOT_FLAME(new Flamethrower(), "Flame Jets", new Flamethrower().getDescription(), new Flamethrower().getCooldown()),
    SHOOT_LASER_GUN(new ShootLaserGun(), "Laser Shot", new ShootLaserGun().getDescription(), new ShootLaserGun().getCooldown()),
    CALLISTO_EXPLOSIVE_SHOT(new CallistoExplosiveArrow(), "Explosive Arrow", new CallistoExplosiveArrow().getDescription(),
            new CallistoExplosiveArrow().getCooldown()),
    TRITON_BURST(new TritonBurst(), "Rapid Fire", new TritonBurst().getDescription(), new TritonBurst().getCooldown()),
    RAILGUN_SHOT(new ShootRailGun(), "Rail Blast", new ShootRailGun().getDescription(), new ShootRailGun().getCooldown()),
    ;

    private final ActivatedAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    LCAbility(ActivatedAbility a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public ActivatedAbility getAbility() {
        return ability;
    }

    public static boolean hasAbility(ItemStack item, LCAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(Left-Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(Left-Click) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<LCAbility> getAbilities(ItemStack item) {
        ArrayList<LCAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (LCAbility a : LCAbility.values()) {
                if (LCAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, LCAbility ability) {
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
        lore.add(ChatColor.YELLOW + "(Left-Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
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

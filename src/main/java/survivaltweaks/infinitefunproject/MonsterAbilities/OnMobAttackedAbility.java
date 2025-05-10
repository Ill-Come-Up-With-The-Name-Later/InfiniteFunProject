package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.entity.Monster;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility.*;

public enum OnMobAttackedAbility implements Ability {

    WEB_ATTACKER(new WebAttacker()),
    LIGHTNING_ATTACKER(new LightningStrikeAttacker()),
    FORM_ARMOR_FROM_ATTACK(new BuildArmor()),
    POISON_ATTACKER(new PoisonAttacker()),
    THORN_ATTACKER(new ThornAttacker()),
    IGNITE_ATTACKER(new IgniteAttacker()),
    EXPLODE_ATTACKER(new ExplodeAttacker()),
    SHRINK_FROM_ATTACK(new ShrinkFromAttack()),
    RETALIATE_CRITS(new RetaliationCrits()),
    ;

    public final MonsterAbility monsterAbility;

    OnMobAttackedAbility(MonsterAbility ability) {
        monsterAbility = ability;
    }

    public MonsterAbility getMonsterAbility() {
        return monsterAbility;
    }

    public String getAbilityName() {
        return monsterAbility.abilityName();
    }

    public static void addAbility(Monster monster, OnMobAttackedAbility ability) {
        if(!hasAbility(monster, ability)) {
            monster.setMetadata(ability.getAbilityName(), new MobAbilityData(ability.getAbilityName()));
        }
    }

    public static boolean hasAbility(Monster monster, OnMobAttackedAbility ability) {
        return !monster.getMetadata(ability.getAbilityName()).isEmpty();
    }

    public static boolean hasAbility(Monster monster) {
        for(OnMobAttackedAbility ability : OnMobAttackedAbility.values()) {
            if(hasAbility(monster, ability)) {
                return true;
            }
        }

        return false;
    }
}
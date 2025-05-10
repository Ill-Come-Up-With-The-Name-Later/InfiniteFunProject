package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.entity.Monster;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility.*;

public enum OnMobAttacksAbility implements Ability {

    WEB_TARGET(new WebTarget()),
    GAIN_HEALTH_FROM_TARGET(new GainHealth()),
    WIND_CHARGE_TARGET(new WindCharge()),
    SCORCH_TARGET(new ScorchTarget()),
    VENOM_TARGET(new VenomTarget()),
    ELECTRIC_STRIKE_TARGET(new ElectricStrike()),
    STUN_TARGET(new StunTarget()),
    GROW_FROM_ATTACK(new GrowOffAttack()),
    MINI_CRIT_COMBO(new MiniCritCombo()),
    ;

    public final MonsterAbility monsterAbility;

    OnMobAttacksAbility(MonsterAbility ability) {
        monsterAbility = ability;
    }

    public MonsterAbility getMonsterAbility() {
        return monsterAbility;
    }

    public String getAbilityName() {
        return monsterAbility.abilityName();
    }

    public static void addAbility(Monster monster, OnMobAttacksAbility ability) {
        if(!hasAbility(monster, ability)) {
            monster.setMetadata(ability.getAbilityName(), new MobAbilityData(ability.getAbilityName()));
        }
    }

    public static boolean hasAbility(Monster monster, OnMobAttacksAbility ability) {
        return !monster.getMetadata(ability.getAbilityName()).isEmpty();
    }

    public static boolean hasAbility(Monster monster) {
        for(OnMobAttacksAbility ability : OnMobAttacksAbility.values()) {
            if(hasAbility(monster, ability)) {
                return true;
            }
        }

        return false;
    }

}

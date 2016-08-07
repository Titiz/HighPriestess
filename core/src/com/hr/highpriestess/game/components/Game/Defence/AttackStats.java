package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;

/**
 * Created by Titas on 2016-08-06.
 */


public class AttackStats extends Component {
    float damage;
    public AttackSpeed attackSpeed;
    public Range range;
    public DamageType damageType;
    public ArmorType armorType;
    public MagicalArmorType magicalArmorType;


    public enum DamageType {
        PHYSICAL,
        MAGICAL
    }

    public enum ArmorType {
        NONE,
        LIGHT,
        MEDIUM,
        HEAVY,
    }

    public enum MagicalArmorType {
        NONE,
        LIGHT,
        MEDIUM,
        HEAVY,
    }

    public enum Range {
        MELEE,
        RANGED_CLOSE,
        RANGED_MIDDLE,
        RANGED_FAR,
    }

    public enum AttackSpeed {
        SLOW,
        NORMAL,
        FAST
    }


    public AttackStats (AttackStats.DamageType damageType,
                        float damage,
                        AttackStats.AttackSpeed attackSpeed,
                        AttackStats.Range range,
                        AttackStats.ArmorType armorType,
                        AttackStats.MagicalArmorType magicalArmorType) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.damageType = damageType;
        this.armorType = armorType;
        this.magicalArmorType = magicalArmorType;
    }
}

package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;

/**
 * Created by Titas on 2016-08-03.
 */
public class Health extends Component {


    private final int maxHealth;
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Health(int health) {
        setHealth(health);
        maxHealth = health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }
}

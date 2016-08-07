package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;

/**
 * Created by Titas on 2016-08-06.
 */
public class Attacking extends Component {
    private int victim;

    public int getVictim() {
        return victim;
    }

    public void setVictim(int victim) {
        this.victim = victim;
    }
}

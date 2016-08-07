package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;
import com.artemis.ComponentManager;

/**
 * Created by Titas on 2016-08-06.
 */
public class Target extends Component {
    private int target = -1;

    public boolean mouseTarget = false;

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}

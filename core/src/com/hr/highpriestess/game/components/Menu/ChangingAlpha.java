package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-20.
 */
public class ChangingAlpha extends Component {
    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private boolean down = false;



}

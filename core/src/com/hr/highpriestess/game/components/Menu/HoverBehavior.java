package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-19.
 */
public class HoverBehavior extends Component {

    public void incrementTicks() {
        ticks+=1;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    private int ticks;

    private boolean hovered = false;

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}

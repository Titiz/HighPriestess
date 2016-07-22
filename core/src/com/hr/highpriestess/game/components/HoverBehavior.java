package com.hr.highpriestess.game.components;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-19.
 */
public class HoverBehavior extends Component {
    private boolean hovered = false;

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}

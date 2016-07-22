package com.hr.highpriestess.game.components;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-21.
 */
public class ClickOpen extends Component {

    String identifier;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    private boolean clicked = false;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}

package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;

/**
 * Created by Titas on 2016-08-02.
 */
public class Selectable extends Component {
    boolean selected;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setSelectionLayer(int selectionLayer) {
        this.selectionLayer = selectionLayer;
    }

    public int getSelectionLayer() {
        return selectionLayer;
    }

    public boolean isSelected() {
        return selected;
    }

    int selectionLayer;

    public Selectable(int selectionLayer) {
        this.selectionLayer = selectionLayer;
    }
}

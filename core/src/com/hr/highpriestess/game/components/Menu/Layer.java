package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-23.
 */
public class Layer extends Component {

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {

        return layer;
    }

    int layer;
    int nextActiveLayer;

    public Layer(int layer, int nextActiveLayer) {
        this.layer = layer;
        this.nextActiveLayer = nextActiveLayer;
    }

    public int getNextActiveLayer() {
        return nextActiveLayer;
    }
}

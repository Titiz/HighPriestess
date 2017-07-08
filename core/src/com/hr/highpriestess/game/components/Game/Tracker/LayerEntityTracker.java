package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;

import java.util.HashMap;

/**
 * Created by Titas on 2017-07-06.
 */
public class LayerEntityTracker extends Component {

    public HashMap<G.Layer, Array<Integer>> LayerMap; // Layers to a list containing all entities in that render layer.

    public LayerEntityTracker() {
        reset();
    }

    public void reset() {
        LayerMap = new HashMap<G.Layer, Array<Integer>>();
    }

    public void add(int entity, G.Layer layer) {
        if (!LayerMap.containsKey(layer)) {
            Array<Integer> new_array = new Array<Integer>();
            LayerMap.put(layer, new_array);
        }
        LayerMap.get(layer).add(entity);
    }
}

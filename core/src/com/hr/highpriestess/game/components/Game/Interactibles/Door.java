package com.hr.highpriestess.game.components.Game.Interactibles;

import com.artemis.Component;

/**
 * Created by Titas on 2017-07-07.
 */
public class Door extends Component {

    public String nextMap;

    public Door(String nextMap) {
        this.nextMap = nextMap;
    }
}

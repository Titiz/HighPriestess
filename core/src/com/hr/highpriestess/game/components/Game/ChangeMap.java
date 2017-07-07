package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-28.
 */
public class ChangeMap extends Component {
    private String nextMap;

    public ChangeMap() {}

    public ChangeMap(String nextMap){
        this.nextMap = nextMap;
    }

    public String getNextMap() {
        return nextMap;
    }

    public void setNextMap(String nextMap) {
        this.nextMap = nextMap;
    }
}

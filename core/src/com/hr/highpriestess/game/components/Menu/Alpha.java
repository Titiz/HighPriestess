package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-19.
 */
public class Alpha extends Component {
    public float getAlpha() {
        return alpha;
    }

    private float alpha = 1;

    public void changeAlpha(float change) {
        alpha += change;
    }

}

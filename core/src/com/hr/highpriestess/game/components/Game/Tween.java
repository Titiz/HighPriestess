package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Titas on 2017-08-05.
 */
public class Tween extends Component {

    public Vector2 destination;

    public Tween() {
        destination = new Vector2();
    }
    public Tween(int x, int y) {
        destination = new Vector2();
        this.destination.set(x, y);
    }
}

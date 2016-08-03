package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Titas on 2016-07-31.
 */
public class Anim extends Component {

    public boolean flippedX;

    public static enum Layer
    {
        ENEMY,
        PLAYER,

        DEFAULT,
    }

    public String id;
    public Layer layer = Layer.DEFAULT;


    public float speed = 1;
    public float age = 0;
    public float scale = 1;
    public float rotation = 0;
    public final Color color = new Color(1, 1, 1, 1);

    public Anim(String id, Layer layer) {
        this.id = id;
        this.layer = layer;
    }



}

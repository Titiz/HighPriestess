package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

import java.util.EnumMap;
import java.util.List;

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

    public static enum Animations
    {
        IDLE,
        WALKING,


    }



    public String activeId;
    public Layer layer = Layer.DEFAULT;
    public String ids[]; // This will store all of the animations.


    public float speed = 1;
    public float age = 0;
    public float scale = 1;
    public float rotation = 0;
    public final Color color = new Color(1, 1, 1, 1);

    public Anim(Layer layer, String... ids) {
        activeId = ids[0];
        this.ids = ids;
        this.layer = layer;
    }



}

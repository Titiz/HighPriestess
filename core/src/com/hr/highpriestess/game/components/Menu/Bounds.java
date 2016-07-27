package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;




public class Bounds extends Component {

    public float x;
    public float y;
    public float width;
    public float height;
    public float centerX;
    public float centerY;

    public Bounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.centerX = x+width/2;
        this.centerY = y+height/2;
    }

}


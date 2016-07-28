package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Titas on 2016-07-28.
 */
public class ImageComponent extends Component {
    Texture img;

    public ImageComponent(Texture img) {
        this.img = img;
    }

    public Texture getImg() {
        return img;
    }
}

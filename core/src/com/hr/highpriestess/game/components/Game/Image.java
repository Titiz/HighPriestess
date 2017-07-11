package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.hr.highpriestess.G;

/**
 * Created by Titas on 2017-07-11.
 */
public class Image extends Component {

    public String imageName; // All we really need for this component is what is its image name.
    public G.Layer layer = G.Layer.DEFAULT;

    public Image(){};

    public Image(String imageName, G.Layer layer){
        this.imageName = imageName;
        this.layer = layer;
    };
}

package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;

/**
 * Created by Titas on 2016-07-28.
 */
public class CharAnimations extends Component {
    private HashMap<String, Animation> anims = new HashMap<String, Animation>();

    public CharAnimations(HashMap<String, Animation> anims){
        this.anims = anims;
    }
}

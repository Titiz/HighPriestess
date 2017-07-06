package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.hr.highpriestess.G;

import java.util.HashMap;

/**
 * Created by Titas on 2017-07-06.
 */
public class ShaderHolder extends Component {

    public HashMap<G.Layer, ShaderProgram> ShaderMap; // maps layers to their current shader

    public ShaderHolder() {
        ShaderMap = new HashMap<G.Layer, ShaderProgram>();
    }

}

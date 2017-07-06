package com.hr.highpriestess.game.components.Game.Shaders;

import com.artemis.Component;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.hr.highpriestess.G;

import java.util.HashMap;

/**
 * Created by Titas on 2017-07-06.
 */
public class ShaderHolder extends Component {

    HashMap<G.Layer, ShaderProgram> ShaderMap; // maps layers to their current shader
}

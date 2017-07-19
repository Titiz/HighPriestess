package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Titas on 2017-07-15.
 */
public class AnimationTracker extends Component {
    public HashMap<String, HashSet<String>> animationAtlasMap;
    public HashMap<String, Integer> atlasUseMap;

    public AnimationTracker() {
        animationAtlasMap = new HashMap<String, HashSet<String>>();
        atlasUseMap = new HashMap<String, Integer>();
    }
}

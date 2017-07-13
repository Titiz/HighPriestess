package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Titas on 2017-07-12.
 */
public class NeighborMapTracker extends Component {

    public HashMap<String, Array<String>> neighborMapAssets; // maps and their resources
    public HashSet<String> currentNeighborNames;
    public HashSet<String> lastMapNeighborNames;
    public HashMap<String, Integer> assetUseCounter; //counts in how many of the neighboring maps (including the current map) each asset is used.
    public String activeMapName;


    public NeighborMapTracker() {
        neighborMapAssets = new HashMap<String, Array<String>>();
        currentNeighborNames = new HashSet<String>();
        assetUseCounter = new HashMap<String, Integer>();
    }



    public void reset() {
        neighborMapAssets.clear();
        currentNeighborNames.clear();
    }







}

package com.hr.highpriestess;


import com.artemis.Entity;
import com.artemis.World;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.screens.MenuScreen;
import com.sun.scenario.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores important constants which can be accessed from anywhere in the  game
 */

public class G {


    public static final int CELL_SIZE = 16;
    public static final int UNIT_SIZE = 20;



    public static AssetSystem assetSystem;
    public static World gameWorld;
    public static World menuWorld;


    public static World defenceWorld;
    public static int[][] defenceGrid;



    public static HighPriestessMain game;

}

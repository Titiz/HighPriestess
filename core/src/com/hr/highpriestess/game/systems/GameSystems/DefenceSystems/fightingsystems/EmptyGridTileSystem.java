package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Created by Titas on 2016-08-09.
 */
public class EmptyGridTileSystem extends IteratingSystem {

    String TAG = EmptyGridTileSystem.class.getName();

    ComponentMapper<MoveToDestination> moveToDestinationCm;

    public EmptyGridTileSystem() {
        super(Aspect.all(MoveToDestination.class, Bounds.class));
    }

    @Override
    protected void process(int e) {
        MoveToDestination move = moveToDestinationCm.get(e);
        int [] finalPos = getEmptyGridTile(move.getFinalX(), move.getFinalY(), e);
        if (move.getFinalX() != finalPos[0] && move.getFinalY() != finalPos[1])
            moveToDestinationCm.get(e).setFinalXY(finalPos[0], finalPos[1]);

    }


    // Returns the location of an empty grid tile
    private int [] getEmptyGridTile(float x, float y, int e) {
        int [] position = {0, 0};
        int i = (int) ((x+G.UNIT_SIZE/2) / G.UNIT_SIZE);
        int j = (int) ((y+G.UNIT_SIZE/2) / G.UNIT_SIZE);
        if (G.defenceGrid[i][j] == -1 || G.defenceGrid[i][j] == e) {

            position[0] = i * G.UNIT_SIZE;
            position[1] = j * G.UNIT_SIZE;
            if (e == 1 || e == 9) {
                Gdx.app.debug(TAG, "i: " + i + " j: " + j);
                Gdx.app.debug(TAG, "x: " + x + " y: " + y);

            }

        }
        else {
            position = getEmptyGridTile(x+G.UNIT_SIZE, y, e);
        }
        return position;

    }
}

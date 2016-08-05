package com.hr.highpriestess.game.components.Game.Defence;

import com.artemis.Component;
import com.artemis.ComponentMapper;



/**
 * Created by Titas on 2016-08-05.
 */
public class MoveToDestination extends Component {

    private float finalX;
    private float finalY;
    public boolean changedFinalPos;

    public float getFinalX() {
        return finalX;
    }

    public float getFinalY() {
        return finalY;
    }

    public void setFinalXY(float finalX, float finalY) {
        this.finalX = finalX;
        this.finalY = finalY;
        changedFinalPos = true;
    }



}

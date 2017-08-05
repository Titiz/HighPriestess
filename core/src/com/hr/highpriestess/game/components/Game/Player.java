package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;
import com.artemis.Entity;

/**
 * Created by Titas on 2016-07-28.
 */
public class Player extends Component {

    public Entity collidingEntity; //To see with what entity the player is currently colliding
    public Boolean isActiveButtonClicked; //To see whether the activate button is clicked
    public enum States {
        DEFAULT,
        DIALOGUE,
        TWEEN,
    }
    public States currentState = States.DEFAULT;


}

package com.hr.highpriestess.game.components.Game.Interactibles;

import com.artemis.Component;

/**
 * Created by Titas on 2017-07-07.
 */
public class Dialogue extends Component {


    private String conversation;
    public Boolean stopsTime;

    public Dialogue() {
        conversation = "High Priestess, these are not times in which the gods can help us. They have surely abandoned us.";
        stopsTime = false;
    }

    public String getConversation() {
        return conversation;
    }
}

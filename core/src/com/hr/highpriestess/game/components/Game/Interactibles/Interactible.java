package com.hr.highpriestess.game.components.Game.Interactibles;

import com.artemis.Component;

/**
 * Created by Titas on 2017-07-07.
 */
public class Interactible extends Component {

    public String label; //What is used to make little text to tell the user what action can be taken.


    public Interactible() {}

    public Interactible(String label) {
        this.label = label;
    }


}

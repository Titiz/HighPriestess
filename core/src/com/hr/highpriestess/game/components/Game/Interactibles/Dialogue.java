package com.hr.highpriestess.game.components.Game.Interactibles;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.util.Node;

/**
 * Created by Titas on 2017-07-07.
 */
public class Dialogue extends Component {


    private String dialogueFile;


    public Dialogue() {
        dialogueFile = "filename.xml";
    }

    public String getDialogueFile() {
        return dialogueFile;
    }
}

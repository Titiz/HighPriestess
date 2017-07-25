package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;

/**
 * Created by Titas on 2017-07-19.
 */
public class DialogueTracker extends Component
{

    public int dialogueEntity;
    public boolean inDialogue;
    public int dialoguePointer;

    public void startNewConversation(int dialogueEntity) {
        this.dialogueEntity = dialogueEntity;
        this.inDialogue = true;
        this.dialoguePointer = 0;
    }


    public DialogueTracker() {
    }
}

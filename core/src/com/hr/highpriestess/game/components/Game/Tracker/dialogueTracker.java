package com.hr.highpriestess.game.components.Game.Tracker;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.util.Node;

/**
 * Created by Titas on 2017-07-19.
 */
public class DialogueTracker extends Component
{

    public int dialogueEntity;
    public boolean inDialogue;
    public int dialoguePointer;
    public int currentNode = 0;
    public Array<Node> nodes;
    public String currentString = "";
    public Boolean isMakingDecision = false;
    public String[] decisions;

    public int currentDecisionId;

    enum  DialogueStates {
        DECIDING,
        LISTENING
    }

    public DialogueStates dialogueState;

    public String getCurrentDecision() {
        return decisions[currentDecisionId];
    }



    public void startNewConversation(int dialogueEntity) {
        this.dialogueEntity = dialogueEntity;
        this.inDialogue = true;
        this.dialoguePointer = 0;
        this.currentNode = 0;
        this.nodes = new Array<Node>();
        this.currentString = "";
        this.decisions = null;
    }

    public void continueConversation(int dialogueEntity, int nextNode) {
        this.dialogueEntity = dialogueEntity;
        this.inDialogue = true;
        this.dialoguePointer = 0;
        this.currentNode = 0;
        currentString = "";
        this.decisions = null;
        currentNode = nextNode;
    }


    public Node getCurrentNode() {
        return this.nodes.get(currentNode);
    }

    public DialogueTracker() {
    }


    public int getNeighborId(int id, int neighborIndex) {
        return this.nodes.get(id).neighbors[neighborIndex];
    }
    public int getNeighborId(int id) {
        return getNeighborId(id, 0);
    }

    public Node getNeighbor(int id, int neighborIndex) {
        return nodes.get(this.getNeighborId(id, neighborIndex));
    }
    public Node getNeighbor(int id) {
        return getNeighbor(id, 0);
    }
}

package com.hr.highpriestess.game.util.Nodes;

import com.hr.highpriestess.game.util.Nodes.Node;

/**
 * Used for dialogue
 */
public class DialogueNode extends Node {

    public final int tag;
    public final String text;
    public final String speaker;

    public DialogueNode(int tag, String text, String speaker, int[] neighbors) {
        super(neighbors);
        this.tag = tag;
        this.speaker = speaker;
        this.text = text;

    }



}


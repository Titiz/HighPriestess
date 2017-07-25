package com.hr.highpriestess.game.util;

/**
 * Used for dialogue
 */
public class Node {

    public final int tag;
    public final String text;
    public final String speaker;
    public final int [] neighbors;

    public Node(int tag, String text, String speaker, int[] neighbors) {
        this.tag = tag;
        this.speaker = speaker;
        this.text = text;
        this.neighbors = neighbors;
    }
}


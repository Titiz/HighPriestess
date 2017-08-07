package com.hr.highpriestess.game.util.Nodes;

/**
 * Created by Titas on 2017-08-06.
 */
public class TweenNode extends Node {


    public String actor;
    public String destination;
    public Boolean stopsDialogue;
    public Boolean stopsNextTween;


    public TweenNode(String actor, String destination, int[] neighbors, boolean stopsDialogue, Boolean stopsNextTween) {
        super(neighbors);
        this.destination = destination;
        this.actor = actor;
        this.stopsDialogue= stopsDialogue;
        this.stopsNextTween = stopsNextTween;
    }
}

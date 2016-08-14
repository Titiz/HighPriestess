package com.hr.highpriestess.game.components.Game.Defence.Movement.NodeMovement;

import com.artemis.Component;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;

/**
 * Created by Titas on 2016-08-12.
 */
public class MoveThroughPath extends Component {
    public GraphPath path;

    int currentNodeIndex = -1;

    int startingNode;
    int endingNode;

    public int getEndingNode() {
        return endingNode;
    }

    public void setEndingNode(int endingNode) {
        this.endingNode = endingNode;
    }

    public int getStartingNode() {

        return startingNode;
    }

    public void setStartingNode(int startingNode) {
        this.startingNode = startingNode;
    }



    public void setupNodes(int startingNode, int endingNode) {
        this.startingNode = startingNode;
        this.endingNode = endingNode;
    }


    public void incrementNodeIndex() {
        this.setCurrentNodeIndex(getCurrentNodeIndex() + 1);
    }

    public int getCurrentNodeIndex() {
        return currentNodeIndex;
    }

    public void setCurrentNodeIndex(int currentNodeIndex) {
        this.currentNodeIndex = currentNodeIndex;
    }

    public MoveThroughPath() {
        this.path = new DefaultGraphPath();
    }

    public GraphPath getPath() {
        return path;
    }

    public void setPath(GraphPath path) {
        this.path = path;
    }
}

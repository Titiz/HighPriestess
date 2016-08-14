package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.math.Vector2;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.Pathfinding.MyPathFindingUtils;
import com.hr.highpriestess.game.components.Game.Defence.Movement.NodeMovement.MoveThroughPath;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Created by Titas on 2016-08-12.
 */
public class MoveThroughPathSystem extends IteratingSystem {

    public MoveThroughPathSystem() {
        super(Aspect.all(MoveThroughPath.class));
    }

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentMapper<MoveThroughPath> moveThroughPathCm;

    @Override
    protected void process(int e) {
        MoveThroughPath moveThroughPath = moveThroughPathCm.get(e);
        // we move through the map if it has more than 0 nodes in it.
        if (moveThroughPath.getPath().getCount() != 0) {
            // if it's the last note we finish the path.
            int lastIndex = moveThroughPath.getPath().getCount()-2;

            if (moveThroughPath.getCurrentNodeIndex() == lastIndex) {
                moveThroughPath.setPath(new DefaultGraphPath());
                moveThroughPath.setCurrentNodeIndex(-1);
                moveThroughPathCm.remove(e);
            } else
            // otherwise we move along it
            {
                if (!moveToDestinationCm.has(e)){
                    moveThroughPath.incrementNodeIndex();
                    int index =  moveThroughPath.getCurrentNodeIndex();
                    moveToDestinationCm.create(e);
                    int toNode = (Integer) moveThroughPath.getPath().get(index+1);
                    Vector2 toNodeij = MyPathFindingUtils.getNodeIndices(toNode, G.defenceGrid);
                    // the entity will only move to the node if it's a -1, if not then we have to remake the path.
                    if (G.defenceGrid[(int) toNodeij.y][(int) toNodeij.x] == -1) {
                        // we set the path to not be a -1 so that other entities don't pass through there.
                        G.defenceGrid[(int) toNodeij.y][(int) toNodeij.x] = -2;
                        Vector2 toNodeXY = MyPathFindingUtils.getCoordsFromNode(toNode, G.defenceGrid);
                        moveToDestinationCm.get(e).setFinalXY(toNodeXY.x, toNodeXY.y);
                        moveThroughPath.setupNodes(toNode, moveThroughPath.getEndingNode());
                        moveThroughPath.setCurrentNodeIndex(-1);
                        moveThroughPath.path.clear();
                    } else {
                        moveThroughPathCm.get(e).path.clear();
                        moveToDestinationCm.remove(e);
                    }
                    }
                }
            }
        }
    }


package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.Pathfinding.MyDiagonalDistanceHeuristic;
import com.hr.highpriestess.game.Pathfinding.MyGraph;
import com.hr.highpriestess.game.Pathfinding.MyIndexAStarPathFinder;
import com.hr.highpriestess.game.components.Game.Defence.Movement.NodeMovement.MoveThroughPath;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Menu.Bounds;

import java.util.Arrays;

/**
 * Class which makes a path from one position to another
 * using nodes.
 */
public class CreatePathSystem extends IteratingSystem {

    public CreatePathSystem() {
        super(Aspect.all(MoveThroughPath.class));
    }

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentMapper<MoveThroughPath> moveThroughPathCm;
    int[][] grid;
    IndexedGraph graph;
    Heuristic heuristic;



    protected void begin() {
        // we check if the grid has altered
        if (!Arrays.deepEquals(G.defenceGrid, grid)) {
            this.grid = G.defenceGrid;
            graph = new MyGraph(grid);
            heuristic = new MyDiagonalDistanceHeuristic(grid);
        }
    }

    @Override
    protected void process(int e) {
        MoveThroughPath entity = moveThroughPathCm.get(e);
        GraphPath path = entity.getPath();
        // we create a path if the path has zero nodes.
        if (path.getCount() == 0) {
            System.out.println("YH");
            MyIndexAStarPathFinder pathFinder = new MyIndexAStarPathFinder(graph);
            boolean found = pathFinder.searchNodePath(entity.getStartingNode(), entity.getEndingNode(), heuristic, path);
            System.out.println("PATHFOUND: " + found);
            // we print out a debug statement to know from what node to what node the path goes.
            if (found)
                System.out.println("START: " + path.get(0) + " END: " + path.get(path.getCount()-1));
            // we remove the component in case it cannot be found
            else
                moveThroughPathCm.remove(e);
        }
    }
}

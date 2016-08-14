package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.Movement.NodeMovement.MoveThroughPath;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

import java.util.Arrays;

/**
 * Class that will track the grid of the defence map
 * and see where all units are.
 */
public class GridSystem extends IteratingSystem {

    private final int CELL_SIZE = G.UNIT_SIZE;
    final int WIDTH = 100;
    final int HEIGHT = 30;
    int [][] Grid;


    boolean showDebugGrid = true;
    CameraSystem cameraSystem;
    ShapeRenderer shapeRenderer = new ShapeRenderer();



    GroupManager groupManager;
    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<MoveThroughPath> moveThroughPathCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentMapper<Selectable> selectableCm;
    int counter = 0;



    public GridSystem() {
        super(Aspect.all(Bounds.class, Kinematics.class));
    }

    @Override
    protected void begin() {
        Grid = new int[WIDTH][HEIGHT];
        for (int[] col : Grid) {
            Arrays.fill(col, -1);
        }
    }

    @Override
    protected void process(int e) {
        Bounds bounds = boundsCm.get(e);
        try {
            int j = (int) bounds.x / CELL_SIZE;
            int i = (int) bounds.y / CELL_SIZE;
            Grid[i][j] = e;

        } catch (Exception ex) {

        }


    }

    private void snapToGridPosition() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j< HEIGHT; j++) {
                int e = Grid[i][j];
                if (e != -1) {
                    float destinationX = i * CELL_SIZE;
                    float destinationY = j * CELL_SIZE;
                    if (!moveToDestinationCm.has(e) &&
                            boundsCm.get(e).x != destinationX &&
                            boundsCm.get(e).y != destinationY) {
                        moveToDestinationCm.create(e);
                        MoveToDestination mov = moveToDestinationCm.get(e);
                        mov.setFinalXY(destinationX, destinationY);
                    }
                }
            }
        }
    }


    private void showDebugGrid(boolean isShowing) {
        if (isShowing) {
            shapeRenderer.setProjectionMatrix(cameraSystem.camera.combined);
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j< HEIGHT; j++) {
                    shapeRenderer.setColor(Color.BLUE);
                    shapeRenderer.rect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
            shapeRenderer.end();
        }
    }


    @Override
    protected void end() {
        G.defenceGrid = Grid;
        showDebugGrid(showDebugGrid);
        //snapToGridPosition();
        counter += 1;
        if (counter %  300 == 0)
            System.out.println(Arrays.deepToString(Grid));

    }



}

package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Bounds;
import com.hr.highpriestess.game.components.ClickOpen;
import com.hr.highpriestess.game.components.HoverBehavior;

/**
 * Created by Titas on 2016-07-21.
 */

public class OpenOnClick extends IteratingSystem {

    CollisionSystem collisionSystem;
    ComponentMapper<ClickOpen> clickOpenCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverBehavior> hovCm;


    public OpenOnClick() {
        super(Aspect.all(ClickOpen.class));
    }

    protected void process(int e){
        if (hovCm.get(e).isHovered() && !clickOpenCm.get(e).isClicked()){
            if (Gdx.input.isButtonPressed(0)) {
                System.out.println(clickOpenCm.get(e).isClicked());
                clickOpenCm.get(e).setClicked(true);
                clickOpenCm.get(e).setIdentifier("NEXT");
                System.out.println(clickOpenCm.get(e).getIdentifier());

            }
        } else if (clickOpenCm.get(e).isClicked() && !hovCm.get(e).isHovered()) {
            clickOpenCm.get(e).setClicked(false);
        }
    }


}

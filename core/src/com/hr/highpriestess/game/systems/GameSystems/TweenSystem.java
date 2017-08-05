package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tween;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Created by Titas on 2017-08-05.
 */
public class TweenSystem extends IteratingSystem {

    ComponentMapper<Tween> tweenCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Player> playerCm;

    TagManager tagManager;

    String TAG = TweenSystem.class.getName();

    public TweenSystem() {
        super(Aspect.all(Tween.class));
    }

    @Override
    protected void inserted(int e) {
        Gdx.app.debug(TAG, "the following entity starting a tween: " + e);
    }

    @Override
    protected void process(int e) {
        Tween tween = tweenCm.get(e);
        Bounds bounds = boundsCm.get(e);
        Kinematics kin = kinCm.get(e);
        if (playerCm.has(e)) {
            Player player = playerCm.get(e);
            player.currentState = Player.States.TWEEN;
        }
        float v_x = kin.getMax_vx(), v_y = kin.getMax_vy();


        float differenceX = tween.destination.x - bounds.x,
                differenceY = tween.destination.y - bounds.y;

        if (differenceX < 0) {
            v_x *= -1;
        }

        if (differenceY < 0) {
            v_y *= -1;
        }

        if (differenceX < v_x) {
            bounds.setX(tween.destination.x);
            kin.setVx(0);
        } else {
            kin.setVx(v_x);

        }

        if (differenceY < v_y) {
            bounds.setY(tween.destination.y);
            kin.setVy(0);
        } else {
            kin.setVy(v_y);
        }

        if (tween.destination.y == bounds.y && tween.destination.x == bounds.x)  {
            Gdx.app.debug(TAG, "the following entity finished a tween: " + e);
            tweenCm.remove(e);
        }

    }
}

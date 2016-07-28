package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-28.
 */
public class Kinematics extends Component {

    private float vx;
    private float max_vx;
    private float vy;
    private float max_vy;
    private float ax;
    private float ay;


    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getMax_vx() {
        return max_vx;
    }

    public void setMax_vx(float max_vx) {
        this.max_vx = max_vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getMax_vy() {
        return max_vy;
    }

    public void setMax_vy(float max_vy) {
        this.max_vy = max_vy;
    }

    public float getAx() {
        return ax;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public float getAy() {
        return ay;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }

    public void change_vx () {
        this.vx += this.ax;
    }

    public void change_vy () {
        this.vy += this.ay;
    }


}


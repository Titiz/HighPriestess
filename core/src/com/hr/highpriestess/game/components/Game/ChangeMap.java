package com.hr.highpriestess.game.components.Game;

import com.artemis.Component;

/**
 * Created by Titas on 2016-07-28.
 */
public class ChangeMap extends Component {

    private String nextMap; // What map we are going to
    private String enteringGateName; // Name of the gate
    private String exitGateName; // Where on the map will the player appear?

    public ChangeMap() {}


/*    public ChangeMap(String nextMap, String enteringGateName){
        this.nextMap = nextMap;
        this.enteringGateName = enteringGateName;
    }*/



    public ChangeMap(String nextMap, String enteringGateName, String exitGateName){
        this.nextMap = nextMap;
        this.enteringGateName = enteringGateName;
        this.exitGateName = exitGateName;
    }

    public String getNextMap() {
        return nextMap;
    }

    public void setNextMap(String nextMap) {
        this.nextMap = nextMap;
    }

    public String getEnteringGateName() {
        return enteringGateName;
    }

    public void setEnteringGateName(String enteringGateName) {
        this.enteringGateName = enteringGateName;
    }

    public String getExitGateName() {
        return exitGateName;
    }
}

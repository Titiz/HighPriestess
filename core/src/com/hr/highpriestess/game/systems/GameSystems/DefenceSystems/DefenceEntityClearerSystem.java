package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;


import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;


/**
 * Created by Titas on 2016-07-29.
 */






/**
 * Created by Titas on 2016-07-29.
 */
public class DefenceEntityClearerSystem extends EntityClearerSystem {



    public void clearEntities() {

        deleteEntities("player", tagManager);
        deleteEntities("enemy", groupManager);
        deleteEntities("gate", groupManager );



    }


    @Override
    protected void processSystem() {

    }
}

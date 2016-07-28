package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;


public class ChangeMapSystem extends BaseEntitySystem {


    private GroupManager groupManager;
    private TagManager tagManager;

    private MapSystem mapSystem;
    private CollisionSystem collisionSystem;
    private ComponentMapper<ChangeMap> changeMapCm;
    int playerEntity;
    int [] changeMapEntities;

    public ChangeMapSystem(){
        super(Aspect.all(Bounds.class).one(Player.class, ChangeMap.class));
    }

    @Override
    protected void processSystem() {
         ImmutableBag<Entity> changeMapBoxes = groupManager.getEntities("gate");
         Entity player = tagManager.getEntity("player");


        for (Entity changeMapBox : changeMapBoxes){
            if (collisionSystem.twoEntityCollision(changeMapBox.getId(), player.getId())) {
                mapSystem.setActiveMap(changeMapCm.get(changeMapBox.getId()).getNextMap());
                System.out.println("WOW");
            }
        }


    }




}

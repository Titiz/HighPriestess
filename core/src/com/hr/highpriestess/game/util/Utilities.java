package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Created by Titas on 2016-08-05.
 */
public class Utilities{

    private static Vector2 tmp = new Vector2();

    public static float distance2(final Entity a, final Entity b)
    {
        final Bounds pa = (Bounds)a.getComponent(Bounds.class);
        final Bounds pb = (Bounds)b.getComponent(Bounds.class);

        return tmp.set(pa.x, pa.y).dst2(pb.x, pb.y);
    }

    public static float angle( final Entity a, final Entity b)
    {
        final Bounds pa = (Bounds)a.getComponent(Bounds.class);
        final Bounds pb = (Bounds)b.getComponent(Bounds.class);

        return tmp.set(pb.x, pb.y).sub(pa.x, pa.y).angle();

    }

    public static float distance(final Entity a, final Entity b)
    {
        final Bounds pa = (Bounds)a.getComponent(Bounds.class);
        final Bounds pb = (Bounds)b.getComponent(Bounds.class);

        return tmp.set(pa.x, pa.y).dst(pb.x, pb.y);
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy*dy);
    }
}
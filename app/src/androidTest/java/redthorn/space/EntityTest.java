package redthorn.space;

import android.graphics.Rect;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jaricthorning on 10/1/18.
 *
 * Tests abstract class Entity
 *
 * Implements Entity class using Spaceship class
 */



public class EntityTest {

    private static final double DELTA = 1e-15;

    @Test
    public void constructorTest(){
        /* Test with implementation as entity is abstract */
        Spaceship spaceship = new Spaceship(0, 0);
        Assert.assertNotNull(spaceship);

        Assert.assertEquals(spaceship.getX(), 0, DELTA);
        Assert.assertEquals(spaceship.getY(), 0, DELTA);

    }

    @Test
    public void layoutRectTest(){
        Spaceship spaceship = new Spaceship(0, 0);
        Assert.assertNotNull(spaceship.getLayoutRects());
        Assert.assertEquals(spaceship.getLayoutRects().size(), 0);
        Rect rect = new Rect(0, 0, 100, 100);
        spaceship.addLayoutRect(rect);
        Assert.assertEquals(spaceship.getLayoutRects().size(), 1);
        Assert.assertEquals(spaceship.getLayoutRects().get(0), rect);
        spaceship.clearLayoutRects();
        Assert.assertEquals(spaceship.getLayoutRects().size(), 0);
        Assert.assertNotNull(spaceship.getLayoutRects());
    }


}

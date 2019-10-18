/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */ 
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Car;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Vehicle;
import org.junit.Test;

/**
 * Testing Car methods.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class CarTest {

    /**
     * Test method for {@link Car#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.STREET);
                
        final Vehicle car = new Car(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.LIGHT) {
                
                    if (currentLightCondition == Light.RED) {
                        assertFalse("Car should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            car.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else {
                        assertTrue("Car should be able to pass Light"
                               + ", with light " + currentLightCondition,
                               car.canPass(destinationTerrain, currentLightCondition));
                    }
                    
                } else if (destinationTerrain == Terrain.STREET) {
                    assertTrue("Car should be able to pass Street"
                                    + ", with light " + currentLightCondition,
                                    car.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                           // cars can pass CROSSWALK
                           

                    if (currentLightCondition == Light.RED 
                                    || currentLightCondition == Light.YELLOW) {
                        assertFalse("Car should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            car.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else { 
                        assertTrue("Car should be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            car.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse("Car should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        car.canPass(destinationTerrain, currentLightCondition));
                } 
            } 
        }
    }

    /**
     * Test method for valid terrain neighbors of {@link Car#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByValidTerrain() {
        for (final Terrain r : Terrain.values()) {
            if (r == Terrain.LIGHT || r == Terrain.STREET || r == Terrain.CROSSWALK) {
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, r);
                neighbors.put(Direction.NORTH, r);
                neighbors.put(Direction.EAST, r);
                neighbors.put(Direction.SOUTH, r);
                
                boolean seenWest = false;
                boolean seenNorth = false;
                boolean seenEast = false;
                boolean seenSouth = false;
                
                final Vehicle v = new Car(0, 0, Direction.NORTH);
                
                for (int count = 0; count < Direction.values().length; count++) {
                    final Direction d = v.chooseDirection(neighbors);
                    
                    if (d == Direction.NORTH) {
                        seenNorth = true;
                        neighbors.put(Direction.NORTH, Terrain.WALL);
                    } else if (d == Direction.WEST) {
                        seenWest = true;
                        neighbors.put(Direction.WEST, Terrain.WALL);
                    } else if (d == Direction.EAST) {
                        seenEast = true;
                        neighbors.put(Direction.EAST, Terrain.WALL);
                    } else if (d == Direction.SOUTH) { // this should NOT be chosen
                        seenSouth = true;
                    }
                }
         
                assertTrue("Car chooseDirection() fails to select in steps "
                           + "among all possible valid choices!",
                           seenWest && seenNorth && seenEast && seenSouth);
            }
        }
    }
    
    
    /**
     * Test method for only back valid terrain of {@link Car#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnValidTerrainMustReverse() {
        for (final Terrain r : Terrain.values()) {
            for (final Terrain t : Terrain.values()) {
                if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT 
                    && (r == Terrain.LIGHT || r == Terrain.STREET || r == Terrain.CROSSWALK)) {
                    
                    final Map<Direction, Terrain> neighbors = 
                                    new HashMap<Direction, Terrain>();
                    neighbors.put(Direction.WEST, t);
                    neighbors.put(Direction.NORTH, t);
                    neighbors.put(Direction.EAST, t);
                    neighbors.put(Direction.SOUTH, r);
                    
                    final Vehicle v = new Car(0, 0, Direction.NORTH);
                    
                    assertEquals("Car chooseDirection() failed "
                                    + "when reverse was the only valid choice!",
                                 Direction.SOUTH, v.chooseDirection(neighbors));
                }
            } 
        }
    }

    /**
     * Test method for {@link model.Car#Car(int, int, model.Direction)}.
     */
    @Test
    public void testCar() {
        final Vehicle v = new Car(10, 11, Direction.NORTH);
        
        assertEquals("Car x coordinate not initialized correctly!", 10, v.getX());
        assertEquals("Car y coordinate not initialized correctly!", 11, v.getY());
        assertEquals("Car direction not initialized correctly!",
                     Direction.NORTH, v.getDirection());
        assertEquals("Car death time not initialized correctly!",
                     15, v.getDeathTime());
        assertTrue("Car isAlive() fails initially!", v.isAlive());
    }
    
    /** Test method for car setters. */
    @Test
    public void testCarSetters() {
        final Vehicle v = new Car(10, 11, Direction.NORTH);
        
        v.setX(12);
        assertEquals("car setX failed!", 12, v.getX());
        v.setY(13);
        assertEquals("car setY failed!", 13, v.getY());
        v.setDirection(Direction.SOUTH);
        assertEquals("car setDirection failed!", Direction.SOUTH, v.getDirection());
    }

}

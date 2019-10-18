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
import model.Bicycle;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Vehicle;
import org.junit.Test;

/**
 * Testing truck methods.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class BicycleTest {

    /**
     * Test method for {@link bike#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnValidTerrainNearTrail() {
        
        final Vehicle bike = new Bicycle(0, 0, Direction.NORTH);
        
        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.STREET 
                            || t == Terrain.CROSSWALK || t == Terrain.LIGHT) {            
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, Terrain.TRAIL);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, t);
                
                for (final Direction d : Direction.values()) {
                    bike.setDirection(d);
                    
                    if (d == Direction.EAST) {
                        assertNotEquals("A bike near a trail and facing " + d
                                     + " should not reverse direction!",
                                     Direction.WEST, bike.chooseDirection(neighbors));
                        
                    } else {
                        assertEquals("A bike near a trail and facing " + d
                                     + " chose a wrong direction!",
                                     Direction.WEST, bike.chooseDirection(neighbors));
                    }
                }
            }
        }
    }
        
    /**
     * Test method for {@link Bicycle#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.TRAIL);
                
        final Vehicle v = new Bicycle(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.LIGHT 
                                || destinationTerrain == Terrain.CROSSWALK) {
                
                    if (currentLightCondition == Light.GREEN) {
                        assertTrue("Bicycle should be able to pass Light"
                                        + ", with light " + currentLightCondition,
                                        v.canPass(destinationTerrain, currentLightCondition));
                    } else {
                        assertFalse("Bicycle should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                                    v.canPass(destinationTerrain,
                                                  currentLightCondition));
                        
                    }
                    
                } else if (destinationTerrain == Terrain.STREET 
                                && destinationTerrain == Terrain.TRAIL) {
                    assertTrue("Bicycle should be able to pass Street and Trail"
                                    + ", with light " + currentLightCondition,
                                    v.canPass(destinationTerrain, currentLightCondition));
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse("Bicycle should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        v.canPass(destinationTerrain, currentLightCondition));
                } 
            } 
        }
    }

    /**
     * Test method for valid terrain neighbors of 
     * {@link Bicycle#chooseDirection(java.util.Map)}.
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
                
                final Vehicle v = new Bicycle(0, 0, Direction.NORTH);
                
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
         
                assertTrue("Truck chooseDirection() fails to select in steps "
                           + "among all possible valid choices!",
                           seenWest && seenNorth && seenEast && seenSouth);
            }
        }
    }
    
    
    /**
     * Test method for only back valid terrain of 
     * {@link Bicycle#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnValidTerrainMustReverse() {
        for (final Terrain r : Terrain.values()) {
            for (final Terrain t : Terrain.values()) {
                if (t != Terrain.STREET && t != Terrain.CROSSWALK 
                                && t != Terrain.LIGHT && t != Terrain.TRAIL
                    && (r == Terrain.LIGHT || r == Terrain.STREET 
                    || r == Terrain.CROSSWALK || r == Terrain.TRAIL)) {
                    
                    final Map<Direction, Terrain> neighbors = 
                                    new HashMap<Direction, Terrain>();
                    neighbors.put(Direction.WEST, t);
                    neighbors.put(Direction.NORTH, t);
                    neighbors.put(Direction.EAST, t);
                    neighbors.put(Direction.SOUTH, r);
                    
                    final Vehicle v = new Bicycle(0, 0, Direction.NORTH);
                    
                    assertEquals("Bike chooseDirection() failed "
                                    + "when reverse was the only valid choice!",
                                 Direction.SOUTH, v.chooseDirection(neighbors));
                }
            } 
        }
    }

    /**
     * Test method for {@link model.Bicycle#Bicycle(int, int, model.Direction)}.
     */
    @Test
    public void testBicyle() {
        final Vehicle v = new Bicycle(10, 11, Direction.NORTH);
        
        assertEquals("bike x coordinate not initialized correctly!", 10, v.getX());
        assertEquals("bike y coordinate not initialized correctly!", 11, v.getY());
        assertEquals("bike direction not initialized correctly!",
                     Direction.NORTH, v.getDirection());
        assertEquals("bike death time not initialized correctly!",
                     35, v.getDeathTime());
        assertTrue("bike isAlive() fails initially!", v.isAlive());
    }
    
    /** Test method for bike setters. */
    @Test
    public void testBicycleSetters() {
        final Vehicle v = new Bicycle(10, 11, Direction.NORTH);
        
        v.setX(12);
        assertEquals("bike setX failed!", 12, v.getX());
        v.setY(13);
        assertEquals("bike setY failed!", 13, v.getY());
        v.setDirection(Direction.SOUTH);
        assertEquals("bike setDirection failed!", Direction.SOUTH, v.getDirection());
    }

}

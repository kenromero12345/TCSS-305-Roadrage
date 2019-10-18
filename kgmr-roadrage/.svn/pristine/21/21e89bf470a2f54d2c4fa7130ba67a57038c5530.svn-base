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
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import model.Vehicle;
import org.junit.Test;

/**
 * Testing truck methods.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class TruckTest {
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.STREET);
                
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.LIGHT) {
                
                    assertTrue("Truck should be able to pass Light"
                               + ", with light " + currentLightCondition,
                               truck.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.STREET) {
                    assertTrue("Truck should be able to pass Street"
                                    + ", with light " + currentLightCondition,
                                    truck.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {

                    if (currentLightCondition == Light.RED) {
                        assertFalse("Truck should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else {
                        assertTrue("Truck should be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse("Truck should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        truck.canPass(destinationTerrain, currentLightCondition));
                } 
               
            } 
        }
    }

    /**
     * Test method for valid terrain neighbors of {@link Truck#chooseDirection(java.util.Map)}.
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
                
                final Vehicle tr = new Truck(0, 0, Direction.NORTH);
                
                for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
                    final Direction d = tr.chooseDirection(neighbors);
                    
                    if (d == Direction.WEST) {
                        seenWest = true;
                    } else if (d == Direction.NORTH) {
                        seenNorth = true;
                    } else if (d == Direction.EAST) {
                        seenEast = true;
                    } else if (d == Direction.SOUTH) { // this should NOT be chosen
                        seenSouth = true;
                    }
                }
         
                assertTrue("Truck chooseDirection() fails to select randomly "
                           + "among all possible valid choices!",
                           seenWest && seenNorth && seenEast);
                    
                assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                            seenSouth);
            }
        }
    }
    
    
    /**
     * Test method for only back valid terrain of {@link Truck#chooseDirection(java.util.Map)}.
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
                    
                    final Vehicle tr = new Truck(0, 0, Direction.NORTH);
                    
                    assertEquals("Truck chooseDirection() failed "
                                    + "when reverse was the only valid choice!",
                                 Direction.SOUTH, tr.chooseDirection(neighbors));
                }
            } 
        }
    }

    /**
     * Test method for {@link model.Truck#Truck(int, int, model.Direction)}.
     */
    @Test
    public void testTruck() {
        final Vehicle t = new Truck(10, 11, Direction.NORTH);
        
        assertEquals("Truck x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.NORTH, t.getDirection());
        assertEquals("Truck death time not initialized correctly!",
                     0, t.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }
    
    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        t.setX(12);
        assertEquals("Truck setX failed!", 12, t.getX());
        t.setY(13);
        assertEquals("Truck setY failed!", 13, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals("Truck setDirection failed!", Direction.SOUTH, t.getDirection());
    }

}

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
import model.Atv;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import model.Vehicle;
import org.junit.Test;

/**
 * Testing atv methods.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class AtvTest {
    
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
        validTerrain.add(Terrain.TRAIL);
        validTerrain.add(Terrain.GRASS);
                
        final Vehicle v = new Atv(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (validTerrain.contains(destinationTerrain)) {
                    assertTrue("Atv should be able to pass Light"
                                    + ", with light " + currentLightCondition,
                                    v.canPass(destinationTerrain, currentLightCondition));
                    
                } else {
                    assertFalse("Atv should NOT be able to pass " + destinationTerrain
                                + ", with light " + currentLightCondition,
                                v.canPass(destinationTerrain, currentLightCondition));
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
            if (r !=  Terrain.WALL) {
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, r);
                neighbors.put(Direction.NORTH, r);
                neighbors.put(Direction.EAST, r);
                neighbors.put(Direction.SOUTH, r);
                
                boolean seenWest = false;
                boolean seenNorth = false;
                boolean seenEast = false;
                boolean seenSouth = false;
                
                final Vehicle v = new Atv(0, 0, Direction.NORTH);
                
                for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
                    final Direction d = v.chooseDirection(neighbors);
                    
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
         
                assertTrue("Atv chooseDirection() fails to select randomly "
                           + "among all possible valid choices!",
                           seenWest && seenNorth && seenEast);
                    
                assertFalse("Atv chooseDirection() reversed direction when not necessary!",
                            seenSouth);
            }
        }
    }
    
    /**
     * Test method for only back valid terrain of {@link Atv#chooseDirection(java.util.Map)}.
     * Doesn't needed because Atv will always have validTerrain but why not.
     */
    @Test
    public void testChooseDirectionOnValidTerrainMustReverse() {
        for (final Terrain r : Terrain.values()) {
            for (final Terrain t : Terrain.values()) {
                if (t == Terrain.WALL && r != Terrain.WALL) {
                    
                    final Map<Direction, Terrain> neighbors = 
                                    new HashMap<Direction, Terrain>();
                    neighbors.put(Direction.WEST, t);
                    neighbors.put(Direction.NORTH, t);
                    neighbors.put(Direction.EAST, t);
                    neighbors.put(Direction.SOUTH, r);
                    
                    final Vehicle tr = new Atv(0, 0, Direction.NORTH);
                    
                    assertEquals("Atv chooseDirection() failed "
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
    public void testAtv() {
        final Vehicle t = new Atv(10, 11, Direction.NORTH);
        
        assertEquals("Atv x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Atv y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Atv direction not initialized correctly!",
                     Direction.NORTH, t.getDirection());
        assertEquals("Atv death time not initialized correctly!",
                     25, t.getDeathTime());
        assertTrue("Atv isAlive() fails initially!", t.isAlive());
    }
    
    /** Test method for Human setters. */
    @Test
    public void testAtvSetters() {
        final Vehicle a = new Atv(10, 11, Direction.NORTH);
        
        a.setX(12);
        assertEquals("Atv setX failed!", 12, a.getX());
        a.setY(13);
        assertEquals("Atv setY failed!", 13, a.getY());
        a.setDirection(Direction.SOUTH);
        assertEquals("Atv setDirection failed!", Direction.SOUTH, a.getDirection());
    }
}

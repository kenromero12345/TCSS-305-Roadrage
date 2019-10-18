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
import model.Taxi;
import model.Terrain;
import model.Vehicle;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing Taxi methods.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class TaxiTest {
    
    /**
    * Vehicle for testing.
    */
    private Vehicle myTestVehicle;
   
   /**
    * Test neighbors for testing chooseDirection method.
    */
    private Map<Direction, Terrain> myTestNeighbors;

    /**
     * 
     */
    @Before
    public void setUp() {
        myTestVehicle = new Taxi(0, 0, Direction.NORTH);
        
        myTestNeighbors = new HashMap<Direction, Terrain>();
    }

    /**
     * Test method for {@link model.Taxi#canPass(model.Terrain, model.Light)}.
     */
    @Test
    public void testCanPassRedCrosswalk() {
        myTestNeighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        
        assertFalse("1st wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("2nd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("3rd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertTrue("Go true", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        
        myTestVehicle.canPass(Terrain.STREET, Light.GREEN); // street
        
        //testing where a problem could occur when the counter should become 0
        
        assertFalse("1st wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("2nd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("3rd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        myTestVehicle.canPass(Terrain.CROSSWALK, Light.GREEN);
        
        myTestVehicle.canPass(Terrain.STREET, Light.GREEN); //street
        
        assertFalse("1st wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("2nd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("3rd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertTrue("Go true", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        
        //testing reset
        
        assertFalse("1st wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("2nd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("3rd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        myTestVehicle.canPass(Terrain.CROSSWALK, Light.GREEN);
        
        myTestVehicle.reset();
        
        assertFalse("1st wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("2nd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse("3rd wait false", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        assertTrue("Go true", myTestVehicle.canPass(Terrain.CROSSWALK, Light.RED));
        
    }
    
    /**
     * Test method for {@link Taxi#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.STREET);
                
        final Vehicle v = new Taxi(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.LIGHT) {
                
                    if (currentLightCondition == Light.RED) {
                        assertFalse("Taxi should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            v.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else {
                        assertTrue("Taxi should be able to pass Light"
                               + ", with light " + currentLightCondition,
                               v.canPass(destinationTerrain, currentLightCondition));
                    }
                    
                } else if (destinationTerrain == Terrain.STREET) {
                    assertTrue("Taxi should be able to pass Street"
                                    + ", with light " + currentLightCondition,
                                    v.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                           // Taxis can pass CROSSWALK
                           

                    if (currentLightCondition == Light.RED) {
                        assertFalse("Taxi should NOT be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            v.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else { 
                        assertTrue("Taxi should be able to pass " + destinationTerrain
                            + ", with light " + currentLightCondition,
                            v.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse("Taxi should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        v.canPass(destinationTerrain, currentLightCondition));
                } 
            } 
        }
    }

    /**
     * Test method for valid terrain neighbors of {@link Taxi#chooseDirection(java.util.Map)}.
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
                
                final Vehicle v = new Taxi(0, 0, Direction.NORTH);
                
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
         
                assertTrue("Taxi chooseDirection() fails to select in steps "
                           + "among all possible valid choices!",
                           seenWest && seenNorth && seenEast && seenSouth);
            }
        }
    }
    
    
    /**
     * Test method for only back valid terrain of {@link Taxi#chooseDirection(java.util.Map)}.
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
                    
                    final Vehicle v = new Taxi(0, 0, Direction.NORTH);
                    
                    assertEquals("Taxi chooseDirection() failed "
                                    + "when reverse was the only valid choice!",
                                 Direction.SOUTH, v.chooseDirection(neighbors));
                }
            } 
        }
    }

    /**
     * Test method for {@link model.Taxi#Taxi(int, int, model.Direction)}.
     */
    @Test
    public void testTaxi() {
        final Vehicle v = new Taxi(10, 11, Direction.NORTH);
        
        assertEquals("Taxi x coordinate not initialized correctly!", 10, v.getX());
        assertEquals("Taxi y coordinate not initialized correctly!", 11, v.getY());
        assertEquals("Taxi direction not initialized correctly!",
                     Direction.NORTH, v.getDirection());
        assertEquals("Taxi death time not initialized correctly!",
                     15, v.getDeathTime());
        assertTrue("Taxi isAlive() fails initially!", v.isAlive());
    }
    
    /** Test method for Taxi setters. */
    @Test
    public void testTaxiSetters() {
        final Vehicle v = new Taxi(10, 11, Direction.NORTH);
        
        v.setX(12);
        assertEquals("Taxi setX failed!", 12, v.getX());
        v.setY(13);
        assertEquals("Taxi setY failed!", 13, v.getY());
        v.setDirection(Direction.SOUTH);
        assertEquals("Taxi setDirection failed!", Direction.SOUTH, v.getDirection());
    }

}

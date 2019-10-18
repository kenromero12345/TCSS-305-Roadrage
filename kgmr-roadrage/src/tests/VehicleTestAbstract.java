/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */ 
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import model.Atv;
import model.Bicycle;
import model.Car;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import model.Vehicle;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing abstractVehicle.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class VehicleTestAbstract {
    
    /**
     * Death time of car.
     */
    private static final int CAR_DEATH_TIME = 15;
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /**
     * vehicle for testing.
     */
    private Vehicle myTestVehicle;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
        myTestVehicle = new Car(0, 0, Direction.NORTH);
    }

    /**
     * Test method for {@link model.Truck#Truck(int, int, model.Direction)}.
     */
    @Test
    public void testCar() {        
        assertEquals("Constructor: Car x coordinate not initialized correctly!"
                     , 0, myTestVehicle.getX());
        assertEquals("Constructor: Car y coordinate not initialized correctly!"
                     , 0, myTestVehicle.getY());
        assertEquals("Constructor: Car direction not initialized correctly!",
                     Direction.NORTH, myTestVehicle.getDirection());
        assertEquals("Constructor: Car death time not initialized correctly!",
                     CAR_DEATH_TIME, myTestVehicle.getDeathTime());
        assertTrue("Constructor: Car isAlive() fails initially!", myTestVehicle.isAlive());
    }

    /**
     * Test method for {@link model.AbstractVehicle#collide(model.Vehicle)}.
     */
    @Test
    public void testCollide() {
        assertTrue("Test alive collide", myTestVehicle.isAlive());
        myTestVehicle.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse("Test death collide", myTestVehicle.isAlive());
    }

    /**
     * Test method for {@link model.AbstractVehicle#getDeathTime()}.
     */
    @Test
    public void testGetDeathTime() {
        assertEquals("Test getDeathTime", CAR_DEATH_TIME, myTestVehicle.getDeathTime());
    }

    /**
     * Test method for {@link model.AbstractVehicle#getImageFileName()}.
     */
    @Test
    public void testGetImageFileName() {
        assertEquals("Test img filename alive", "car.gif", myTestVehicle.getImageFileName());
        myTestVehicle.collide(new Truck(0, 0, Direction.NORTH));
        assertEquals("Test img filename dead", "car_dead.gif"
                     , myTestVehicle.getImageFileName());
    }

    /**
     * Test method for {@link model.AbstractVehicle#getDirection()}.
     */
    @Test
    public void testGetDirection() {
        assertEquals("Test getDirection", Direction.NORTH, myTestVehicle.getDirection());
    }

    /**
     * Test method for {@link model.AbstractVehicle#getX()}.
     */
    @Test
    public void testGetX() {
        assertEquals("test getX", 0, myTestVehicle.getX());
    }

    /**
     * Test method for {@link model.AbstractVehicle#getY()}.
     */
    @Test
    public void testGetY() {
        assertEquals("test getY", 0, myTestVehicle.getY());
    }

    /**
     * Test method for {@link model.AbstractVehicle#isAlive()}.
     */
    @Test
    public void testIsAlive() {
        assertTrue("Test alive", myTestVehicle.isAlive());
        myTestVehicle.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse("Test death", myTestVehicle.isAlive());
    }

    /**
     * Test method for {@link model.AbstractVehicle#poke()}.
     */
    @Test
    public void testPoke() {
        myTestVehicle.poke();
        assertTrue("Test Poke before death False", myTestVehicle.isAlive());
        myTestVehicle.collide(new Truck(0, 0, Direction.NORTH));
        myTestVehicle.poke();
        assertFalse("Test Poke 1 False", myTestVehicle.isAlive());
        for (int i = 0; i < CAR_DEATH_TIME - 2; i++) {
            myTestVehicle.poke();
        }
        assertFalse("Test Poke 15 False", myTestVehicle.isAlive());
        myTestVehicle.poke();
        assertTrue("Test Poke 16 False", myTestVehicle.isAlive());
    }

    /**
     * Test method for {@link model.AbstractVehicle#reset()}.
     */
    @Test
    public void testReset() {
        myTestVehicle.setX(1);
        myTestVehicle.setY(1);
        myTestVehicle.setDirection(Direction.SOUTH);
        myTestVehicle.collide(new Truck(0, 0, Direction.NORTH));
        myTestVehicle.reset();
        assertEquals("Car x coordinate not initialized correctly!", 0, myTestVehicle.getX());
        assertEquals("Car y coordinate not initialized correctly!", 0, myTestVehicle.getY());
        assertEquals("Car direction not initialized correctly!",
                     Direction.NORTH, myTestVehicle.getDirection());
        assertEquals("Car death time not initialized correctly!",
                     CAR_DEATH_TIME, myTestVehicle.getDeathTime());
        assertTrue("Car isAlive() fails initially!", myTestVehicle.isAlive());
    }

    /** Test method for Human setters. */
    @Test
    public void testTruckSetters() {
        myTestVehicle.setX(1);
        assertEquals("Car setX failed!", 1, myTestVehicle.getX());
        myTestVehicle.setY(1);
        assertEquals("Car setY failed!", 1, myTestVehicle.getY());
        myTestVehicle.setDirection(Direction.SOUTH);
        assertEquals("Car setDirection failed!"
                     , Direction.SOUTH, myTestVehicle.getDirection());
    }

    /**
     * Test method for {@link model.AbstractVehicle#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Truck setX failed!"
                     , "Vehicle Type: Car x: 0 y: 0 Direction: NORTH Death Time: 15"
                     , myTestVehicle.toString());
    }

    /**
     * Test method for {@link model.AbstractVehicle#chooseDirectionRandomly(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionRandomly() {
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
         
                assertTrue("Atv chooseDirectionRandomly() fails to "
                                + "select randomly among all possible valid choices!",
                           seenWest && seenNorth && seenEast);
                    
                assertFalse("Atv chooseDirection() reversed direction when not necessary!",
                            seenSouth);
            }
        }
    }
    
    /**
     * Test method for Reverse of 
     * {@link model.AbstractVehicle#chooseDirectionRandomly(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionRandomlyCanReverse() {
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
                    
                    assertEquals("Truck chooseDirectionRandomly() "
                                    + "failed when reverse was the only valid choice!",
                                 Direction.SOUTH, tr.chooseDirection(neighbors));
                }
            } 
        }
    }

    /**
     * Test method for {@link model.AbstractVehicle#chooseDirectionSteps(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSteps() {
        final Vehicle v = new Car(0, 0, Direction.NORTH);
        
        for (final Terrain r : Terrain.values()) {
            if (v.canPass(r, Light.GREEN)) {
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, r);
                neighbors.put(Direction.NORTH, r);
                neighbors.put(Direction.EAST, r);
                neighbors.put(Direction.SOUTH, r);
                 
                assertEquals("Car chooseDirection() fails to select in N"
                                + "orth among all possible valid choices!", Direction.NORTH,
                                v.chooseDirection(neighbors));
                neighbors.put(Direction.NORTH, Terrain.WALL);
                
                assertEquals("Car chooseDirection() fails to select in W"
                                + "est among all possible valid choices!", Direction.WEST,
                                v.chooseDirection(neighbors));
                neighbors.put(Direction.WEST, Terrain.WALL);
                
                assertEquals("Car chooseDirection() fails to select in E"
                                + "ast among all possible valid choices!", Direction.EAST,
                                v.chooseDirection(neighbors));
                neighbors.put(Direction.EAST, Terrain.WALL);
                
                assertEquals("Car chooseDirection() fails to select in S"
                                + "outh among all possible valid choices!", Direction.SOUTH,
                                v.chooseDirection(neighbors));
            }
        }
    }
    
    /**
     * Test method for Reverse of 
     * {@link model.AbstractVehicle#chooseDirectionSteps(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionStepsCanReverse() {
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
     * Test method for 
     * {@link model.AbstractVehicle#chooseDirectionSpecific(java.util.Map, model.Terrain)}.
     */
    @Test
    public void testChooseDirectionSpecific() {
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
                        assertNotEquals("Specific Direction:"
                                        + " A bike near a trail and facing " + d
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

}

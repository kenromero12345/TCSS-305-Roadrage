/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

import java.util.Map;

/**
 * Car class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Car extends AbstractVehicle {
    
    /**
     * Time the Car is not moving.
     */
    private static final int DEATH_TIME = 15;

    /**
     * Car constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the car
     * @param theY y coordinate of the car
     * @param theDir direction the car is looking
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Return a boolean if the car can pass through a specific terrain and light.
     * The car can pass through a specific terrain and light 
     * which are street, green and yellow light, and green crosswalk
     * 
     * @param theTerrain the terrain the car might be passing
     * @param theLight the light the car might be passing
     * @return a boolean if the car can pass through a specific terrain 
     * and light which are street, green and yellow light, and green crosswalk
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET
                        || (theTerrain == (Terrain.LIGHT) 
                            && (theLight == Light.GREEN 
                            || theLight == Light.YELLOW))               
                        || (theTerrain == (Terrain.CROSSWALK) 
                            && (theLight == Light.GREEN));
    }

    /**
     * Chooses the direction the car should go depending on 
     * what it can pass through and its neighbors.
     * The car will go straight, or else left, or else right, or else reverse.
     * 
     * @param theNeighbors the adjacent terrain and/or light 
     * @return the direction straight, or else left, or else right, or else reverse
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return chooseDirectionSteps(theNeighbors);
    }
}

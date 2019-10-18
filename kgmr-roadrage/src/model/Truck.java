/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

import java.util.Map;

/**
 * Truck class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Truck extends AbstractVehicle {
    
    /**
     * Time the Truck is not moving.
     */
    private static final int DEATH_TIME = 0;
    
    /**
     * Truck constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the truck
     * @param theY y coordinate of the truck
     * @param theDir direction the truck is looking
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Return a boolean if the truck can pass through a specific terrain and light.
     * The truck can pass through a specific terrain and light 
     * which are street, light, and green and yellow crosswalk.
     * 
     * @param theTerrain the terrain the truck might be passing
     * @param theLight the light the truck might be passing
     * @return a boolean if the truck can pass through a specific terrain 
     * and light which are street, light, and green and yellow crosswalk
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET 
                        || theTerrain == Terrain.LIGHT                  
                        || (theTerrain == Terrain.CROSSWALK 
                                        && (theLight == Light.YELLOW 
                                        || theLight == Light.GREEN));
    }

    /**
     * Chooses the direction the truck should go depending on 
     * what it can pass through and its neighbors.
     * The truck will go straight, left, or right randomly or else reverse.
     * 
     * @param theNeighbors the adjacent terrain and/or light 
     * @return the direction straight, left, or right randomly or else back.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {         
        return chooseDirectionRandomly(theNeighbors);
    }
}

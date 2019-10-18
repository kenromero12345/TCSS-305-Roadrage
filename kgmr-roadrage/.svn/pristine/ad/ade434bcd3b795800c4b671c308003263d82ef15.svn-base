/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

import java.util.Arrays;
import java.util.Map;

/**
 * Bicycle class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Bicycle extends AbstractVehicle {

    /**
     * Time the Bicycle is not moving.
     */
    private static final int DEATH_TIME = 35;
    
    /**
     * Bicycle constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the bicyle
     * @param theY y coordinate of the bicyle
     * @param theDir direction the bicyle is looking
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Return a boolean if the bike can pass through a specific terrain and light.
     * The bike can pass through a specific terrain and light 
     * which are trail, street, green light, and green crosswalk.
     * 
     * @param theTerrain the terrain the bike might be passing
     * @param theLight the light the bike might be passing
     * @return a boolean if the bike can pass through a specific terrain 
     * and light which are trail, street, green light, and green crosswalk
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.TRAIL 
                        || theTerrain == Terrain.STREET 
                        || (theTerrain == Terrain.LIGHT
                                        && theLight == Light.GREEN)               
                        || (theTerrain == Terrain.CROSSWALK 
                                        && theLight == Light.GREEN);
    }

    /**
     * Chooses the direction the bike should go depending on 
     * what it can pass through and its neighbors.
     * The bike will go to trails except back trail, or else 
     * straight or else left or else right or else reverse.
     * 
     * @param theNeighbors the adjacent terrain and/or light 
     * @return the direction of a trail except back trail, or else 
     * straight, or else left or else right or else reverse.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {        
        return Arrays.stream(new Direction[] 
            {chooseDirectionSpecific(theNeighbors, Terrain.TRAIL)
                            , chooseDirectionSteps(theNeighbors)}).
                        filter(x -> x != null).
                        findFirst().
                        get();
    }
}

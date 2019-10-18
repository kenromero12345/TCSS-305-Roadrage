/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

import java.util.Arrays;
import java.util.Map;

/**
 * Human class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Human extends AbstractVehicle {
    
    /**
     * Time the Human is not moving.
     */
    private static final int DEATH_TIME = 45;

    /**
     * Human constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the human
     * @param theY y coordinate of the human
     * @param theDir direction the human is looking
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Return a boolean if the human can pass through a specific terrain and light.
     * The human can pass through a specific terrain and light 
     * which are grass and yellow and red crosswalk.
     * 
     * @param theTerrain the terrain the human might be passing
     * @param theLight the light the human might be passing
     * @return a boolean if the car can pass through a specific terrain 
     * and light which are grass and yellow and red crosswalk
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.GRASS             
                        || (theTerrain == Terrain.CROSSWALK 
                            && (theLight == Light.YELLOW 
                            || theLight == Light.RED));
    }

    /**
     * Chooses the direction the human should go depending on 
     * what it can pass through and its neighbors.
     * The human will go to crosswalks except back crosswalk, or else 
     * straight, left, or right randomly or else reverse.
     * 
     * @param theNeighbors the adjacent terrain and/or light 
     * @return the direction of a crosswalk except back crosswalk, or else 
     * straight, left, or right randomly or else back.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return Arrays.stream(new Direction[] 
            {chooseDirectionSpecific(theNeighbors, Terrain.CROSSWALK)
                            , chooseDirectionRandomly(theNeighbors)}).
                        filter(x -> x != null).
                        findFirst().
                        get(); 
    }
}

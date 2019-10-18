/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

import java.util.Map;

/**
 * ATV class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Atv extends AbstractVehicle {
    
    /**
     * Time the ATV is not moving.
     */
    private static final int DEATH_TIME = 25;

    /**
     * ATV constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the atv
     * @param theY y coordinate of the atv
     * @param theDir direction the atv is looking
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Return a boolean if the atv can pass through a specific terrain and light.
     * The atv can pass through a specific terrain and light 
     * which are all terrain and light but wall.
     * 
     * 
     * @param theTerrain the terrain the atv might be passing
     * @param theLight the light the atv might be passing
     * @return a boolean if the atv can pass through a specific terrain 
     * and light which are all terrain and light but wall.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain != Terrain.WALL;
    }

    /**
     * Chooses the direction the atv should go depending on 
     * what it can pass through and its neighbors.
     * The atv will go straight, left, or right randomly or else reverse.
     * 
     * @param theNeighbors the adjacent terrain and/or light 
     * @return the direction straight, left, or right randomly or else back.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return chooseDirectionRandomly(theNeighbors);
    }
}
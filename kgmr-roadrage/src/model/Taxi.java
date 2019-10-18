/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */
package model;

/**
 * Taxi class that inherits the parent abstract class.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public class Taxi extends Car {
    
    /**
     * Time for the taxi to move at a red crosswalk.
     */
    private static final int RED_LIGHT_CROSSWALK_CAN_PASS_TIME = 3;
    
    /**
     * Time for the taxi is waiting at a red crosswalk.
     */
    private int myRedLightCrosswalkCounter;
 
    /**
     * Taxi constructor that inherits the parent's constructor.
     * 
     * @param theX x coordinate of the taxi
     * @param theY y coordinate of the taxi
     * @param theDir direction the taxi is looking
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
    }

    /**
     * Return a boolean if the taxi can pass through a specific terrain and light.
     * The taxi can pass through a specific terrain and light 
     * which are street, green and yellow light, and green and yellow crosswalk.
     * At red crosswalk, after 3 turns, can pass.
     * 
     * @param theTerrain the terrain the taxi might be passing
     * @param theLight the light the taxi might be passing
     * @return a boolean if the taxi can pass through a specific terrain 
     * and light which are street, green and yellow light, and green and yellow crosswalk
     * At red crosswalk, after 3 turns, can pass.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        
        if (theTerrain == Terrain.STREET) {
            myRedLightCrosswalkCounter = 0;
        }
        
        if (canPassHelper(theTerrain, theLight)) {
            result = true;
        } else if (myRedLightCrosswalkCounter == RED_LIGHT_CROSSWALK_CAN_PASS_TIME) {
            result = true;
            myRedLightCrosswalkCounter = 0;
        } else if (theLight == Light.RED && theTerrain == Terrain.CROSSWALK) {
            myRedLightCrosswalkCounter++;
        }
        
        return result;
    }
    
    /**
     * Helper method for canPass to avoid complexity warning.
     * 
     * @param theTerrain the terrain the taxi might be passing
     * @param theLight the light the taxi might be passing
     * @return a boolean if the taxi can pass through a specific terrain 
     */
    private boolean canPassHelper(final Terrain theTerrain, final Light theLight) {
        return theTerrain == (Terrain.STREET) 
                        || (theTerrain == (Terrain.LIGHT) 
                        && (theLight == Light.GREEN 
                        || theLight == Light.YELLOW))               
                    || (theTerrain == (Terrain.CROSSWALK) 
                        && (theLight == Light.GREEN 
                        || theLight == Light.YELLOW));
    }
    
    /**
     * Inherits parent's reset plus reseting counter for waiting at red croswalk.
     */
    @Override
    public void reset() {
        super.reset();
        myRedLightCrosswalkCounter = 0;
    }
}

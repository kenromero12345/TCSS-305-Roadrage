/*
 * TCSS 305 - Autumn 2018
 * Assignment 3 - Road Rage
 */

package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AbstractClass that the child classes inherits.
 * 
 * @author Ken Gil Romero kgmr@uw.edu
 * @version 10/26/18
 */
public abstract class AbstractVehicle implements Vehicle {
       
    /**
     * X coordinate of the vehicle.
     */
    private int myX;
    
    /**
     * Y coordinate of the vehicle.
     */
    private int myY;
    
    /**
     * Direction the vehicle is looking.
     */
    private Direction myDir;
    
    /**
     * Starting x coordinate of the vehicle.
     */
    private final int myStartX;
    
    /**
     * Starting y coordinate of the vehicle.
     */
    private final int myStartY;
    
    /**
     * Starting direction the vehicle is looking.
     */
    private final Direction myStartDir;
    
    /**
     * Countdown time the vehicle would be move again(death time - revive time).
     */
    private int myReviveTime;
    
    /**
     * Time the vehicle would be dead.
     */
    private final int myDeathTime;
    
    /**
     * The image filename of the vehicle when alive.
     */
    private String myImageFileName;
    
    /**
     * The image filename of the vehicle when dead.
     */
    private String myDeadImageFileName;
    
    /**
     * Abstract constructor that a vehicle child class inherits.
     * 
     * @param theX x coordinate of the vehicle
     * @param theY y coordinate of the vehicle
     * @param theDir direction the vehicle is looking
     * @param theDeathTime time the vehicle is dead
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir
                           , final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDir =  theDir;
        
        myStartX = theX;
        myStartY = theY;
        myStartDir =  theDir;
        
        myDeathTime = theDeathTime;
        myReviveTime = 0;
        
        imageFileNameMutator();
    }
    
    /**
     * Helper method for the abstract vehicle in setting the image filenames.
     */
    private void imageFileNameMutator() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName().toLowerCase(Locale.ENGLISH));
        
        sb.append(".gif");
        
        myImageFileName = sb.toString();
        
        final int imageFileTypeLength = 4;
        
        sb.insert(sb.length() - imageFileTypeLength , "_dead");
        
        myDeadImageFileName = sb.toString(); 
    }

    /* (non-Javadoc)
     * @see model.Vehicle#canPass(model.Terrain, model.Light)
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /* (non-Javadoc)
     * @see model.Vehicle#chooseDirection(java.util.Map)
     */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /* (non-Javadoc)
     * @see model.Vehicle#collide(model.Vehicle)
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && getDeathTime() > theOther.getDeathTime()) {
            myReviveTime++;
        }
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getDeathTime()
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getImageFileName()
     */
    @Override
    public String getImageFileName() {
        final String imageFileName;
        if (isAlive()) {
            imageFileName = myImageFileName;
        } else {
            imageFileName = myDeadImageFileName;
        }
        return imageFileName;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getDirection()
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getX()
     */
    @Override
    public int getX() {
        return myX;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getY()
     */
    @Override
    public int getY() {
        return myY;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#isAlive()
     */
    @Override
    public boolean isAlive() {
        return myReviveTime == 0;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#poke()
     */
    @Override
    public void poke() {
        if (isAlive()) {
            setDirection(Direction.random());
        } else {
            myReviveTime++;
            if (myReviveTime == myDeathTime + 1) {
                myReviveTime = 0;
            }
        }
    }

    /* (non-Javadoc)
     * @see model.Vehicle#reset()
     */
    @Override
    public void reset() {
        myX = myStartX;
        myY = myStartY;
        myDir = myStartDir;
        
        myReviveTime = 0;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setDirection(model.Direction)
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setX(int)
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setY(int)
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    /**
     * Returns the vehicle type, it's x, y, direction, and death time in string.
     * 
     * @return the vehicle type, it's x, y, direction, and death time.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("Vehicle Type: ");
        sb.append(getClass().getSimpleName());
        sb.append(" x: ");
        sb.append(getX());
        sb.append(" y: ");
        sb.append(getY());
        sb.append(" Direction: ");
        sb.append(getDirection());
        sb.append(" Death Time: ");
        sb.append(getDeathTime());
        return sb.toString();
    }
    
    /**
     * Chooses the vehicles direction by random if it is a valid terrain.
     * 
     * @param theNeighbors the neighbors of our terrain
     * @return the chosen direction where the vehicle will go randomly
     */
    protected Direction chooseDirectionRandomly(
            final Map<Direction, Terrain> theNeighbors) {
        return Arrays.stream(Direction.values()).
                        //can use Direction.values() because randomize
                        filter(x -> getDirection().reverse() != x).
                        filter(x -> canPass(theNeighbors.get(x), Light.GREEN)).
                        collect(Collectors.collectingAndThen(Collectors.toList()
                                                             , collected -> {
                                Collections.shuffle(collected);
                                return collected.stream();
                            })).
                        findAny().
                        orElse(getDirection().reverse()); 
    }
    
    /**
     * Chooses the vehicles direction in order of straight, left, right, back 
     * if it is a valid terrain.
     * 
     * @param theNeighbors the neighbors of our terrain
     * @return the chosen direction where the vehicle will go in steps
     */
    protected Direction chooseDirectionSteps(final Map<Direction, Terrain> theNeighbors) {
        return Arrays.stream(new Direction[] 
            {getDirection(), getDirection().left(), getDirection().right()}).
                        //can't use Direction.values() because start of array matters
                        //don't need filter for back direction 
                        //  because did not use Direction.values()
                        filter(x -> canPass(theNeighbors.get(x), Light.GREEN)).
                        //filter(x -> getDirection() == x).
                        findFirst().
                        orElse(getDirection().reverse());
    }
    
    /**
     * Chooses the vehicles direction by a specific terrain in order of 
     * straight, left, right. If the 3's terrain is not the same with 
     * our specific terrain, return null.
     * 
     * @param theNeighbors the neighbors of our terrain
     * @param theTerrain the specific terrain we are comparing our neighbors
     * @return the chosen direction where the vehicle will go in steps
     */
    protected Direction chooseDirectionSpecific(final Map<Direction, Terrain> theNeighbors
                                                , final Terrain theTerrain) {
        return Arrays.stream(Direction.values()).
                      //can use Direction.values() because all but 1 will be filtered
                        filter(x -> getDirection().reverse() != x).
                        filter(x -> theNeighbors.get(x) == theTerrain).
                        findFirst().
                        orElse(null);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private int GROUPSIZE = 13;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        getBackground().setColor(new Color(145,227,133));
        getBackground().fill();
        addTrees(200);
        addBirds(200);
    }

    public void addBirds(int birdTotal)
    {
        int x, y;
        int count = 0;
        Bird bird;

        while (count < birdTotal)
        {
            x = Greenfoot.getRandomNumber(1000)+100;
            y = Greenfoot.getRandomNumber(600)+100;
            bird = new Bird();
            if (getObjectsAt(x, y, Tree.class).size() == 0)
            {
                addObject(bird, x, y);
                count++;
            }
        }
    }

    public void addTrees (int treeTotal)
    {
        for (int i=0; i<treeTotal/GROUPSIZE; i++)
            addTreeClump(Greenfoot.getRandomNumber(1000)+100,Greenfoot.getRandomNumber(600)+100);
    }

    public void addTreeClump(int x, int y)
    {
        int rot, distance, cx, cy;
        for (int i=0; i<=GROUPSIZE; i++)
        {
            rot = Greenfoot.getRandomNumber(360);
            distance = Greenfoot.getRandomNumber(140)-70;
            cx = (int)(distance * Math.cos(Math.toRadians(rot)));
            cy = (int)(distance * Math.sin(Math.toRadians(rot)));
            addObject(new Tree(), x+cx, y+cy);
        }
    }
}

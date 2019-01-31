import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends SmoothMover
{
    /**
     * Act - do whatever the Bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int edgeDist = 75;
    public Bird()
    {
        int dir = Greenfoot.getRandomNumber(360);
        int speed = Greenfoot.getRandomNumber(9)+2;
        addToVelocity(new Vector(dir, speed));
    }

    public void act() 
    {
        edgeDetection();
        treeDetection();
        birdAvg();
        move();
    }

    public void edgeDetection()
    {
        double factor = 50.0;
        double speed;
        if (getX() < edgeDist) // left wall
        {
            speed =  (1.0 /getX()) * factor;
            addToVelocity(new Vector(0, speed));
        }
        if (getX() > getWorld().getWidth() - edgeDist) //right wall
        {
            speed = 1.0 / (getWorld().getWidth() - getX()) * factor;
            addToVelocity(new Vector(180, speed));
        }
        if (getY() < edgeDist) // top wall
        {
            speed = 1.0/ getY() * factor;
            addToVelocity(new Vector(90, speed));
        }
        if (getY() > getWorld().getHeight() - edgeDist) // bottom wall
        {
            speed = 1.0/ (getWorld().getHeight() - getY()) * factor;
            addToVelocity(new Vector(270, speed));
        }
    }

    private void treeDetection()
    {
        int dx, dy, treeDist = 50;
        List <Tree> trees = getObjectsInRange(treeDist, Tree.class);
        double tStrength = Math.PI;
        for (Tree tr : trees)
        {
            dx = tr.getX() - getX();
            dy = tr.getY() - getY();
            if (Math.abs(dx)> Math.abs(dy) && dx> 0)
            addToVelocity(new Vector(0, tStrength));
            else if (Math.abs(dx)> Math.abs(dy))
            addToVelocity(new Vector(180, tStrength));
            else if (dy > 0)
            addToVelocity(new Vector(90, tStrength));
            else
            addToVelocity(new Vector(270, tStrength));
        }

    }
    void birdAvg()
    {
        int birdDist = 200;
        int rotTotal = getRotation();
        double speedTotal = getSpeed();
        List <Bird> birds = getObjectsInRange(birdDist, Bird.class);
        int total = birds.size() + 1;
        for (Bird b : birds)
        {
            rotTotal +=b.getRotation();
            speedTotal += b.getSpeed();
        }
        setVelocity( new Vector(rotTotal/total, speedTotal/total));
    }

}

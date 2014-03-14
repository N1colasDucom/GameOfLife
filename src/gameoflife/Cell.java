/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

import static gameoflife.GameOfLife.world;

/**
 *
 * @author Nicolas
 */
public class Cell {
    
    private int x, y;
    private boolean alive = false;
    private boolean limbo = false;
    private int neighbours = 0;

    
    public Cell(int X, int Y)
    {
        x = X;
        y = Y;
    }
    
    public void die()
    {
        limbo = false;
    }
    
    public void live()
    {
        limbo = true;
    }
    
    public void commit()
    {
        alive = limbo;
    }
    
    public int countNeighbours(){
        
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                if(((x+i>0)&&(x+i<world.getSizeX()))&&((y+j>0)&&(y+j<world.getSizeY()))&&!(i==0&&j==0)){
                    if(world.cells.get(x+i).get(y+j).isAlive()==true)
                    {
                       //System.out.println(world.cells.get(i).get(j).isAlive());
                       //System.out.println(world.cells.get(i).get(j).getX()+":"+world.cells.get(i).get(j).getY());
                      neighbours++;
                    }          
                }
            }
        }
        return neighbours;
    }
    
    public void natality(){
        if(Math.random() < 0.85f){
            alive = false;
        } else {
            alive = true;
        }
    }
    public void liveOrDie(){
        neighbours = 0;
        countNeighbours();
        if(this.isAlive()==true){
            //System.out.println(this);
            //System.out.println(neighbours);
            if (neighbours<=1) {
                //loneliness             
                //System.out.println("loneliness");
                this.die();
            } else if(neighbours<=3){
                //happy
                //System.out.println("happy");
                this.live();
            } else if(neighbours>=4){
                //System.out.println("overpop");
                //overpopulation
                this.die();
            }
        } else {
            if(neighbours==3){
                //birth
                this.live();
            }
        }
        //System.out.println(this);
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void invert()
    {
        if(alive == true){
            alive = false;
        } else {
            alive = true;
        }
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    @Override
    public String toString(){
        return this.x+":"+this.y+":"+this.isAlive();
    }
}

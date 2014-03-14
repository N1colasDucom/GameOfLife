/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class Grid {
    
    protected static ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
    private int sizeX,sizeY,cellSizeX,cellSizeY;
    
    public Grid(int x, int y) 
    {
        sizeX = x;
        sizeY = y;
        createCells(x, y);
    }
    
    private void createCells(int x, int y)
    {
        for (int i = 0; i < x; i++) {
            cells.add(new ArrayList<Cell>());
            for (int j = 0; j < y; j++) {
               Cell c = new Cell(i, j);
               cells.get(i).add(c);
            }
        }
    }
    
    public void rnd()
    {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                this.cells.get(i).get(j).natality();
            }
        }
    }
    
    public void breath()
    {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                this.cells.get(i).get(j).liveOrDie();
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                this.cells.get(i).get(j).commit();
            }
        }
    }
    
    public int getCellSizeX()
    {
        return cellSizeX;
    }
    
    public void setCellSizeX(int size)
    {
        cellSizeX = size;
    }
    
    public int getCellSizeY()
    {
        return cellSizeY;
    }
    
    public void setCellSizeY(int size)
    {
        cellSizeY = size;
    }
    
    public int getSizeX()
    {
        return sizeX;
    }
    
    public int getSizeY()
    {
        return sizeY;
    }
    
}

       
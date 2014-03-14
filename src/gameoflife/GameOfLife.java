package gameoflife;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameOfLife extends BasicGame
{
    static Grid world = null;
    static int windowSizeX,windowSizeY,sizeX,sizeY;
    boolean bigBang = false;
    private int it =0;
    
    public GameOfLife(String gamename)
    {
            super(gamename);
    }
    
    public void drawGrid(Graphics g)
    {
        g.setColor(Color.white);
        for(int i=0;i<=world.getSizeX();i++){
           g.drawLine(world.getCellSizeX()*i, 0, world.getCellSizeX()*i, windowSizeY);      
        }
        
        for(int j=0;j<=world.getSizeY();j++){
           g.drawLine(0, world.getCellSizeY()*j, windowSizeX, world.getCellSizeY()*j);
        }    
    }
    
    public void drawCells(Graphics g)
    {
        g.setColor(Color.white);
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(world.cells.get(i).get(j).isAlive()==true)
                {
                    //System.out.println(world.cells.get(i).get(j).isAlive());
                    //System.out.println(world.cells.get(i).get(j).getX()+":"+world.cells.get(i).get(j).getY());
                    g.fillRect(world.cells.get(i).get(j).getX()*world.getCellSizeX(), world.cells.get(i).get(j).getY()*world.getCellSizeY(), world.getCellSizeX(), world.getCellSizeY());
                }
            }
        }
    }
    

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {      
       if (gc.getInput().isKeyDown(Input.KEY_ENTER)) {
           bigBang = true;
        }  else if (gc.getInput().isKeyDown(Input.KEY_BACK)) {
           bigBang = false;
        }
        if (gc.getInput().isMousePressed(0)) {
            world.cells.get(gc.getInput().getMouseX()/world.getCellSizeX()).get(gc.getInput().getMouseY()/world.getCellSizeY()).invert();
        }
       if(bigBang == true){
           world.breath();
           it++;
       } 
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString(Integer.toString(it) , 250*world.getCellSizeX(), 10);
        drawGrid(g);
        drawCells(g);
    }

    public static void main(String[] args)
    {
        sizeX = 250;
        sizeY = 250;
        windowSizeX = 1900;
        windowSizeY = 1000;
        world = new Grid(sizeX, sizeY);
        world.rnd();
        world.setCellSizeX(windowSizeX/sizeX);
        world.setCellSizeY(windowSizeY/sizeY);
        try
        {
                AppGameContainer appgc;
                appgc = new AppGameContainer(new GameOfLife("Game of Life"));
                appgc.setDisplayMode(windowSizeX, windowSizeY, false);
                appgc.setTargetFrameRate(1000);
                appgc.start();
        }
        catch (SlickException ex)
        {
                Logger.getLogger(GameOfLife.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
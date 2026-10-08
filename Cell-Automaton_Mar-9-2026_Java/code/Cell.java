package code;
import java.util.ArrayList;

public class Cell 
{
    //************VARIABLES************
    //Cell Variables
    public String name;
    public int cellColumn; //the column the cell is in
    public int cellRow; //the row the cell is in
    
    //Cell State Variables
    public static ArrayList<Cell> cells = new ArrayList<Cell>();
    public boolean state = false; //false for off/dead and true for on/alive
    public int radius = 1; //the radius of neighboring cells that is checked for
    public boolean checkDiagonals = true; //does the check account for diagonal neighbors?
    
    //Iteration Variables
    public ArrayList<Cell> neighbors = new ArrayList<Cell>();
    public int numAliveNeighbors = 0;

    
    
    
    //************METHODS************
    //Constructor
    public Cell(String theName, int theCellColumn, int theCellRow)
    {
        name = theName;
        cellColumn = theCellColumn;
        cellRow = theCellRow;
        
        cells.add(this);
    }
    
    
    
    //Iteration Methods
    public void findNeighbors()
    {
        neighbors.clear();
        
        for(int i = 0; i < cells.size(); i++)
        {
            boolean columnWithinRadius = Math.abs(cells.get(i).cellColumn - cellColumn) <= radius;
            boolean rowWithinRadius = Math.abs(cells.get(i).cellRow - cellRow) <= radius;
            boolean isCurrentCell = cells.get(i) == this;
            
            if(checkDiagonals && (columnWithinRadius && rowWithinRadius) && !isCurrentCell)
            {
                neighbors.add(cells.get(i));
            }
            else if(!checkDiagonals && (Math.abs(cells.get(i).cellRow - cellRow) + Math.abs(cells.get(i).cellColumn - cellColumn) <= radius) && !isCurrentCell)
            {
                neighbors.add(cells.get(i));
            }
        }
    }
    
    public int findNumAliveNeighbors()
    {
        numAliveNeighbors = 0;
        
        for(int i = 0; i < neighbors.size(); i++)
        {
            if(neighbors.get(i).state)
            {
                numAliveNeighbors++;
            }
        }
        
        return numAliveNeighbors;
    }
    
    public void iterate()
    {
        if(numAliveNeighbors == 3)
        {
            state = true;
        }
        else if(numAliveNeighbors != 2)
        {
            state = false;
        }
    }
    
    public void runOnce()
    {
        this.findNeighbors();
        this.findNumAliveNeighbors();
        this.iterate();
    }
    
    public static void runAllOnce()
    {
        for(int i = 0; i < cells.size(); i++)
        {
            cells.get(i).findNeighbors();
            cells.get(i).findNumAliveNeighbors();
        }
        for(int i = 0; i < cells.size(); i++)
        {
            cells.get(i).iterate();
        }
    }
    
    
    //Getter and Setter Methods
    public boolean getState()
    {
        return state;
    }
    public void setState(boolean theState)
    {
        state = theState;
    }
    
    public int getRadius()
    {
        return radius;
    }
    public void setRadius(int theRadius)
    {
        radius = theRadius;
    }
    
    public boolean getIfDiagonals()
    {
        return checkDiagonals;
    }
    public void setIfDiagonals(boolean ifDiagonals)
    {
        checkDiagonals = ifDiagonals;
    }
    
    
    
    //Set-ALL Methods
    public static void setAllStates(boolean theState)
    {
        for(int i = 0; i < cells.size(); i++)
        {
            cells.get(i).state = theState;
        }
    }
    public static void setAllRadii(int theRadius)
    {
        for(int i = 0; i < cells.size(); i++)
        {
            cells.get(i).radius = theRadius;
        }
    }
    public static void setAllIfDiagonals(boolean ifDiagonals)
    {
        for(int i = 0; i < cells.size(); i++)
        {
            cells.get(i).checkDiagonals = ifDiagonals;
        }
    }
    
    
    
    //To String
    public String toString()
    {
        if(state)
        {
            return name + " is a cell at (" + cellColumn + ", " + cellRow + ") and is alive. ";
        }
        else
        {
            return name + " is a cell at (" + cellColumn + ", " + cellRow + ") and is dead. ";
        }
    }
}
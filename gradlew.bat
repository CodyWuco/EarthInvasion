package com.gamecodeschool.logicsimulator;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// AbstractGridCell represents a single grid cell in the Logic Simulator. It is the bass class for
// each of the different stat classes that each grid cell can represent.
//

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import static android.content.Context.MODE_PRIVATE;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Grid implements Serializable {
    int gridWidth, gridHeight, blockSize;
    int xOffset, yOffset;
    Random rand;
    int gridSize;
    AbstractGridCell selected, previousSelection, wireSource;
    Context context;
    AbstractGridCellSaves saves;

    private class GridPosition{
        int x,y;
        public GridPosition(int x, int y)               {this.x=x; this.y=y;}
    }


    public Vector<AbstractGridCell> gridCells;
    public Vector<AbstractGridCell> hudCells;

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Grid(int x, int y, Context context){
        // Forcing context to be set, so there aren't any errors in context related functions, like
        // saving and loading
        setContext(context);

        saves = new AbstractGridCellSaves();

        if (x > y){ gridSize = 6; }
        else { gridSize = 10; }

        blockSize = y / gridSize;
        gridWidth =  10;
        gridHeight = 10;
        xOffset = 0;
        yOffset = 0;
        rand = new Random();
        selected = null;
        previousSelection = null;
        wireSource = null;
        reset();
        setUpHud();
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public void reset(){
        hudCells = new Vector<>(1
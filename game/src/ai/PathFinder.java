package ai;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    
    // 2D array holding the nodes on the game map
    Node[][] node;
    
    ArrayList<Node> openList = new ArrayList<>(); // this list stores nodes that have not been explored yet  
    public ArrayList<Node> pathList = new ArrayList<>(); // this list stores the nodes of the found path
    
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    
    // Step counter (max 500)
    int step = 0;

    public PathFinder(GamePanel gp)
    {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes()
    {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) //initializing all nodes on the map
        {
            node[col][row] = new Node(col,row);

            col++;
            if(col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
    }

    
    public void resetNodes() //to reset previous pathfinding results
    {
        int col = 0;
        int row = 0;
        // Reset all nodes' states
        while(col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
        //reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

 
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity)
    {
        resetNodes();
        
        // setting the start and the goal nodes
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;    
        
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) //iterate over the entire map to check each node
        {
            //mark solid nodes (obstacles)
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision == true)
            {
                node[col][row].solid = true;
            }

            //mark interactive tiles (destructible)
            for(int i = 0; i < gp.iTile[1].length; i++)
            {
                if(gp.iTile[gp.currentMap][i] != null &&
                        gp.iTile[gp.currentMap][i].destructible == true)
                {
                    int itCol = gp.iTile[gp.currentMap][i].worldX / gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY / gp.tileSize;
                    node[itCol][itRow].solid = true;
                }
            }

            //calculate the cost for each node
            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node)
    {
        //g cost (distance from the start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //h cost (heuristic distance to the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //f cost (total cost)
        node.fCost = node.gCost + node.hCost;
    }

    
    public boolean search() //this function searches for the path to the goal
    {
        //continue searching until the goal is reached or steps exceed 500
        while(goalReached == false && step < 500)
        {
            int col = currentNode.col;
            int row = currentNode.row;

            //checking the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open neighboring nodes (up, left, down, right)
            if(row-1 >= 0) openNode(node[col][row-1]);
            if(col - 1 >= 0) openNode(node[col-1][row]);
            if(row + 1 <= gp.maxWorldRow) openNode(node[col][row+1]);
            if(col + 1 <= gp.maxWorldCol) openNode(node[col+1][row]);

            //find the best node to proceed to
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++)
            {
                if(openList.get(i).fCost < bestNodefCost)
                {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if f costs are equal, check the g cost
                else if(openList.get(i).fCost == bestNodefCost)
                {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost)
                    {
                        bestNodeIndex = i;
                    }
                }
            }

            // if there are no nodes in the open list, break the loop
            if(openList.size() == 0) break;

            // the next step is the best node from the open list
            currentNode = openList.get(bestNodeIndex);

            // if the goal node is reached, track the path
            if(currentNode == goalNode)
            {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) //this function opens a node (add it to the open list)
    {
        if(node.open == false && node.checked == false && node.solid == false)
        {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    //function to track the path after reaching the goal
    public void trackThePath()
    {
        Node current = goalNode;

        //track back to the start node and add all nodes to the path
        while(current != startNode)
        {
            pathList.add(0, current); //add the last node to the front of the list
            current = current.parent;
        }
    }
}
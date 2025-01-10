/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package ai;

public class Node {

    Node parent;
    
    public int col;
    public int row;
    
    int gCost; // gCost: Distance from the start node
    int hCost; // hCost: Heuristic distance to the goal node
    int fCost; // fCost: fCost = gCost + hCost
    
    boolean solid; //solid state indicating whether the node is passable or not
    
    boolean open;//state indicating if the node is open for exploration
    
    boolean checked; //state indicating if the node has been checked

    public Node(int col, int row)
    {
        this.col = col;
        this.row = row;
    }

}
import java.util.List;
import java.util.Objects;

public class State {
    
    private int row; 
    private int col;
    private List<State> cami;
    private float heuristic;
    private float costAcc;

    public State (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public State (int row, int col, List<State> cami, float heuristic, float costAcc){
        this.row = row;
        this.col = col;
        this.cami = cami;
        this.heuristic = heuristic;
        this.costAcc = costAcc;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<State> getCami(){
        return cami;
    }

    public void setCami(List<State> cami){
        this.cami = cami;
    }

    public float getCostAcc() {
        return costAcc;
    }

    public void setCostAcc (float costAcc){
        this.costAcc = costAcc;
    }

    public float getHeuristic(){
        return heuristic;
    }

    public void setHeuristic(float heuristic){
        this.heuristic = heuristic;
    }

    public float getTotalCost() {
        return costAcc + heuristic;
    }


    @Override
    public boolean equals(Object other) {
       if (this == other) return true;
       if(!(other instanceof State)) return false;
       State state = (State) other;
       return row == state.row && col == state.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

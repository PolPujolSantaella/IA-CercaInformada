import java.util.ArrayList;
import java.util.List;

public abstract class Search {
    private float[][] costMap;
    private Heuristic heuristic; 

    public Search(float[][] costMap, Heuristic heuristic){
        this.costMap = costMap;
        this.heuristic = heuristic;
    }

    public abstract List<State> DoSearch(State initialState, State targetState);  

    protected List<State> EvaluateOperators(State currentState, State targetState){
        List<State> successors = new ArrayList<>();

        int currentRow = currentState.getRow();
        int currentCol = currentState.getCol();

        int [][] moves = {{-1,0}, {1,0}, {0, -1}, {0, 1}};

        for (int[] move : moves){
            int newRow = currentRow + move[0];
            int newCol = currentCol + move[1];

            if(isValidPosition(newRow, newCol)){
                float newCostAcc = currentState.getCostAcc() + costMap[newRow][newCol];

                float newHeuristic = heuristic.Evaluate(new State(newRow, newCol) ,targetState, costMap);

                State newState = new State(newRow, newCol, currentState.getCami(), newHeuristic, newCostAcc);

                successors.add(newState);
            }
        }

        return successors;
    }

    private boolean isValidPosition(int row, int col){
        return row >= 0 && row < costMap.length && col >= 0 && col < costMap[0].length && Float.isFinite(costMap[row][col]);
    }
}

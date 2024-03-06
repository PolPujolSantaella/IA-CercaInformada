import java.util.*;

public class BestFirstSearch extends Search {

    private float [][] costMap;

    public BestFirstSearch(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
        this.costMap = costMap;
    }

    @Override
    public List<State> DoSearch(State initialState, State targetState) {
        initialState.setCostAcc(costMap[initialState.getRow()][initialState.getCol()]);
        
        List<State> solució = new ArrayList<>();
        PriorityQueue<State> pends = new PriorityQueue<>((a,b) -> Float.compare(a.getHeuristic(), b.getHeuristic()));
        List<State> visitats = new ArrayList<>();

        pends.add(initialState);

        while(!pends.isEmpty()){

            incrementNodesVisited();

            State currentState = pends.poll();
            if (currentState.equals(targetState)){
                solució = currentState.getCami();
                solució.add(currentState);
                break;
            }

            if(!visitats.contains(currentState)){
                visitats.add(currentState);
                List<State> successors = EvaluateOperators(currentState, targetState);
                for (State successor : successors){
                    if (!visitats.contains(successor) && !pends.contains(successor)){
                        
                        List<State> newCami = currentState.getCami() != null ? new ArrayList<>(currentState.getCami()) : new ArrayList<>(); 
                        newCami.add(currentState);
                        successor.setCami(newCami);

                        pends.add(successor);
                    }
                }
            }
        }

        return solució.isEmpty() ? null : solució;
    }
}

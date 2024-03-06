import java.util.*;

public class AStarSearch extends Search {

    private float[][] costMap;
    private Heuristic heuristic;


    public AStarSearch(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
        this.costMap = costMap;
        this.heuristic = heuristic;
    }

    @Override
    public List<State> DoSearch(State initialState, State targetState) {

        List<State> solution = new ArrayList<>();
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingDouble(State::getTotalCost));
        Set<State> closedSet = new HashSet<>();

        initialState.setCostAcc(costMap[initialState.getRow()][initialState.getCol()]);
        openSet.add(initialState);

        while (!openSet.isEmpty()) {

            incrementNodesVisited();

            State currentState = openSet.poll();

            if (currentState.equals(targetState)) {
                solution = currentState.getCami();
                solution.add(currentState);
                break;
            }

            closedSet.add(currentState);

            List<State> successors = EvaluateOperators(currentState, targetState);
            for (State successor : successors) {
                if (closedSet.contains(successor)) {
                    continue;
                }

                float newCostAcc = currentState.getCostAcc() + costMap[successor.getRow()][successor.getCol()];
                if (!openSet.contains(successor) || newCostAcc < successor.getCostAcc()) {
                    successor.setCostAcc(newCostAcc);
                    successor.setHeuristic(heuristic.Evaluate(successor, targetState, costMap));
                    List<State> newCami = currentState.getCami() != null ? new ArrayList<>(currentState.getCami()) : new ArrayList<>();
                    successor.setCami(newCami);
                    successor.getCami().add(currentState);

                    if (!openSet.contains(successor)) {
                        openSet.add(successor);
                    }
                }
            }
        }


        return solution.isEmpty() ? null : solution;
    }
}

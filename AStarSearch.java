import java.util.*;

public class AStarSearch extends Search {

    private float[][] costMap;


    public AStarSearch(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
        this.costMap = costMap;
    }

    @Override
    public List<State> DoSearch(State initialState, State targetState) {
        //1. Inicialitzacions
        initialState.setCostAcc(costMap[initialState.getRow()][initialState.getCol()]);
        PriorityQueue<State> pends = new PriorityQueue<>(Comparator.comparingDouble(State::getTotalCost));
        HashSet<State> visitats = new HashSet<>();
        List<State> path = new ArrayList<>();

        //2. Afegim l'estat inicial a la cua de pendents
        pends.add(initialState);

        //3. Mentres la cua de pendents no estigui buida
        while (!pends.isEmpty()) {

            //3.1 Agafem el primer de la cua
            State currentState = pends.poll();

            //3.2 Si és igual a l'estat final retornem el camí trobat
            if (currentState.equals(targetState)) {
                path = currentState.getCami();
                path.add(currentState);
                break;
            }

            //3.3 Sino Iterem cada successor
            List<State> successors = EvaluateOperators(currentState, targetState);
            for (State successor : successors) {
                //3.4 Calculem el nou cost acumulat per arribar al successor
                float newCostAcc = currentState.getCostAcc() + costMap[successor.getRow()][successor.getCol()];
                //3.5 Si no està visitat
                if (!visitats.contains(successor)) {
                    //3.6 Si no està en pendents
                    if (!pends.contains(successor)) {
                        //3.7 Actualitzem el cost acumulat i el camí
                        successor.setCostAcc(newCostAcc);
                        List<State> newCami = new ArrayList<>(currentState.getCami()); 
                        newCami.add(currentState);
                        successor.setCami(newCami);
                        pends.add(successor);
                    } else if (newCostAcc < successor.getCostAcc()){
                        //3.8 Si el successor està a pendents i el nou cost es menor
                        //Sobreescribim (Borrem) el successor i agreguem el nou
                        pends.remove(successor);
                        successor.setCostAcc(newCostAcc);
                        List<State> newCami = new ArrayList<>(currentState.getCami()); 
                        newCami.add(currentState);
                        successor.setCami(newCami);
                        pends.add(successor);
                    }
                }
            }
            //3.9 Agreguem el estat actual als visitats i augmentem nodesVisitats
            visitats.add(currentState);
            incrementNodesVisitats();
        }

        //4. Retornem camí trobat
        return path.isEmpty() ? null : path;
    }
}

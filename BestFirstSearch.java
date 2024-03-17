import java.util.*;

public class BestFirstSearch extends Search {

    private float [][] costMap;

    public BestFirstSearch(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
        this.costMap = costMap;
    }

    @Override
    public List<State> DoSearch(State initialState, State targetState) {
        //1. Inicialitzacions
        initialState.setCostAcc(costMap[initialState.getRow()][initialState.getCol()]); 
        
        //Cua de pendents ordenada segons la heurística dels estats
        PriorityQueue<State> pends = new PriorityQueue<>(Comparator.comparingDouble(State::getHeuristic));
        List<State> visitats = new ArrayList<>();
        List<State> path = new ArrayList<>(); 

        //2. Afegim l'estat inicial a la cua de pendents
        pends.add(initialState);

        //3. Mentres la cua de pendents no estigui buida
        while(!pends.isEmpty()){
            //3.1 Agafem el primer de la cua
            State currentState = pends.poll();
            
            //3.2 Si és igual a l'estat final retornem el cami trobat
            if (currentState.equals(targetState)){
                path = currentState.getCami();
                path.add(currentState);
                break;
            }

            //3.3 Sino Iterem cada successor
            List<State> successors = EvaluateOperators(currentState, targetState);
            for (State successor : successors){
                //3.4 Si el successor no està visitat ni està en la cua de pendents
                if (!visitats.contains(successor) && !pends.contains(successor)){
                        //3.5 Afegim al successor tot el cami d'on venim i afegim a pendents
                        List<State> newCami = new ArrayList<>(currentState.getCami()); 
                        newCami.add(currentState);
                        successor.setCami(newCami);
                        pends.add(successor);
                }
            }
            //3.6 Afegim l'estat actual a visitats i incrementem nodes visitats
            visitats.add(currentState);
            incrementNodesVisitats();
        }

        //4.Retornem camí trobat
        return path.isEmpty() ? null : path;
    }
}

public class Heuristics {
    
    //Heurística basada en distància Manhattan i cost (no admissible)
    public static float Heuristic1(State currentState, State targetState, float[][] map) {
        int dx = Math.abs(targetState.getRow() - currentState.getRow());
        int dy = Math.abs(targetState.getCol() - currentState.getCol());
        float distance = dx + dy;
        float cost = map[currentState.getRow()][currentState.getCol()];

        return distance + cost;
    }  

    //Heurística basada en només distància Euclidiana (no admissible)
    public static float Heuristic2(State currentState, State targetState, float[][] map){
        float dx = targetState.getRow() - currentState.getRow();
        float dy = targetState.getCol() - currentState.getCol();
        float distance = (float) Math.sqrt(dx * dx + dy * dy); 
        
        return distance;
    }

    //Heurística basada en només cost (Admissible)
    public static float Heuristic3(State currentState, State targetState, float[][] map){
        return map[currentState.getRow()][currentState.getCol()];
    }
}

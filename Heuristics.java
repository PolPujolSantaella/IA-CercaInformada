public class Heuristics {
    
    public static float Heuristic1(State currentState, State targetState, float[][] map) {
        float distance = (float) Math.sqrt(Math.pow(targetState.getRow() - currentState.getRow(), 2) + Math.pow(targetState.getCol() - currentState.getCol(), 2));
        return Math.min(distance, 33.0f); 
    }  

    public static float Heuristic2(State currentState, State targetState, float[][] map){
        float estimatedCost = currentState.getCostAcc() + Heuristic1(currentState, targetState, map);
        return Math.min(estimatedCost, 33.0f);
    }

    public static float Heuristic3(State currentState, State targetState, float[][] map){
        float totalEstimatedCost = Heuristic1(currentState, targetState, map) + Heuristic2(currentState, targetState, map);
        return Math.min(totalEstimatedCost, 33.0f);
    }
}

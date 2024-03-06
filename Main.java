import java.util.List;

public class Main {

    public static char[][] OriginalCharMap = {
      {'P','N','N','N','P','P','P','P','P','P'},
      {'P','N','N','N','M','M','P','P','N','P'},
      {'P','N','N','N','M','M','N','N','N','P'},
      {'P','A','A','A','A','A','A','N','N','N'},
      {'P','N','A','C','A','A','A','A','A','N'},
      {'P','A','A','C','M','C','C','A','A','A'},
      {'P','A','M','A','M','M','C','A','A','A'},
      {'A','A','M','A','M','C','C','P','M','P'},
      {'A','A','M','C','M','C','P','P','P','P'},
      {'A','A','C','C','M','C','C','C','C','C'},
    };
    public static Map OriginalMap = new Map(OriginalCharMap);

    public static char[][] CustomCharMap = {
      {'P','A','P','C','P'},
      {'N','B','N','N','N'},
      {'P','P','N','N','N'},
      {'C','N','N','A','N'},
      {'C','N','N','N','C'},
    };
    public static Map CustomMap = new Map(CustomCharMap);

    public static void main(String args[]){ 

      Map map = OriginalMap;
      Map map2 = CustomMap;

      State iniState = new State (0, 0);
      State finalState = new State(map.getLength()-1, map.getLength()-1);
      State finalState2 = new State(map2.getLength()-1, map2.getLength()-1);

      // Declare heuristics
      Heuristic[] heuristics = new Heuristic[3];
      heuristics[0] = Heuristics::Heuristic1;
      heuristics[1] = Heuristics::Heuristic2;
      heuristics[2] = Heuristics::Heuristic3;


      Search[] algorithmsMap1 = new Search[6]; 
      for (int i = 0; i < 3; i++){
        algorithmsMap1[i] = new BestFirstSearch(map.getCostMap(), heuristics[i]);
        algorithmsMap1[i+3] = new AStarSearch(map.getCostMap(), heuristics[i]); 
      }

      Search[] algorithmsMap2 = new Search[6];
      for (int i = 0; i < 3; i++){
        algorithmsMap2[i] = new BestFirstSearch(map2.getCostMap(), heuristics[i]);
        algorithmsMap2[i+3] = new AStarSearch(map2.getCostMap(), heuristics[i]); 
      }

      for (int i = 0; i < algorithmsMap1.length; i++) {
        Search algorithmMap1 = algorithmsMap1[i];
        Search algorithmMap2 = algorithmsMap2[i];
        long start = System.currentTimeMillis();
        List<State> path = algorithmMap1.DoSearch(iniState, finalState);
        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        List<State> path2 = algorithmMap2.DoSearch(iniState, finalState2);
        long end2 = System.currentTimeMillis();

        System.out.println("------------------------------------------------");
        System.out.println("Resultats del algoritme " + algorithmMap1.getClass() + ":");
        
        if (path.isEmpty()) {
            System.out.println("No s'ha trobat un camí");
        } else {
            System.out.println("------------");
            System.out.println("OriginalMap:");
            System.out.println("------------");
            float cost = 0;
            for (State state : path) {
                System.out.print("(" + state.getRow() + ", " + state.getCol() + ")");
                cost = state.getCostAcc();
            }
            
            System.out.println("\nCost Final: "+ cost); 
            long elapsedTime = end - start;
            double seconds = elapsedTime / 1000.0;
            System.out.println("Temps: "+ seconds + "s");
            System.out.println("Numero d'estats visitats:"+ algorithmMap1.getNodesVisitats());
            
            System.out.println("----------");
            System.out.println("CustomMap:");
            System.out.println("----------");
            float cost2 = 0;
            for (State state : path2) {
                System.out.print("(" + state.getRow() + ", " + state.getCol() + ")");
                cost2 = state.getCostAcc();
            }
            
            System.out.println("\nCost Final: "+ cost2); 
            long elapsedTime2 = end2 - start2;
            double seconds2 = elapsedTime2 / 1000.0;
            System.out.println("Temps: "+ seconds2 + "s");
            System.out.println("Numero d'estats visitats:"+ algorithmMap2.getNodesVisitats());
            
            
        }
      }
    }
}




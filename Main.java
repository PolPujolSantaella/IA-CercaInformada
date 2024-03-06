import java.util.List;
import java.util.Scanner;

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
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
    };
    public static Map CustomMap = new Map(CustomCharMap);

    public static void main(String args[]){      

      Map map = OriginalMap;

      State iniState = new State (0, 0);
      State finalState = new State(9, 9);

      // Declare heuristics
      Heuristic[] heuristics = new Heuristic[3];
      heuristics[0] = Heuristics::Heuristic1;
      heuristics[1] = Heuristics::Heuristic2;
      heuristics[2] = Heuristics::Heuristic3;


      Search[] algorithms = new Search[6]; 
      algorithms[0] = new BestFirstSearch(map.getCostMap(), heuristics[0]);
      algorithms[1] = new BestFirstSearch(map.getCostMap(), heuristics[1]);
      algorithms[2] = new BestFirstSearch(map.getCostMap(), heuristics[2]);
      algorithms[3] = new AStarSearch(map.getCostMap(), heuristics[0]);
      algorithms[4] = new AStarSearch(map.getCostMap(), heuristics[1]);      
      algorithms[5] = new AStarSearch(map.getCostMap(), heuristics[2]);

      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);

      for (int i = 0; i < algorithms.length; i++) {
        
        long start = System.currentTimeMillis();
        Search algorithm = algorithms[i];
        List<State> path = algorithm.DoSearch(iniState, finalState);
        long end = System.currentTimeMillis();

        System.out.println("Resultados del algoritmo " + (i + 1) + ":");
        if (path.isEmpty()) {
            System.out.println("No se encontró un camino.");
        } else {
            System.out.println("Camino encontrado:");
            for (State state : path) {
                System.out.println("(" + state.getRow() + ", " + state.getCol() + ") Coste: " + state.getCostAcc()) ;
            }

            long elapsedTime = end - start;
            double seconds = elapsedTime / 1000.0;
            System.out.println("Temps: "+ seconds + "s");
            System.out.println("Numero d'estats visitats:" + path.size());
        }

        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine(); // Esperar a que se presione Enter

      }
    }
}



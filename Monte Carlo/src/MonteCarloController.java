import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MonteCarloController {
    public MonteCarloView view;

    public MonteCarloController(MonteCarloView view) {
        this.view = view;
        this.view.setController(this);
    }

    public void startSimulation() {
        int totalPoints = view.getTotalPoints();
        int numThreads = view.getNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> futures = new ArrayList<>();

        int pointsPerThread = totalPoints / numThreads;

        for (int i = 0; i < numThreads; i++) {
            Callable<Integer> worker = new MonteCarloModel(pointsPerThread);
            Future<Integer> future = executor.submit(worker);
            futures.add(future);
        }

        executor.shutdown();

        int totalPointsInsideCircle = 0;

        for (Future<Integer> future : futures) {
            try {
                totalPointsInsideCircle += future.get();
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        double estimatedPi = 4.0 * totalPointsInsideCircle / totalPoints;
        view.setResult("Estimated Pi: " + estimatedPi);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MonteCarloView view = new MonteCarloView();
                MonteCarloController controller = new MonteCarloController(view);
                view.setVisible(true);
            }
        });
    }
}

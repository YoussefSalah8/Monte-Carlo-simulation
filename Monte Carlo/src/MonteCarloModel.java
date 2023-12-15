import java.util.concurrent.Callable;

public class MonteCarloModel implements Callable<Integer> {
    private final int points;

    public MonteCarloModel(int points) {
        this.points = points;
    }

    @Override
    public Integer call() {
        int pointsInsideCircle = 0;

        for (int i = 0; i < points; i++) {
            double x = Math.random() * 2 - 1; // Random x coordinate between -1 and 1
            double y = Math.random() * 2 - 1; // Random y coordinate between -1 and 1

            // Check if the point is inside the unit circle
            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }

        return pointsInsideCircle;
    }
}

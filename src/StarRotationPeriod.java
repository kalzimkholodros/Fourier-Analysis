import java.util.ArrayList;

public class StarRotationPeriod {
    public static void main(String[] args) {
        // Example light curve data
        double[] lightCurve = {0.2, 0.4, 0.6, 0.8, 1.0, 0.8, 0.6, 0.4, 0.2};

        double rotationPeriod = calculateRotationPeriod(lightCurve);

        if (rotationPeriod > 0) {
            System.out.println("Star rotation period: " + rotationPeriod + " days");
        } else {
            System.out.println("Error: Unable to determine the rotation period.");
        }
    }

    // Method to calculate the rotation period of a star
    public static double calculateRotationPeriod(double[] lightCurve) {
        int N = lightCurve.length;

        // Search for the minimum value in the light curve
        double minIntensity = Double.MAX_VALUE;
        for (double intensity : lightCurve) {
            if (intensity < minIntensity) {
                minIntensity = intensity;
            }
        }

        // Find the indices where the intensity equals the minimum value
        ArrayList<Integer> minIndices = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (lightCurve[i] == minIntensity) {
                minIndices.add(i);
            }
        }

        // Calculate the time differences between consecutive minimum indices
        ArrayList<Integer> timeDifferences = new ArrayList<>();
        for (int i = 1; i < minIndices.size(); i++) {
            int diff = minIndices.get(i) - minIndices.get(i - 1);
            timeDifferences.add(diff);
        }

        // Find the greatest common divisor of the time differences
        int rotationPeriod = calculateGCD(timeDifferences);

        // Convert the rotation period to days
        double rotationPeriodInDays = (double) rotationPeriod / N;

        return rotationPeriodInDays;
    }

    // Method to calculate the greatest common divisor of an array of integers
    public static int calculateGCD(ArrayList<Integer> numbers) {
        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = gcd(result, numbers.get(i));
        }
        return result;
    }

    // Method to calculate the greatest common divisor of two integers
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

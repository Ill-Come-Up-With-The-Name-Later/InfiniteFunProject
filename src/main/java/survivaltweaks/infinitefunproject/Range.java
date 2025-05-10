package survivaltweaks.infinitefunproject;

import java.util.Random;

/**
 * A range of numbers
 */
public class Range {

    private int min;
    private int max;

    private final Random random = new Random();

    /**
     * Constructs a range. A range
     * represents a range of numbers
     *
     * @param min: The minimum number
     * @param max: The maximum number
     */
    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Sets the minimum value
     *
     * @param min: The minimum value
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum value
     *
     * @param max: The maximum value
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets the minimum value
     *
     * @return The minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * Gets the maximum value
     *
     * @return The maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * Gets a random float between
     * min and max
     *
     * @return A random float between min and max
     */
    public int getRandom() {
        return random.nextInt(min, max + 1);
    }
}
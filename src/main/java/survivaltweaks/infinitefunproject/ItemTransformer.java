package survivaltweaks.infinitefunproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility methods to transform items with
 * functions
 */
public class ItemTransformer {

    private static final Random random = new Random();

    /**
     * Applies a function to an item
     *
     * @param item: The item to modify
     * @param function: The function to apply to item
     * @return The modified item
     * @param <T>: The type of item
     */
    public static <T> T transform(T item, Function<T, T> function) {
        return function.apply(item);
    }

    /**
     * Transforms an ArrayList of items
     * using a function
     *
     * @param items: An ArrayList of items
     * @param function: The function to apply to items
     * @return The modified item ArrayList
     * @param <T>: The type of items in the ArrayList
     */
    public static <T> ArrayList<T> transform(ArrayList<T> items, Function<T, T> function) {
        return items.stream().map(function).
                collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Transforms a List of items
     * using a function
     *
     * @param items: A List of items
     * @param function: The function to apply to items
     * @return The modified item list
     * @param <T>: The type of items in the List
     */
    public static <T> List<T> transform(List<T> items, Function<T, T> function) {
        return items.stream().map(function).
                collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to an item based on random chance
     *
     * @param item: The item to apply function to
     * @param function: The function to apply to item
     * @param chance: The chance to apply function to item
     * @return The item with or without transformation
     * @param <T>: The type of item
     */
    public static <T> T transform(T item, Function<T, T> function, int chance) {
        return random.nextInt(0, 100) <= chance ? function.apply(item) : item;
    }

    /**
     * Applies a function to an ArrayList of items
     * based on random chance
     *
     * @param items: An ArrayList of items
     * @param function: The function to apply to items
     * @param chance: The chance to apply function to items
     * @return The ArrayList with transformations
     * @param <T>: The type of items in the ArrayList
     */
    public static <T> ArrayList<T> transform(ArrayList<T> items, Function<T, T> function, int chance) {
        return items.stream().map(x -> random.nextInt(0, 100) <= chance ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to a List of items
     * based on random chance
     *
     * @param items: A List of items
     * @param function: The function to apply to items
     * @param chance: The chance to apply function to items
     * @return The List with or without transformations
     * @param <T>: The type of items in the List
     */
    public static <T> List<T> transform(List<T> items, Function<T, T> function, int chance) {
        return items.stream().map(x -> random.nextInt(0, 100) <= chance ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to an item
     * if it meets a condition
     *
     * @param item: The item to apply function to
     * @param condition: The condition the item must meet
     * @param function: The function to apply to item
     * @return The item with or without modification
     * @param <T>: The type of item
     */
    public static <T> T transformConditionally(T item, Predicate<T> condition, Function<T, T> function) {
        return condition.test(item) ? function.apply(item) : item;
    }


    /**
     * Applies a function to an ArrayList
     * of items based upon if an item meets a
     * condition
     *
     * @param items: An ArrayList of items
     * @param condition: The condition that an item must meet
     * @param function: The function to apply to an item if it meets condition
     * @return The modified ArrayList
     * @param <T>: The type of items in the ArrayList
     */
    public static <T> ArrayList<T> transformConditionally(ArrayList<T> items, Predicate<T> condition, Function<T, T> function) {
        return items.stream().map(x -> condition.test(x) ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to a List of
     * items based upon if an item meets
     * a condition
     *
     * @param items: A list of items
     * @param condition: The condition an item must meet
     * @param function: The function to apply to an item
     * @return The modified List
     * @param <T>: The type of items in the List
     */
    public static <T> List<T> transformConditionally(List<T> items, Predicate<T> condition, Function<T, T> function) {
        return items.stream().map(x -> condition.test(x) ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to an item
     * based upon if it meets a condition
     * and random chance
     *
     * @param item: The item
     * @param condition: The condition item must meet
     * @param function: The function to apply to item
     * @param chance: The chance for function to apply if item meets condition
     * @return The item with or without modification
     * @param <T>: The type of item
     */
    public static <T> T transformConditionally(T item, Predicate<T> condition, Function<T, T> function, int chance) {
        return condition.test(item) && random.nextInt(0, 100) <= chance ? function.apply(item) : item;
    }

    /**
     * Applies a function to items in an
     * ArrayList depending upon if an item
     * meets a condition and random chance
     *
     * @param items: An ArrayList of items
     * @param condition: The condition an item must meet
     * @param function: The function to apply to items that meet condition
     * @param chance: The chance to apply function if an item meets condition
     * @return The modified ArrayList
     * @param <T>: The type of items in the ArrayList
     */
    public static <T> ArrayList<T> transformConditionally(ArrayList<T> items, Predicate<T> condition, Function<T, T> function, int chance) {
        return items.stream().map(x -> condition.test(x) && random.nextInt(0, 100) <= chance ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a function to items in a List
     * based upon if an item meets a condition
     * and random chance
     *
     * @param items: A List of items
     * @param condition: The condition an item must meet
     * @param function: The function to apply if an item meets condition
     * @param chance: The chance to apply function to an item if it meets condition
     * @return The modified List
     * @param <T>: The type of items in the list
     */
    public static <T> List<T> transformConditionally(List<T> items, Predicate<T> condition, Function<T, T> function, int chance) {
        return items.stream().map(x -> condition.test(x) && random.nextInt(0, 100) <= chance ? function.apply(x) : x)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
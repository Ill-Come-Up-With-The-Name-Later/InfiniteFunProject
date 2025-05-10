package survivaltweaks.infinitefunproject;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An ItemSelector used to randomly pick items
 *
 * @param <T> The type of the ItemSelector items
 */
public class ItemSelector<T> {

    private final ArrayList<Entry<T>> entries = new ArrayList<>();
    private static final Random random = new Random();

    /**
     * Constructs a new empty ItemSelector
     */
    public ItemSelector() {

    }

    /**
     * Constructs an ItemSelector with items in it that
     * have weight 1
     *
     * @param items: An ArrayList of items
     */
    public ItemSelector(ArrayList<T> items) {
        items.forEach(x -> this.addEntry(x, 1));
    }

    /**
     * Constructs an ItemSelector with items in it with
     * set weights
     *
     * @param items: A HashMap of items and integer weights
     */
    public ItemSelector(HashMap<T, Integer> items) {
        items.forEach(this::addEntry);
    }

    /**
     * An entry to the ItemSelector
     *
     * @param <T> The type of the ItemSelector items
     */
    public static class Entry<T> {

        /**
         * Quantity types for entries
         */
        public enum Quantity {

            FIXED,
            RANDOM
        }

        private final T item;
        private final int amount;
        private Quantity quantity;
        private final Range range;

        /**
         * Constructs an Entry with one item
         *
         * @param item: An item
         */
        public Entry(T item) {
            this.item = item;
            this.amount = 1;
            this.quantity = Quantity.FIXED;

            this.range = new Range(amount, amount);
        }

        /**
         * Constructs an Entry with a set number
         * of items
         *
         * @param item: The item
         * @param amount: The amount of item
         */
        public Entry(T item, int amount) {
            this.item = item;
            this.amount = amount;
            this.quantity = Quantity.FIXED;

            this.range = new Range(amount, amount);
        }

        /**
         * Constructs an Entry with a random
         * amount of items between min and max
         *
         * @param item: The item
         * @param min: The minimum possible amount of items
         * @param max: The maximum possible amount of items
         */
        public Entry(T item, int min, int max) {
            this.item = item;
            this.amount = min;
            this.quantity = Quantity.RANDOM;

            this.range = new Range(min, max);
        }

        /**
         * Constructs an entry with a random amount
         * of an item
         *
         * @param item: The item
         * @param range: The range specifying the possible amount
         *                of items
         */
        public Entry(T item, Range range) {
            this.item = item;
            this.amount = range.getMin();

            this.range = range;
        }

        /**
         * Gets the item contained in the
         * entry disregarding quantity
         *
         * @return This entry's contained item
         */
        public T getItem() {
            return item;
        }

        /**
         * Gets the entry's item as well
         * as a quantity
         *
         * @return The item and a quantity
         */
        public HashMap<T, Integer> getItemWithQuantity() {
            if(quantity == Quantity.RANDOM) {
                return new HashMap<>() {
                    {
                        put(getItem(), range.getRandom());
                    }
                };
            }

            return new HashMap<>() {
                {
                    put(getItem(), amount);
                }
            };
        }

        /**
         * Finds if this entry contains
         * item
         *
         * @param item: The item
         * @return If this entry contains item
         */
        public boolean contains(T item) {
            return getItem().equals(item);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry<?> entry)) return false;

            return amount == entry.amount && range.getMin() == entry.range.getMin() && range.getMax() == entry.range.getMax()
                    && Objects.equals(item, entry.item) && quantity == entry.quantity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, amount, quantity, range.getMin(), range.getMax());
        }

        @Override
        public String toString() {
            return "[" + range.getMin() + "-" + range.getMax() + "x " + item.toString() + "]";
        }

        /**
         * Gets if the entry contains nothing
         *
         * @return If the entry is empty
         */
        public boolean isEmpty() {
            return getItem() == null;
        }
    }

    /**
     * Adds an item to the ItemSelector
     *
     * @param item: The item to add
     * @param weight: The item's weight
     */
    public void addEntry(T item, int weight) {
        for(int i = 0; i < weight; i++) {
            entries.add(new Entry<>(item));
        }
    }

    /**
     * Adds an item to the ItemSelector
     * with a specified quantity
     *
     * @param item: The item
     * @param amount: The item's quantity
     * @param weight: The item's weight
     */
    public void addEntry(T item, int amount, int weight) {
        for(int i = 0; i < weight; i++) {
            entries.add(new Entry<>(item, amount));
        }
    }

    /**
     * Adds an item to the ItemSelector
     * with a randomized quantity
     *
     * @param item: The item
     * @param min: Minimum quantity of the item
     * @param max: Maximum quantity of the item
     * @param weight: The item's weight
     */
    public void addEntry(T item, int min, int max, int weight) {
        for(int i = 0; i < weight; i++) {
            entries.add(new Entry<>(item, min, max));
        }
    }

    /**
     * Adds an entry to the ItemSelector's
     * entry list
     *
     * @param entry: The entry to add
     * @param weight: The weight of the entry
     */
    public void addEntry(Entry<T> entry, int weight) {
        for(int i = 0; i < weight; i++) {
            entries.add(entry);
        }
    }

    /**
     * Gets an item's weight without
     * regarding quantity
     *
     * @param item: The item
     * @return The item's weight
     */
    public int getWeight(T item) {
        return countEntries(item);
    }

    /**
     * Gets the entry that contains an item.
     * The entry can be null if no entry contains
     * item.
     *
     * @param item: The item to search for
     * @return The entry containing it
     */
    public Entry<T> getEntry(T item) {
        for(Entry<T> entry: entries) {
            if(entry.contains(item)) {
                return entry;
            }
        }

        return null;
    }

    /**
     * Counts the number of entries that contain
     * the item
     *
     * @param item: The item
     * @return The number of occurrences in entries
     */
    private int countEntries(T item) {
        int count = 0;

        for(Entry<T> entry : entries) {
            if(entry.contains(item)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Determines if an entry contains
     * the specified item
     *
     * @param item: The item
     * @return If the item is found in an entry
     */
    public boolean contains(T item) {
        for(Entry<T> entry : entries) {
            if(entry.contains(item)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Selects entries based on a condition
     *
     * @param condition: The condition
     * @return A list of entries whose items meet condition
     */
    public ArrayList<Entry<T>> selectEntriesConditionally(Predicate<T> condition) {
        return entries.stream().filter(x -> condition.test(x.getItem())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Selects items based on a condition
     *
     * @param condition: The condition
     * @return A list of items meeting condition
     */
    public ArrayList<T> selectItemsConditionally(Predicate<T> condition) {
        return entries.stream().map(Entry::getItem).filter(condition).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets a random item from the ItemSelector
     * disregarding quantity.
     *
     * @return The chosen item
     */
    public T rollItem() {
        return entries.get(random.nextInt(0, entries.size())).getItem();
    }

    /**
     * Gets random items from the ItemSelector.
     *
     * @param count: The number of items to get
     * @return A list of the chosen items
     */
    public ArrayList<T> rollItems(int count) {
        ArrayList<T> items = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            items.add(rollItem());
        }

        return items;
    }

    /**
     * Gets random items from the ItemSelector.
     * Does not allow duplicates of items
     *
     * @param count: The number of items to get
     * @return A list of the chosen items
     */
    public ArrayList<T> rollItemsNoDuplicates(int count) {
        ArrayList<T> items = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            T item = rollItem();

            if(!items.contains(item)) {
                items.add(item);
            }
        }

        return items;
    }

    /**
     * Gets a random item from the ItemSelector
     * with a quantity
     *
     * @return The item and its quantity
     */
    public HashMap<T, Integer> rollItemWithQuantity() {
        return entries.get(random.nextInt(0, entries.size())).getItemWithQuantity();
    }

    /**
     * Gets random items from the ItemSelector
     * with quantities
     *
     * @param count: The number of items to get
     * @return A map of chosen items and their quantities
     */
    public ArrayList<HashMap<T, Integer>> rollItemsWithQuantity(int count) {
        ArrayList<HashMap<T, Integer>> items = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            items.add(rollItemWithQuantity());
        }

        return items;
    }

    /**
     * Gets random items from the ItemSelector
     * with quantity.
     * Does not allow duplicates of items
     *
     * @param count: The number of items to get
     * @return A list of the chosen items
     */
    public ArrayList<HashMap<T, Integer>> rollItemsWithQuantityNoDuplicates(int count) {
        ArrayList<HashMap<T, Integer>> items = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            HashMap<T, Integer> item = rollItemWithQuantity();

            if(!items.contains(item)) {
                items.add(item);
            }
        }

        return items;
    }

    /**
     * Gets the chance for an item to come
     * out of the ItemSelector
     *
     * @param item: The item
     * @return The chance to roll the item
     */
    public float chance(T item) {
        return ((float) countEntries(item) / entries.size()) * 100;
    }

    /**
     * A map of all unique items and their
     * weights
     *
     * @return A map of all items and their weights
     */
    public HashMap<T, Integer> weights() {
        HashMap<T, Integer> weights = new HashMap<>();

        for(Entry<T> entry : entries) {
            if(weights.containsKey(entry.getItem())) {
                weights.put(entry.getItem(), weights.get(entry.getItem()) + 1);
                continue;
            }

            weights.put(entry.getItem(), 1);
        }

        return weights;
    }

    /**
     * A map of all unique entries in
     * the ItemSelector and the weights
     * of the entries
     *
     * @return A map of all unique entries in
     *         the ItemSelector and the weights
     *         of the entries
     */
    public HashMap<Entry<T>, Integer> entryWeights() {
        HashMap<Entry<T>, Integer> weights = new HashMap<>();

        for(Entry<T> entry : entries) {
            if(weights.containsKey(entry)) {
                weights.put(entry, weights.get(entry) + 1);
                continue;
            }

            weights.put(entry, 1);
        }

        return weights;
    }

    /**
     * Returns a map of all items and their
     * chance to be drawn from the ItemSelector
     *
     * @return A map of all items and their
     *         chance to be drawn from the ItemSelector
     */
    public HashMap<T, Float> chances() {
        HashMap<T, Float> chances = new HashMap<>();

        for(Entry<T> entry : entries) {
            if(chances.containsKey(entry.getItem())) {
                continue;
            }

            chances.put(entry.getItem(), chance(entry.getItem()));
        }

        return chances;
    }

    /**
     * Randomly apply a function to items
     * in a list
     *
     * @param items: A list of items
     * @param function: The function to modify the items
     * @param chance: The chance for function to modify items
     * @return The list of items after modifications
     */
    public ArrayList<T> applyFunction(ArrayList<T> items, Function<T, T> function, int chance) {
        return ItemTransformer.transform(items, function, chance);
    }

    /**
     * Randomly apply a function to an item
     *
     * @param item: The item
     * @param function: The function to apply
     * @param chance: The chance to apply function
     * @return The item with or without modification
     */
    public T applyFunction(T item, Function<T, T> function, int chance) {
        return ItemTransformer.transform(item, function, chance);
    }

    /**
     * Randomly apply a function to items
     * in a list with a condition
     *
     * @param items: A list of items
     * @param condition: The condition to check before running function
     * @param function: The function to modify the items
     * @param chance: The chance for function to modify items
     * @return The list of items after modifications
     */
    public ArrayList<T> applyFunctionConditionally(ArrayList<T> items, Predicate<T> condition, Function<T, T> function, int chance) {
        return ItemTransformer.transformConditionally(items, condition, function, chance);
    }

    /**
     * Randomly apply a function to an item
     * if it meets a condition
     *
     * @param item: The item
     * @param condition: The condition to check before running function
     * @param function: The function to apply
     * @param chance: The chance to apply function
     * @return The item with or without modification
     */
    public T applyFunctionConditionally(T item, Predicate<T> condition, Function<T, T> function, int chance) {
        return ItemTransformer.transformConditionally(item, condition, function, chance);
    }

    /**
     * Apply a function to items
     * in a list
     *
     * @param items: A list of items
     * @param function: The function to modify the items
     * @return The list of items after modifications
     */
    public ArrayList<T> applyFunction(ArrayList<T> items, Function<T, T> function) {
        return ItemTransformer.transform(items, function);
    }

    /**
     * Apply a function to an item
     *
     * @param item: The item
     * @param function: The function to apply
     * @return The item with or without modification
     */
    public T applyFunction(T item, Function<T, T> function) {
        return ItemTransformer.transform(item, function);
    }

    /**
     * Apply a function to items
     * in a list with a condition
     *
     * @param items: A list of items
     * @param condition: The condition to check before running function
     * @param function: The function to modify the items
     * @return The list of items after modifications
     */
    public ArrayList<T> applyFunctionConditionally(ArrayList<T> items, Predicate<T> condition, Function<T, T> function) {
        return ItemTransformer.transformConditionally(items, condition, function);
    }

    /**
     * Apply a function to an item
     * if it meets a condition
     *
     * @param item: The item
     * @param condition: The condition to check before running function
     * @param function: The function to apply
     * @return The item with or without modification
     */
    public T applyFunctionConditionally(T item, Predicate<T> condition, Function<T, T> function) {
        return ItemTransformer.transformConditionally(item, condition, function);
    }

    /**
     * Reformats a HashMap into a list
     * of items with an item occurring
     * <code>map.get(item)</code> times in the list
     *
     * @param itemMap: The item map containing items and quantities
     * @return A reformatted list of items
     */
    public ArrayList<T> reformatMap(HashMap<T, Integer> itemMap) {
        return new ArrayList<>(itemMap.keySet());
    }

    /**
     * Reformats a list of HashMaps into a list
     * of items with an item occurring
     * <code>map.get(item)</code> times in the list
     *
     * @param mapList: The list of item map containing items and quantities
     * @return A reformatted list of items
     */
    public ArrayList<T> reformatMap(ArrayList<HashMap<T, Integer>> mapList) {
        return mapList.stream().flatMap(x -> x.keySet().stream()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return "Item Selector {entries = " + entries + "}";
    }

    /**
     * Returns an unmodifiable view of
     * all entries
     *
     * @return An unmodifiable view of
     *         all entries
     */
    public List<Entry<T>> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
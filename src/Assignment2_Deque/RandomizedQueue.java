package Assignment2_Deque;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;

    private int itemsCounter;

    private static final int BIGGER = 0;
    private static final int SMALLER = 1;
    private boolean iterationInProgress;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        itemsCounter = 0;

    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return itemsCounter;
    }

    // add the item
    public void enqueue(Item item) {
        checkItem(item);
        if (itemsCounter >= items.length - 1) {
            resizeAndCopy(BIGGER);
        }
        items[itemsCounter] = item;
        itemsCounter++;
    }

    private void resizeAndCopy(int direction) {
        int newSize = direction == BIGGER ? items.length * 2 : items.length / 2;
        Item[] newArray = (Item[]) new Object[newSize];
        int pos = 0;
        for (Item item : items) {
            if (item != null) {
                newArray[pos] = item;
                pos++;
            }
        }
        items = newArray;
    }


    // remove and return a random item
    public Item dequeue() {
        checkRemovePossibility();
        int randomPosition = StdRandom.uniform(itemsCounter);
        Item result = items[randomPosition];
        if (randomPosition == size() - 1) {
            items[randomPosition] = null;
        } else {
            items[randomPosition] = items[size() - 1];
            items[size() - 1] = null;
        }
        itemsCounter--;
        if (itemsCounter <= items.length / 4) {
            resizeAndCopy(SMALLER);
        }
        return result;
    }


    // return (but do not remove) a random item
    public Item sample() {
        checkRemovePossibility();
        int randomPosition = StdRandom.uniform(itemsCounter);
        return items[randomPosition];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        iterationInProgress = true;
        final int[] currentItemCounter = {0};
        HashSet<Integer> chosenPositions = new HashSet<>();

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                boolean hasNext = currentItemCounter[0] != itemsCounter;
                if (!hasNext)
                    iterationInProgress = false;
                return hasNext;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                int randomPos = StdRandom.uniform(itemsCounter);
                while (chosenPositions.contains(randomPos)) {
                    randomPos = StdRandom.uniform(itemsCounter);
                }
                chosenPositions.add(randomPos);
                Item result = items[randomPos];
                currentItemCounter[0]++;
                return result;
            }
        };
    }

    private void checkItem(Item item) {
        if (item == null) throw new NullPointerException();
    }

    private void checkRemovePossibility() {
        if (iterationInProgress) throw new UnsupportedOperationException();
        if (isEmpty()) throw new NoSuchElementException();
    }
}

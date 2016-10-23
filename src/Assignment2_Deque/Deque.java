package Assignment2_Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private boolean iterationInProgress;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItem(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        n++;
    }

    private void checkItem(Item item) {
        if (item == null) throw new NullPointerException();
    }

    // add the item to the end
    public void addLast(Item item) {
        checkItem(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkRemovePossibility();
        Item item = first.item;
        first = first.next;
        if (size() == 1)
            last = first;
        else
            first.previous = null;
        n--;
        return item;
    }

    private void checkRemovePossibility() {
        if (iterationInProgress) throw new UnsupportedOperationException();
        if (isEmpty()) throw new NoSuchElementException();
    }

    // remove and return the item from the end
    public Item removeLast() {
        checkRemovePossibility();
        Item item = last.item;
        Node newLast = last.previous;
        last.previous = null;
        last = newLast;
        if (size() == 1)
            first = last;
        else
            newLast.next = null;
        n--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        iterationInProgress = true;

        Iterator<Item> iterator = new Iterator<Item>() {
            int currentPosition = 0;
            Node currentNode = first;

            @Override
            public boolean hasNext() {
                boolean hasNext = currentPosition < size();
                if (!hasNext) iterationInProgress = false;
                return hasNext;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item result = currentNode.item;
                currentNode = currentNode.next;
                currentPosition++;
                return result;
            }
        };



        return iterator;
    }
}

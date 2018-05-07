package codinginterview.book;

import java.util.HashSet;
import java.util.Set;

public class Ch2_LinkedLists {
    /**
     * 2.1 Remove Dupes: Remove duplicates from an
     * unsorted linked list
     */
    @SuppressWarnings("All")
    public void removeDupes(Node<Integer> node) {
        final Set<Integer> buffer = new HashSet<>();

        Node<Integer> prev = null;
        while (node != null) {
            if (buffer.contains(node.item)) {
                prev.next = node.next;
            } else{
                buffer.add(node.item);
                prev = node;
            }
            node = node.next;
        }
    }

    /**
     * 2.7 Intersection: Given two (singly) linked list, determine if the two lists intersect.
     * Return the intersecting node. The intersection is defined based on reference not value
     */
    public <E> Node<E> findIntersection(Node<E> firstList, Node<E> secondList) {
        int firstSize = 0;
        int secondSize = 0;

        Node<E> firstCurr = firstList;
        Node<E> secondCurr = secondList;
        while (firstCurr.next != null) {
            firstCurr = firstCurr.next;
            firstSize++;
        }
        while (secondCurr.next != null) {
            secondCurr = secondCurr.next;
            secondSize++;
        }

        //Tails don't intersect, so the lists can't intersect
        if (firstCurr != secondCurr) {
            return null;
        }

        final int sizeDiff = firstSize - secondSize;

        Node<E> longer = sizeDiff > 0 ? firstList : secondList;
        Node<E> shorter = sizeDiff > 0 ? secondList : firstList;

        int position = 0;
        while (position < sizeDiff && longer.next != null) {
            longer = longer.next;
            position++;
        }

        while (longer.next != shorter.next) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer.next;
    }

    private static final class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

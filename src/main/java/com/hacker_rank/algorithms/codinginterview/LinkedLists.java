package com.hacker_rank.algorithms.codinginterview;

import java.util.HashSet;
import java.util.Set;

public class LinkedLists {
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

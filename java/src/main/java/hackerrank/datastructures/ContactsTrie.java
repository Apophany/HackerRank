package hackerrank.datastructures;

import java.util.Scanner;

public class ContactsTrie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        final Node root = new Node();

        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();

            if ("add".equals(op)) {
                add(contact, root);
            }
            if("find".equals(op)) {
                System.out.println(find(contact, root));
            }
        }
    }

    private static void add(String contact, Node root) {
        Node curr = root;
        for(int i = 0; i < contact.length(); i++) {
            int index = contact.charAt(i) - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            curr = curr.children[index];
            curr.count++;
        }
    }

    private static int find(String contact, Node root) {
        int count = 0;
        int position = 0;

        Node curr = root;
        while (curr != null && position < contact.length()) {
            int index = contact.charAt(position) - 'a';
            if (position == contact.length() - 1) {
                count = curr.children[index] != null ? curr.children[index].count : 0;
                break;
            }

            curr = curr.children[index];
            position++;
        }

        return count;
    }

    private static final class Node {
        final Node[] children = new Node[26];
        int count = 0;
    }
}

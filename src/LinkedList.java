/*
A linked list is essentially an object in an object in an object...... infinitely.
The final one will have the next nested object as null / empty
 */
import java.util.Scanner;

class LinkedList {
    /*
    This is how a linked list looks like. Data with the next object.
    But in most programs, you will be setting Node node = null initially.
    Or you can do so now too. All that's different is what when you run the program, your node will be empty, and here we're just preloading some data you can already work with.
    In all essence, basically skipping some insert()s
     */
    Node node = new Node(1, new Node(2, new Node (3, new Node(4, new Node(5, null)))));

    void insert(int data) {
        /*
         If It's empty, we need to handle it because otherwise our mutatedNode.child while loop will cause an error
         */
        if (node == null) {
            node = new Node(data, null);
            return;
        }

        /*
        Now the other case, which is basically everything else.
        mutatedNode stores a copy of the node which we can modify
        Now here, we need to parse until we reach the last node. That will be mutatedNode. and then we can effectively edit its child to be a new Node.
         */
        Node mutatedNode = node;
        while (mutatedNode.child != null) {
            mutatedNode = mutatedNode.child;
        }

        mutatedNode.child = new Node(data, null);

        System.out.println("Inserted the node");
    }

    void delete() {
        /*
        If the node is empty, then we need to skip as this is an underflow case.
         */
        if (node == null) {
            System.out.println("Node is empty");
            return;
        }

        /*
        If its has only one child, we need to handle its deletion because otherwise our mutatedNode.child.child while loop will cause an error
         */
        if (node.child == null) {
            node = null;
            return;
        }

        /*
        Now the other case, which is basically everything else.
        mutatedNode stores a copy of the node which we can modify
        Now here, we need to parse until we reach the second last node, so that we can remove the last node.
        So that is why unlike insert, we are doing mutatedNode.child.child, not mutatedNode.child, to reach the second last instead of the last element
        The reason is that when currently inside a node's child (the last node) how do we delete the thing we are currently in? If we are in the second last node, we can just set the child to node, effectively causing the second last element to now become the last element.
        Thus now, we have successfully deleted an element
         */
        Node mutatedNode = node;
        while (mutatedNode.child.child != null) {
            mutatedNode = mutatedNode.child;
        }

        mutatedNode.child = null;

        System.out.println("Deleted the last node");
    }

    void display() {
        Node mutatedNode = node;
        while (mutatedNode != null) {
            System.out.print(mutatedNode.data + " -> ");
            mutatedNode = mutatedNode.child;
        }

        /*
        Why are we doing this?
        Well its simply just so show that our element beyond the last element; its child, is null or empty.
        Its optional. But if you remove it now you just have an awkward " -> " at the last when you display. Try it.
         */
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList obj = new LinkedList();
        Scanner sc = new Scanner(System.in);

        /*
        This is a do-while + switch case.
        Normally, this is not what the question will make you do
        But here, we are doing this so that we can test any of the functions in any sequence
         */
        int choice;
        do {
            System.out.println("1. Insert\n2. Delete\n3. Display\n4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter value to insert: ");
                    obj.insert(sc.nextInt());
                    break;
                case 2:
                    obj.delete();
                    break;
                case 3:
                    obj.display();
                    break;
            }
        } while (choice != 4);
    }
}

class Node {
    int data;
    Node child;

    Node (int data, Node child) {
        this.data = data;
        this.child = child;
    }
}
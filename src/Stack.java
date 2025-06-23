/*
Stack is basically an array where you can only delete and insert from the back (last element)
Remember the recursion dry run Jane ma'am taught us? We drew a stack in the rightmost column right? Yeah, that's essentially it
 */

import java.util.Scanner;

class Stack {
    int[] stack = new int[10];
    int pointer = -1;

    void push(int data) {
        /*
        Check for overflow.
        Overflow is essentially when the array is full, and you cannot fill towards the right no more
        If the pointer's index is at the last then we cannot insert
         */
        if (pointer == (stack.length - 1)) {
            System.out.println("Stack Overflow");
            return;
        }
        pointer++;
        stack[pointer] = data;
        System.out.println("Inserted element at " + pointer);
    }

    int pop() {
        /*
        Check for underflow.
        Underflow is essentially when an array is empty; nothing exists, and there is nothing you can delete
        If the pointer's index is still -1, then there is nothing to delete. No element, yet.
         */
        if (pointer == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }

        // Why are we doing this below line? well we want to access the value + delete the value.
        int value = stack[pointer];
        // now we delete it
        stack[pointer] = 0;
        pointer--;
        return value;
    }

    void display() {
        /*
        Just a simple for loop that iterates from 0 to array length and prints the value at that index.
        You know it already so im not going in detail
         */
        for (int i = 0; i < stack.length; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack obj = new Stack();
        Scanner sc = new Scanner(System.in);

        /*
        This is a do-while + switch case.
        Normally, this is not what the question will make you do
        But here, we are doing this so that we can test any of the functions in any sequence
         */

        int choice;
        do {
            System.out.println("1. Push\n2. Pop\n3. Display\n4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter value to push: ");
                    obj.push(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Popped value: " + obj.pop());
                    break;
                case 3:
                    obj.display();
                    break;
            }
        } while (choice != 4);
    }
}
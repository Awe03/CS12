/*
A Queue is a collection of values, with two pointers: front and rear
In all essence, it is basically an array where you can only delete from the front (left side) and insert from the rear (right side)
 */

import java.util.Scanner;

class Queue {
    /*
    I am initializing a queue of size 5 for demonstration purposes. Set it to whatever length you want.
    If we are accepting length of the queue from the user, then let int[] queue; and then initialize it later in the constructor.
    When doing so, you'll also need to initialize length to queue.length or whatever size variable is used
    Alternatively, you can just use array.length everywhere, but having a length variable just makes it easier
     */
    int[] queue = new int[5];
    int length = queue.length;
    int rear = -1, front = -1;

    void insert(int element) {
        /*
        Check for overflow.
        Overflow is essentially when the array is full, and you cannot fill no more
        This is when the right side of the queue has no remaining space for insertion (when rear == max length - 1)
        IE: say length is 5. Last working index (pointer) is 4. So when its 4 we prevent further insertion
         */
        if (rear == length - 1) {
            System.out.println("Stack Overflow");
            return;
        }

        /*
        We are initializing front and rear when something is inserted
        Why are we doing this?
        Well, initially, nothing exists, so front and rear = -1. This way, when nothing exists and nothing has been inserted yet, we can check for underflow.
        Ie: For underflow (deletion) protection, we can see that its -1 so we are fine.
        But once we are GOING to insert something, we know that there will be some value. So we are thus initializing to 0,0 so now we can insert.
         */
        if (front == -1 && rear == -1) {
            front = 0;
            rear = 0;
        } else {
            /*
            Now you might be questioning. Why do we have the ++ here in an else? Why not outside?
            Well this is to prevent the fact that when we insertFromRear the rear should FIRST INSERT, then move.
            If we had removed this, by the logic, say you have the following queue
            _ _ _ _ _
            ^ both front and rear are here after the initialize (above) of front = 0 and rear = 0
            If this was not in an else, in the first insertion, the rear++ would still run, and then it would insert here
            _ _ _ _ _
              ^ Value would be inserted here cause rear got incremented. So we want the rear to, in the first case, first insert then move. Otherwise, we should always be moving first then inserting.
            That's how insertions work. First move pointer, then insert
             */
            rear++;
        }

        queue[rear] = element;
        System.out.println("Inserted element " + element + " at " + rear);
    }

    int delete() {
        /*
        Check for underflow.
        Underflow is essentially when an array is empty; nothing exists, and there is nothing you can delete
        If both pointer's index is still -1, then this indicates that insertion has not been executed yet.
        Because when we insert for the first time, we initialize front and rear to 0. This is an indicator that something has been inserted.
        So as long as its -1 (default value) we know that nothing has been inserted
         */
        if (front == -1 && rear == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }

        int element = queue[front];
        queue[front] = 0;
        System.out.println("Deleted element at " + front);
        front++;

        /*
        Resetting back to original -1, -1 start if all have been deleted.
        This is so that once its completely empty, we can reset back to the original state.
        This way, when nothing is present in the queue, when deleting again we can easily check for underflow by checking if front and rear are -1.
        There is also another reason for this. If the queue is at the end, then we cannot insert anymore
        Take the following queue:
        1 2 3 4 5
        ^       ^
        f       r
        Deleting all, we get:
        _ _ _ _ _
                ^
                ^
                f
                r
        We cannot insert by default. It's at the max length. There is no 6th position.
        So we reset back to -1, -1 so we can have a fresh start
         */
        if (front > rear) {
            System.out.println("Resetting");
            front = -1;
            rear = -1;
        }
        return element;
    }

    void display() {
        /*
        Just a simple for loop that iterates from 0 to array length and prints the value at that index.
        You know it already so im not going in detail
         */
        for (int i = 0; i < length; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue obj = new Queue();
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
                    System.out.println(obj.delete());
                    break;
                case 3:
                    obj.display();
                    break;
            }
        } while (choice != 4);
    }
}
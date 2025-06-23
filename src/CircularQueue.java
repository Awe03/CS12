/*
A Circular Queue is pretty much the same as a Queue
 Only change is that unlike a queue, if the array is full (rear == length - 1), rear will jump back to 0 so you can insert back at the start, if available
What am I talking about? Take a look at the following queue:
_ _ 3 4 5
^ ^ These are empty
Rear pointer is at '5' and front pointer is at '3'. In a regular queue, you cannot insert anymore because the rear pointer is at the end of the array
But in this case, a circular queue, the rear will jump back to 0 since there are 2 free spaces so you can insert more.
So say I were to now insert 6.
6 _ 3 4 5
Now rear is at '6' and front is still at '3'. This is how a circular queue works.
 */

import java.util.Scanner;

class CircularQueue {
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
        This is when the right side of the queue has no remaining space for insertion
        Now the below condition might seem scary but don't worry. If all hell breaks loose, just mug it up.
        So how this works is some neat mathematics.
        Take a look at the following queue:
        1 2 3 4 5
        ^       ^
        f       r
        Front is at '1' and rear is at '5'. let us delete twice.
        _ _ 3 4 5
            ^   ^
            f   r
        Let us insert 6 since we can
        6 _ 3 4 5
        ^   ^
        r   f
        And insert 7
        6 7 3 4 5
          ^ ^
          r f
        Front is at '3' and rear is at '7'. Notice how the queue is full
        Now, we should prevent further insertions.
        So substituting into the formula,
        front = 2, rear = 1, length = 5
        (2 == (1 + 1) % 5) => (2 == 2) => true, so we have an overflow

        Even in normal cases, take a look at the following queue:
        1 2 3 4 5
        ^       ^
        f       r
        Front is at '1' and rear is at '5'. Substituting into the formula,
        front = 0, rear = 4, length = 5
        (0 == (4 + 1) % 5) => (0 == 0) => true, so we have an overflow

        In any other case, take a look at the following queue:
        _ 2 3 4 5
          ^     ^
          f     r
        Front is at '2' and rear is at '5'. Substituting into the formula,
        front = 1, rear = 4, length = 5
        (1 == (4 + 1) % 5) => (1 == 0) => false, so we do not have an overflow
         */
        if (front == (rear + 1) % length) {
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
        } else if (rear == length - 1) {
            /*
            If the rear pointer is at the end of the array, we jump back to 0.
            This is the circular part of the circular queue.
            Take a look at the following queue:
            1 2 3 4 5
            ^       ^
            f       r
            Deleting twice, we get:
            _ _ 3 4 5
                ^   ^
                f   r
            Say we insert '6', we will jump back to the start and insert it there
            6 _ 3 4 5
            ^   ^
            r   f
             */
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

        /*
        Now, the below, honestly, you're better off just mugging it up.
        But if you want to understand, here is how it works:
         */

        /*
        Why do we do a front++ later and not above after println?
        Well all you need to know is that it solves an edge case of
        Take the following queue:
         */

        if (front == rear) {
            /*
            If front and rear are the same, it means that we have just deleted the last element in the queue
            Both pointers are pointing to the same element now. So we reset pointers back to -1, -1, so that we can check for underflow next time
             */
            System.out.println("Resetting");
            front = -1;
            rear = -1;
        } else {
            /*
            Now if front is not equal to the rear - 1, it means that there are still elements in the queue
            So we need to move the front pointer to the next element. Same as queue so far.

            However, this is a circular queue. So if front reaches the end, then we need to jump back to the start
             */
            if (front == length - 1) {
                front = 0;
            } else {
                front++;
            }
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
        CircularQueue obj = new CircularQueue();
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
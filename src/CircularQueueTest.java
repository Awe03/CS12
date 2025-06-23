public class CircularQueueTest {

    public static void runAllTests() {
        System.out.println("=".repeat(80));
        System.out.println("          COMPREHENSIVE CIRCULAR QUEUE TEST SUITE");
        System.out.println("=".repeat(80));

        testBasicOperations();
        testOverflowScenarios();
        testWrappingBehavior();
        testComplexMixedOperations();
        testEdgeCases();
        testStressScenarios();
        testBoundaryConditions();
        testEmptyQueueBehavior();
        testSingleElementOperations();
        testAlternatingOperations();

        System.out.println("=".repeat(80));
        System.out.println("              ALL COMPREHENSIVE TESTS COMPLETED");
        System.out.println("=".repeat(80));
    }

    public static void testBasicOperations() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 1: BASIC OPERATIONS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Starting with empty queue");
        q.display();

        System.out.println("\n→ Insert sequence: 10, 20, 30");
        q.insert(10);
        q.insert(20);
        q.insert(30);
        q.display();

        System.out.println("\n→ Delete 2 elements");
        System.out.println("  Deleted: " + q.delete());
        System.out.println("  Deleted: " + q.delete());
        q.display();

        System.out.println("\n→ Insert 2 more: 40, 50");
        q.insert(40);
        q.insert(50);
        q.display();

        System.out.println("\n→ Delete remaining elements");
        while (true) {
            int deleted = q.delete();
            if (deleted == -1) break;
            System.out.println("  Deleted: " + deleted);
            q.display();
        }
    }

    public static void testOverflowScenarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 2: OVERFLOW SCENARIOS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Fill queue to capacity with 100, 200, 300, 400, 500");
        for (int i = 1; i <= 5; i++) {
            q.insert(i * 100);
        }
        q.display();

        System.out.println("\n→ Attempt to insert 600 (should overflow)");
        q.insert(600);
        q.display();

        System.out.println("\n→ Attempt to insert 700 (should overflow again)");
        q.insert(700);
        q.display();

        System.out.println("\n→ Delete one element to make space");
        System.out.println("  Deleted: " + q.delete());
        q.display();

        System.out.println("\n→ Now insert 600 (should succeed)");
        q.insert(600);
        q.display();

        System.out.println("\n→ Attempt 700 again (should overflow)");
        q.insert(700);
        q.display();
    }

    public static void testWrappingBehavior() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 3: CIRCULAR WRAPPING BEHAVIOR");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Fill queue completely: 1, 2, 3, 4, 5");
        for (int i = 1; i <= 5; i++) {
            q.insert(i);
        }
        q.display();

        System.out.println("\n→ Delete first 3 elements");
        for (int i = 0; i < 3; i++) {
            System.out.println("  Deleted: " + q.delete());
            q.display();
        }

        System.out.println("\n→ Insert 6, 7, 8 (should wrap around to indices 0, 1, 2)");
        q.insert(6);
        q.display();
        q.insert(7);
        q.display();
        q.insert(8);
        q.display();

        System.out.println("\n→ Queue state analysis:");
        System.out.println("  Array: [6, 7, 8, 4, 5]");
        System.out.println("  Front should be at index 3 (element 4)");
        System.out.println("  Rear should be at index 2 (element 8)");

        System.out.println("\n→ Delete all remaining elements in FIFO order");
        for (int i = 0; i < 5; i++) {
            int deleted = q.delete();
            System.out.println("  Deleted: " + deleted);
            q.display();
            if (deleted == -1) break;
        }
    }

    public static void testComplexMixedOperations() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 4: COMPLEX MIXED OPERATIONS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Complex sequence: Insert 3, Delete 1, Insert 2, Delete 2, Insert 3");

        System.out.println("\n  Phase 1: Insert 11, 22, 33");
        q.insert(11); q.insert(22); q.insert(33);
        q.display();

        System.out.println("\n  Phase 2: Delete 1 element");
        System.out.println("    Deleted: " + q.delete());
        q.display();

        System.out.println("\n  Phase 3: Insert 44, 55");
        q.insert(44); q.insert(55);
        q.display();

        System.out.println("\n  Phase 4: Delete 2 elements");
        System.out.println("    Deleted: " + q.delete());
        System.out.println("    Deleted: " + q.delete());
        q.display();

        System.out.println("\n  Phase 5: Insert 66, 77, 88");
        q.insert(66); q.insert(77); q.insert(88);
        q.display();

        System.out.println("\n  Phase 6: Fill to capacity");
        q.insert(99);
        q.display();
        q.insert(111); // Should overflow
        q.display();

        System.out.println("\n  Phase 7: Empty the queue completely");
        int count = 0;
        while (true) {
            int deleted = q.delete();
            if (deleted == -1) break;
            count++;
            System.out.println("    Step " + count + " - Deleted: " + deleted);
            q.display();
        }
    }

    public static void testEdgeCases() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 5: EDGE CASES");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Edge Case 1: Delete from empty queue");
        System.out.println("  Deleted: " + q.delete());
        q.display();

        System.out.println("\n→ Edge Case 2: Multiple deletes from empty queue");
        for (int i = 1; i <= 3; i++) {
            System.out.println("  Attempt " + i + " - Deleted: " + q.delete());
        }

        System.out.println("\n→ Edge Case 3: Insert 1, delete 1, repeat 5 times");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  Cycle " + i + ":");
            q.insert(i * 10);
            q.display();
            System.out.println("    Deleted: " + q.delete());
            q.display();
        }

        System.out.println("\n→ Edge Case 4: Fill queue, empty it, fill again");
        System.out.println("  First fill:");
        for (int i = 1; i <= 5; i++) {
            q.insert(i);
        }
        q.display();

        System.out.println("  Empty completely:");
        while (q.delete() != -1) {
            q.display();
        }

        System.out.println("  Second fill:");
        for (int i = 10; i <= 14; i++) {
            q.insert(i);
        }
        q.display();
    }

    public static void testStressScenarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 6: STRESS SCENARIOS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Stress Test 1: Rapid fill and empty cycles");
        for (int cycle = 1; cycle <= 3; cycle++) {
            System.out.println("\n  Cycle " + cycle + " - Fill with values " + (cycle*10) + " to " + (cycle*10+4));
            for (int i = 0; i < 5; i++) {
                q.insert(cycle * 10 + i);
            }
            q.display();

            System.out.println("  Cycle " + cycle + " - Empty completely");
            int deleteCount = 0;
            while (true) {
                int deleted = q.delete();
                if (deleted == -1) break;
                deleteCount++;
                if (deleteCount <= 2 || deleteCount >= 4) { // Show first 2 and last few
                    System.out.println("    Deleted: " + deleted);
                    q.display();
                }
            }
        }

        System.out.println("\n→ Stress Test 2: Alternating operations (50 operations)");
        System.out.println("  Pattern: Insert 2, Delete 1, repeat...");
        int insertValue = 100;
        for (int i = 1; i <= 10; i++) {
            System.out.println("  Round " + i + ":");
            q.insert(insertValue++);
            q.insert(insertValue++);
            q.display();
            System.out.println("    Deleted: " + q.delete());
            q.display();
        }

        System.out.println("\n  Final cleanup - empty remaining elements:");
        while (true) {
            int deleted = q.delete();
            if (deleted == -1) break;
            System.out.println("    Final deleted: " + deleted);
            q.display();
        }
    }

    public static void testBoundaryConditions() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 7: BOUNDARY CONDITIONS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Boundary Test 1: Index wrapping at maximum");
        System.out.println("  Fill queue: 1,2,3,4,5");
        for (int i = 1; i <= 5; i++) {
            q.insert(i);
        }
        q.display();

        System.out.println("\n  Delete 4 elements (front will be at index 4)");
        for (int i = 0; i < 4; i++) {
            System.out.println("    Deleted: " + q.delete());
            q.display();
        }

        System.out.println("\n  Insert 4 elements (rear should wrap around)");
        for (int i = 6; i <= 9; i++) {
            q.insert(i);
            q.display();
        }

        System.out.println("\n  Delete remaining elements (front should wrap)");
        while (true) {
            int deleted = q.delete();
            if (deleted == -1) break;
            System.out.println("    Wrapped delete: " + deleted);
            q.display();
        }
    }

    public static void testEmptyQueueBehavior() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 8: EMPTY QUEUE BEHAVIOR");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Test multiple delete attempts on empty queue");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  Attempt " + i + ": " + q.delete());
        }

        System.out.println("\n→ Insert after empty queue operations");
        q.insert(999);
        q.display();

        System.out.println("\n→ Verify queue works normally after empty state");
        q.insert(888);
        q.insert(777);
        q.display();

        System.out.println("\n→ Delete all and test empty again");
        while (q.delete() != -1) {
            q.display();
        }

        System.out.println("\n→ Final empty state verification");
        System.out.println("  Delete attempt: " + q.delete());
        q.display();
    }

    public static void testSingleElementOperations() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 9: SINGLE ELEMENT OPERATIONS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Single element cycle test (10 cycles)");
        for (int i = 1; i <= 10; i++) {
            System.out.println("  Cycle " + i + ":");
            q.insert(i * 111);
            q.display();
            System.out.println("    Deleted: " + q.delete());
            q.display();
        }

        System.out.println("\n→ Single element with overflow attempt");
        q.insert(555);
        q.display();

        System.out.println("  Try to insert when single element exists:");
        for (int i = 1; i <= 4; i++) {
            q.insert(i * 100);
            q.display();
        }

        System.out.println("  Try overflow:");
        q.insert(1000);
        q.display();

        System.out.println("\n  Empty and verify:");
        while (q.delete() != -1) {
            q.display();
        }
    }

    public static void testAlternatingOperations() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST 10: ALTERNATING OPERATIONS");
        System.out.println("=".repeat(60));

        CircularQueue q = new CircularQueue();

        System.out.println("→ Pattern: I-I-D-I-D-D-I-I-I-D-D (I=Insert, D=Delete)");

        int[] insertValues = {10, 20, 30, 40, 50, 60, 70};
        int insertIndex = 0;

        String[] operations = {"I", "I", "D", "I", "D", "D", "I", "I", "I", "D", "D"};

        for (int i = 0; i < operations.length; i++) {
            System.out.println("\n  Step " + (i+1) + ": Operation " + operations[i]);

            if (operations[i].equals("I")) {
                if (insertIndex < insertValues.length) {
                    q.insert(insertValues[insertIndex++]);
                } else {
                    q.insert(999); // Fallback value
                }
            } else {
                System.out.println("    Deleted: " + q.delete());
            }
            q.display();
        }

        System.out.println("\n→ Final cleanup");
        while (true) {
            int deleted = q.delete();
            if (deleted == -1) break;
            System.out.println("    Final: " + deleted);
            q.display();
        }
    }

    public static void main(String[] args) {
        runAllTests();
    }
}

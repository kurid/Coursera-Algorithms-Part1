/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class PuzzleChecker {

    public static void main(String[] args) {

        File testFolder = new File("./8puzzle/puzzle11.txt");
//        File[] listOfTestFiles = testFolder.listFiles();
//        int numberOfTestFiles = Objects.requireNonNull(listOfTestFiles).length;
//        numberOfTestFiles = 1;
        runTest(testFolder);

//        for (int i = 0; i < numberOfTestFiles; i++) {
//            runTest(listOfTestFiles[i]);
//        }
    }

    private static void runTest(File text) {
        In in = new In(text.getPath());
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

//        System.out.println(initial);
//        System.out.println("=========INITIAL===================\n");

//        System.out.println(initial.twin());
//
//        Iterator<Board> iterator = initial.neighbors().iterator();
//        while (iterator.hasNext()) {
//            Board board = iterator.next();
//            Iterator<Board> iterator2 = board.neighbors().iterator();
//            System.out.println(board);
//            System.out.println("________");
//            while (iterator2.hasNext()) {
//                System.out.println(iterator2.next());
//            }
//            System.out.println("============================\n");
//        }
//    }

    // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class Solver {

    private boolean isSolvable;
    private final Queue<Board> solution;
    private int neededMove;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solution = new Queue<>();
        MinPQ<SearchNode> mainPQ = new MinPQ<>(Comparator.comparingInt(SearchNode::getPriority));

        MinPQ<SearchNode> twinPQ = new MinPQ<>(Comparator.comparingInt(SearchNode::getPriority));
        mainPQ.insert(new SearchNode(initial, null, 0));
        twinPQ.insert(new SearchNode(initial.twin(), null, 0));
        solveForInitialAndTwin(mainPQ, twinPQ);
    }

    private void solveForInitialAndTwin(MinPQ<SearchNode> mainPQ, MinPQ<SearchNode> twinPQ) {
        while (true) {
            SearchNode minFromMain = mainPQ.delMin();
            SearchNode minFromTwin = twinPQ.delMin();
            solution.enqueue(minFromMain.board);
            if (minFromMain.board.isGoal() || minFromTwin.board.isGoal()) {
                isSolvable = minFromMain.board.isGoal();
                if(isSolvable) {
                    neededMove = minFromMain.alreadyDoneMoves;
                }
                break;
            }
            addNeighborsToPQ(minFromMain, mainPQ);
            addNeighborsToPQ(minFromTwin, twinPQ);
        }
    }

    private void addNeighborsToPQ(SearchNode searchNode, MinPQ<SearchNode> pq) {
        for (Board neighbor : searchNode.board.neighbors()) {
            if (!neighbor.equals(searchNode.predecessor)) {
                pq.insert(new SearchNode(neighbor, searchNode.board,
                        searchNode.alreadyDoneMoves + 1));
            }
        }
    }


    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }


    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable) return -1;
        return neededMove;
    }


    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }


    private class SearchNode {

        Board board;
        Board predecessor;
        int alreadyDoneMoves;

        SearchNode(Board board, Board predecessor, int alreadyDoneMoves) {
            this.board = board;
            this.predecessor = predecessor;
            this.alreadyDoneMoves = alreadyDoneMoves;
        }

        int getPriority() {
            return alreadyDoneMoves + board.manhattan();
        }
    }


}
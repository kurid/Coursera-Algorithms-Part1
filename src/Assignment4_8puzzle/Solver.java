package Assignment4_8puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private boolean isSolvable;
    private SearchNode goal;
    private int neededMove;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> mainPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();

        mainPQ.insert(new SearchNode(initial, null, 0));
        twinPQ.insert(new SearchNode(initial.twin(), null, 0));

        solveForInitialAndTwin(mainPQ, twinPQ);
    }

    private void solveForInitialAndTwin(MinPQ<SearchNode> mainPQ, MinPQ<SearchNode> twinPQ) {
        while (true) {
            SearchNode minFromMain = mainPQ.delMin();
            SearchNode minFromTwin = twinPQ.delMin();
            if (minFromMain.board.isGoal() || minFromTwin.board.isGoal()) {
                isSolvable = minFromMain.board.isGoal();
                if (isSolvable) {
                    neededMove = minFromMain.alreadyDoneMoves;
                    goal = minFromMain;
                }
                break;
            }
            addNeighborsToPQ(minFromMain, mainPQ);
            addNeighborsToPQ(minFromTwin, twinPQ);
        }
    }

    private void addNeighborsToPQ(SearchNode searchNode, MinPQ<SearchNode> pq) {
        for (Board neighbor : searchNode.board.neighbors()) {
            if (searchNode.predecessor == null || !neighbor.equals(searchNode.predecessor.board)) {
                SearchNode node = new SearchNode(neighbor, searchNode,
                        searchNode.alreadyDoneMoves + 1);
                pq.insert(node);
                neededMove = node.alreadyDoneMoves;
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
        if(!isSolvable) return null;
        Stack<Board> solution = new Stack<>();
        SearchNode tmp = goal;
        while (tmp != null) {
            Board board = tmp.board;
            solution.push(board);
            tmp = tmp.predecessor;
        }
        return solution;
    }


    private class SearchNode implements Comparable<SearchNode> {

        Board board;
        SearchNode predecessor;
        int alreadyDoneMoves;
        final int manhattan;

        SearchNode(Board board, SearchNode predecessor, int alreadyDoneMoves) {
            this.board = board;
            this.predecessor = predecessor;
            this.alreadyDoneMoves = alreadyDoneMoves;
            this.manhattan = board.manhattan();
        }

        int getPriority() {
            return alreadyDoneMoves + manhattan;
        }

        @Override
        public int compareTo(SearchNode that) {
            return getPriority() - that.getPriority();
        }
    }


}
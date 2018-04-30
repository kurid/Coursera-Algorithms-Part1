package Assignment4_8puzzle;

import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int[][] blocks;

    private int blankX;

    private int blankY;

    private final int dimension;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) throw new IllegalArgumentException();
        dimension = blocks.length;
        this.blocks = createCopyAndSaveZeroPosition(blocks);
        calculateHammingAndManhattan();
    }

    private int[][] createCopyAndSaveZeroPosition(int[][] blocks) {
        int[][] result = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int numberInBlock = blocks[i][j];
                if (numberInBlock == 0) {
                    blankX = i;
                    blankY = j;
                }
                result[i][j] =  numberInBlock;
            }
        }
        return result;
    }

        // board dimension n
        public int dimension () {
            return dimension;

        }

        // number of blocks out of place
        public int hamming () {
            int blocksOutOfPlace = 0;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int numberInBlock = blocks[i][j];
                    if (numberInBlock == 0) continue;

                    int expectedNumber = 1 + dimension * i + j;
                    if (expectedNumber != numberInBlock) {
                        blocksOutOfPlace++;
                    }
                }
            }
            return blocksOutOfPlace;
        }

        // sum of Manhattan distances between blocks and goal
        public int manhattan () {
            int distanceBetweenBlocksAndGoal = 0;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int numberInBlock = blocks[i][j];
                    if(numberInBlock == 0) continue;
                    int expectedRow = (numberInBlock - 1) / dimension;
                    int expectedColumn = (numberInBlock - 1) % dimension;
                    distanceBetweenBlocksAndGoal += Math.abs(i - expectedRow);
                    distanceBetweenBlocksAndGoal += Math.abs(j - expectedColumn);
                }
            }
            return distanceBetweenBlocksAndGoal;
        }


        private void calculateHammingAndManhattan () {

        }

        // is this board the goal board?
        public boolean isGoal () {
            return hamming() == 0;
        }

        // a board that is obtained by exchanging any pair of blocks
        public Board twin () {
            int[][] twinBoard = createCopy(blocks);
            boolean firstBlockSelected = false;
            int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int numberInBlock = blocks[i][j];
                    if (numberInBlock != 0) {
                        if (firstBlockSelected) {
                            x2 = i;
                            y2 = j;
                        } else {
                            x1 = i;
                            y1 = j;
                            firstBlockSelected = true;
                        }
                    }
                }
            }

            swap(twinBoard, x1, y1, x2, y2);
            return new Board(twinBoard);
        }

        private void swap ( int[][] twinBoard, int x1, int y1, int x2, int y2){
            int tmp = twinBoard[x1][y1];
            twinBoard[x1][y1] = twinBoard[x2][y2];
            twinBoard[x2][y2] = tmp;
        }

        // does this board equal y?
        public boolean equals (Object y){
            if (this == y) return true;
            if (y == null || this.getClass() != y.getClass()) return false;

            Board that = (Board) y;
            if (dimension() != that.dimension()) return false;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (blocks[i][j] != that.blocks[i][j])
                        return false;
                }
            }
            return true;
        }

        // all neighboring boards
        public Iterable<Board> neighbors () {
            Stack<Board> neighborsCollection = new Stack<>();
            addNeighborByPosition(neighborsCollection, blankX - 1, blankY);
            addNeighborByPosition(neighborsCollection, blankX + 1, blankY);
            addNeighborByPosition(neighborsCollection, blankX, blankY - 1);
            addNeighborByPosition(neighborsCollection, blankX, blankY + 1);
            return neighborsCollection;
        }

        private void addNeighborByPosition (Stack < Board > neighbors,int x, int y){
            if (isValidPosition(x, y)) {
                int[][] copyBlocks = createCopy(blocks);
                swap(copyBlocks, blankX, blankY, x, y);
                neighbors.push(new Board(copyBlocks));
            }
        }

        private boolean isValidPosition ( int x, int y){
            return x >= 0 && x < dimension && y >= 0 && y < dimension;
        }

        private int[][] createCopy ( int[][] blocks){
            int[][] copyBlocks = new int[dimension][dimension];
            for (int i = 0; i < dimension(); i++) {
                System.arraycopy(blocks[i], 0, copyBlocks[i], 0, dimension);
            }
            return copyBlocks;
        }

        // string representation of this board (in the output format specified below)
        public String toString () {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dimension()).append("\n");
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    stringBuilder.append(String.format("%2d ", blocks[i][j]));
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
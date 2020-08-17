public class Pain {
    String sequence1, sequence2;
    int match, mismatch, gap;
    Node[][] gameBoard;
    boolean filledBoard;

    public Pain(String seq1, String seq2, int match, int mismatch, int gap){
        sequence1 = seq1;
        sequence2 = seq2;
        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
    }

    public String[] fillBoard(){
        Node currentNode, prevNode, aboveNode, aboveLeftNode;
        gameBoard = new Node[sequence2.length()+1][sequence1.length()+1];
        String[] alignments;

        for (int i=0; i < gameBoard.length; i++){
            for (int j=0; j < gameBoard[i].length; j++){
                gameBoard[i][j] = new Node(i, j);
                gameBoard[i][j].setScore(initialScore(i, j));
                gameBoard[i][j].setPrev(pointers(i, j));
            }
        }

        for (int irow = 1; irow < gameBoard.length; irow++){
            for (int icol = 1; icol < gameBoard[irow].length; icol++){
                currentNode = gameBoard[irow][icol];
                prevNode = gameBoard[irow][icol-1];
                aboveNode = gameBoard[irow-1][icol];
                aboveLeftNode = gameBoard[irow-1][icol-1];
                correctScore(currentNode, prevNode, aboveNode, aboveLeftNode);
            }
        }
        alignments = (String[]) bufferingseq();
        return alignments;
    }

    private void correctScore(Node currentNode, Node prevNode, Node aboveNode, Node aboveLeftNode){
        int rowScore = aboveNode.getScore() + gap;
        int colScore = prevNode.getScore() + gap;
        int matchOrMismatch = aboveLeftNode.getScore() + gap;

        if (sequence2.charAt(currentNode.getRow()-1) == sequence1.charAt(currentNode.getCol()-1)){
            matchOrMismatch += match;
        } else {
            matchOrMismatch += mismatch;
        }
        if (rowScore >= colScore) {
            if (matchOrMismatch >= rowScore){
                currentNode.setScore(matchOrMismatch);
                currentNode.setPrev(aboveLeftNode);
            } else {
                currentNode.setScore(rowScore);
                currentNode.setPrev(aboveNode);
            }
        } else {
            if (matchOrMismatch >= colScore) {
                currentNode.setScore(matchOrMismatch);
                currentNode.setPrev(aboveLeftNode);
            } else {
                currentNode.setScore(colScore);
                currentNode.setPrev(prevNode);
            }
        }
    }

    private String[] bufferingseq() {
        StringBuffer buf1 = new StringBuffer();
        StringBuffer buf2 = new StringBuffer();
        Node currentNode = gameBoard[gameBoard.length-1][gameBoard[0].length-1];
        
        while (done(currentNode)){
            if (currentNode.getRow() - currentNode.getPrev().getRow() == 1) {
                buf2.insert(0, sequence2.charAt(currentNode.getRow()-1));
            } else {
                buf2.insert(0, '-');
            }
            if (currentNode.getCol() - currentNode.getPrev().getCol() == 1) {
                buf1.insert(0, sequence1.charAt(currentNode.getCol()-1));
            } else {
                buf1.insert(0, '-');
            }
            currentNode = currentNode.getPrev();
        }
        String[] alignments = new String[] {buf1.toString(), buf2.toString()};
        return alignments;
    }

    private boolean done(Node currentNode) {
        return currentNode.getPrev() != null;
    }
    
    private int initialScore(int row, int col) {
        if (row == 0 && col != 0){
            return col* gap;
        } else if (col == 0 && row != 0){
            return row * gap;
        } else {
            return 0;
        }
    }

    private Node pointers(int row, int col){
        if (row == 0 && col != 0){
            return gameBoard[row][col-1];
        } else if (col == 0 && row != 0){
            return gameBoard[row-1][col];
        } else {
            return null;
        }
    }
}
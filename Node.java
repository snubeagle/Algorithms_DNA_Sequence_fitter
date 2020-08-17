public class Node {
    private Node prev;
    private int score, row, col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return Integer.valueOf(score);
    }

    public void setPrev(Node node){
        prev = node;
    }

    public Node getPrev(){
        return prev;
    }

    public int getRow(){
        return Integer.valueOf(row);
    }

    public int getCol(){
        return Integer.valueOf(col);
    }

    @Override
    public String toString(){
        return ("Cell: " + row + ", " + col + ": score=" + score + ", previous Cell=" + prev);
    }
}
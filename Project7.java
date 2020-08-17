
import java.util.Scanner;

public class Project7 {
    public static void main(String[] args){
        int match = 1;
        int mismatch = -1;
        int gap = -2;
        Scanner sc = new Scanner(System.in);
        String[] msg = {"Enter first sequence or press enter for default: ", "Enter second sequence or press enter for default: "};
        String[] alignments;
        
        System.out.println(msg[0]);
        String seq1 = sc.nextLine();
        
        if (seq1.isEmpty()){
            seq1 = "AACAGTTACC";
        }
        
        System.out.println(msg[1]);
        String seq2 = sc.nextLine();
        
        if (seq2.isEmpty()){
            seq2 = "TAAGGTCA";
        }
        
        Pain algorithm = new Pain(seq1, seq2, match, mismatch, gap);
        alignments = algorithm.fillBoard();

        System.out.println("Sequence 1: " + alignments[0]);
        System.out.println("Sequence 1: " + alignments[1]);

        sc.close();
    }


}
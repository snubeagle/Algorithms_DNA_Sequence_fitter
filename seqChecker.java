public class seqChecker {

    public String seqCheck(final String seq) {
        boolean badInput = true;
        final int i = 0;
        final char[] goodChars = { 'A', 'C', 'G', 'T' };

        while (badInput) {
            final String check = Character.toString(seq.charAt(i));
            if (!(new String(goodChars).contains(check))) {
                System.out.println("Invalid Input, input must be a combination of A, C, G, and T only");
            }
            if ((i == seq.length() - 1) && (new String(goodChars).contains(check))) {
                badInput = false;
            }
        }
        final String rseq = new String(seq);
        return rseq;
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class SieveOfEratosthenes {

    public static final int LOWEST_PRIME_NUMBER = 2;

    public static void main(String[] args) {

        int number = 0;
        boolean isValid = false;
        Scanner input = new Scanner(System.in);

        while (!isValid)
        {
            System.out.print("Enter int >= 2 : ");
            if (input.hasNextInt())
            {
                number = input.nextInt();
            }
            if (number >= LOWEST_PRIME_NUMBER)
            {
                int[] sequence = sieve(number);
                System.out.print(nonCrossedOutSubseqToString(sequence));
                isValid = true;
            }
            input.nextLine();
        }

    }

    public static int[] createSequence(int N) {
        int seqLength = N-LOWEST_PRIME_NUMBER;
        int[] sequence = new int[seqLength+1];
        int n = LOWEST_PRIME_NUMBER;
        for (int i = 0; i <= seqLength; i++)
            sequence[i] = n++;
        System.out.println(Arrays.toString(sequence)
                .replace("[","")
                .replace("]",""));
        return sequence;
    }

    public static void crossOutHigherMultiples(int[] sequence, int n) {
        if (sequence != null && n >= LOWEST_PRIME_NUMBER)
        {
            for (int i = n; i < sequence.length; i++)
            {
                if (sequence[i] % n == 0 && sequence[i] > 0)
                    sequence[i] = -sequence[i];
            }
        }
    }

    public static int[] sieve(int N) {
         int[] sequence = createSequence(N);
         StringBuilder oldSequence = new StringBuilder();
         for (int i = LOWEST_PRIME_NUMBER; i <= N; i++)
         {
             crossOutHigherMultiples(sequence, i);
             StringBuilder newSequence = sequenceToString(sequence);
             assert newSequence != null;
             if (newSequence.length() != oldSequence.length())
                System.out.println(newSequence);
             oldSequence = newSequence;
         }
         return sequence;
    }

    public static StringBuilder sequenceToString (int[] sequence) {
        StringBuilder sequenceString = new StringBuilder();
        if (sequence != null)
        {
            for (int i = 0; i < sequence.length; i++)
            {
                if (sequence[i] < 0)
                    sequenceString.append("[").append(-sequence[i]).append("]");
                else
                    sequenceString.append(sequence[i]);
                if (i < sequence.length-1)
                    sequenceString.append(", ");
            }
            return sequenceString;
        }
        return null;
    }

    public static String nonCrossedOutSubseqToString (int[] sequence) {
        if (sequence != null)
        {
            boolean isFirst = true;
            StringBuilder primeSequence = new StringBuilder();
            for (int i : sequence)
            {
                if (isFirst)
                {
                    primeSequence.append(i);
                    isFirst = false;
                }
                else if (i > 0)
                    primeSequence.append(", ").append(i);
            }
            return primeSequence.toString();
        }
        return null;
    }

}

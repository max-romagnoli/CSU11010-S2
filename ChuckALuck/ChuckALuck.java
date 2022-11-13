import java.util.Scanner;

public class ChuckALuck {

    public static final String HOUSE_WINS = "The House won.";
    public static final String PLAYER_WINS = "You won.";


    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        double balance;
        int bet;
        String tmp;
        boolean isValid = false;
        boolean quit = false;

        while(!isValid)
        {
            System.out.print("Enter the amount of cash you have : ");
            if (input.hasNextDouble())
            {
                balance = input.nextDouble();
                if (balance > 0)
                {
                    Wallet theWallet = new Wallet();
                    theWallet.put(balance);
                    isValid = true;

                    while(!quit)
                    {
                        System.out.println();
                        System.out.println("1 <-- TRIPLE\n2 <-- FIELD\n3 <-- HIGH\n4 <-- LOW\nQUIT <-- STOP PLAYING");
                        System.out.println();
                        System.out.print("Select the type of bet or enter 'quit' if you want to stop : ");
                        if (input.hasNextInt() && theWallet.check()>0)
                        {
                            bet = input.nextInt();
                            System.out.println();

                            if (bet >= 1 && bet <= 4)
                            {
                                resolveBet(bet, theWallet, input);
                            }
                            else
                            {
                                System.out.println("Invalid input: select number from 1 to 4.");
                                input.nextLine();
                            }
                        }
                        else
                        {
                            tmp = input.next();
                            quit = tmp.equalsIgnoreCase("quit");
                            if(theWallet.check() > 0)
                            {
                                System.out.println("Insufficient balance.");
                                quit = true;
                            }
                            if(!quit)
                                System.out.println("Invalid input: select number from 1 to 4.");
                            else
                            {
                                System.out.println();
                                System.out.printf("GAME SUMMARY\nYou won %d times.\n", theWallet.getWinCount());
                                System.out.printf("Winnings: %.2f\n", theWallet.getWinnings());
                                System.out.printf("Losses: %.2f\n", theWallet.getLosses());
                            }
                        }
                    }
                }
                else
                    System.out.println("Invalid input: balance can't be negative");
            }
            else
                System.out.println("Invalid input.");
            input.nextLine();
        }

    }

    public static void resolveBet(int betType, Wallet theWallet, Scanner input) throws InterruptedException {

        double tryBet;
        double bet;
        boolean isEnough = false;
        double winnings;

        System.out.printf("Your current balance is %.2f.\n", theWallet.check());

        while(!isEnough)
        {
            System.out.print("Enter the amount you want to bet : ");
            tryBet = input.nextDouble();
            System.out.println();
            if(theWallet.get(tryBet))
            {
                bet = tryBet;
                isEnough = true;
                Dice dice1 = new Dice();
                Dice dice2 = new Dice();
                Dice dice3 = new Dice();
                int roll1 = dice1.roll();
                int roll2 = dice2.roll();
                int roll3 = dice3.roll();
                System.out.println("Rolling the dices...");
                Thread.sleep(2000);
                System.out.printf("Your roll is: [%d] [%d] [%d]\n", dice1.topFace(), dice2.topFace(), dice3.topFace());
                Thread.sleep(2000);
                System.out.println();

                switch(betType)
                {
                    case 1:
                        if(checkTriple(roll1, roll2, roll3))
                        {
                            winnings = bet+(30*bet);
                            theWallet.put(winnings);
                            System.out.println(PLAYER_WINS);
                            theWallet.incrementWinCount();
                            theWallet.incrementWinnings(winnings);
                        }
                        else
                        {
                            System.out.println(HOUSE_WINS);
                            theWallet.incrementLosses(bet);
                        }
                        break;
                    case 2:
                        if(checkField(roll1, roll2, roll3))
                        {
                            winnings = bet*2;
                            theWallet.put(winnings);
                            System.out.println(PLAYER_WINS);
                            theWallet.incrementWinCount();
                            theWallet.incrementWinnings(winnings);
                        }
                        else
                        {
                            System.out.println(HOUSE_WINS);
                            theWallet.incrementLosses(bet);
                        }
                        break;
                    case 3:
                        if(checkHigh(roll1, roll2, roll3))
                        {
                            winnings = bet*2;
                            theWallet.put(winnings);
                            System.out.println(PLAYER_WINS);
                            theWallet.incrementWinCount();
                            theWallet.incrementWinnings(winnings);
                        }
                        else
                        {
                            System.out.println(HOUSE_WINS);
                            theWallet.incrementLosses(bet);
                        }
                        break;
                    case 4:
                        if(checkLow(roll1, roll2, roll3))
                        {
                            winnings = bet*2;
                            theWallet.put(winnings);
                            System.out.println(PLAYER_WINS);
                            theWallet.incrementWinCount();
                            theWallet.incrementWinnings(winnings);
                        }
                        else
                        {
                            System.out.println(HOUSE_WINS);
                            theWallet.incrementLosses(bet);
                        }
                        break;
                }
                Thread.sleep(1000);
            }
            else
                System.out.println("The bet amount is greater than your balance.");
            input.nextLine();
        }

    }

    public static boolean checkTriple(int r1, int r2, int r3) {
        if((r1==1 && r2==1 && r3==1)||(r1==6 && r2==6 && r3==6))
            return false;
        else return r1==r2 && r2==r3;
    }

    public static boolean checkField(int r1, int r2, int r3) {
        int sideSum = r1+r2+r3;
        return sideSum < 8 || sideSum > 12;
    }

    public static boolean checkHigh(int r1, int r2, int r3) {
        int sideSum = r1+r2+r3;
        if (sideSum <= 10)
            return false;
        else return r1 != r2 || r2 != r3 || sideSum < 12;
    }

    public static boolean checkLow(int r1, int r2, int r3) {
        int sideSum = r1+r2+r3;
        if (sideSum >= 11)
            return false;
        else return r1 != r2 || r2 != r3 || sideSum > 9;
    }
}

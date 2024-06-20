package msg;

public class InvestmentManager {
    private static boolean encerrarProcesso = false;
    private static boolean achouInvestimento = false;
    private static final Investment investment = new Investment();

    public InvestmentManager() {
    }
    
    public static void manageInvestment() {
        try {
            locateInvestment();
            if (!achouInvestimento) {
                throw new Exception("Investment not found");
            }

            processInvestment();
            investment.save();
        } catch (Exception var1) {
            Exception e = var1;
            System.out.println("***** Error: InvestmentManager.manageInvestment() () *****");
            System.out.println("\t" + String.valueOf(e));
        }

    }

    private static void processInvestment() {
        while(!encerrarProcesso) {
            printChoices();
            processChoice();
            if (!encerrarProcesso) {
                investment.print();
                UserInterface.pressEnter();
            }
        }

    }

    private static void processChoice() {
        try {
            char choice = UserInterface.getChar();
            switch (choice) {
                case '\n':
                case '3':
                    encerrarProcesso = true;
                    break;
                case '1':
                    investment.updateInvestmentName();
                    break;
                case '2':
                    investment.updateExpectedReturn();
                    break;
                default:
                    System.out.println("\n\nNot a valid choice\n");
                    UserInterface.pressEnter();
            }
        } catch (Exception var2) {
            Exception e = var2;
            System.out.println("***** Error: InvestmentManager.manageInvestment() *****");
            System.out.println("\t" + String.valueOf(e));
        }

    }

    private static void printChoices() {
        UserInterface.clearScreen();
        System.out.println("\t           UPDATE INVESTMENTS\n\n");
        System.out.println("\t MARTHA STOCKTON GREENGAGE FOUNDATION\n\n");
        System.out.println("\t        1. Update investment name\n");
        System.out.println("\t        2. Update expected return\n");
        System.out.println("\t        3. Exit to investment menu\n\n");
        System.out.println("Enter your choice and press <ENTER>: ");
    }

    private static void locateInvestment() {
        while(!achouInvestimento && !encerrarProcesso) {
            System.out.println("Please enter the number of the investment to be updated (12 digits): ");
            String input = UserInterface.getString();
            achouInvestimento = investment.find(input);
            if (!achouInvestimento) {
                System.out.println("Investment " + input + " was not found.");
                System.out.println("Would you like to enter another investment idn?");
                char choice = UserInterface.getChar();
                if (choice == 'n') {
                    encerrarProcesso = true;
                }
            }
        }

    }
}
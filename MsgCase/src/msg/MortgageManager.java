package msg;


public class MortgageManager {
   private static boolean encerrarProcesso = false;
   private static boolean achouHipoteca = false;
   private static final Mortgage mortgage = new Mortgage();


   public MortgageManager() {
   }


   public static void manageMortgage() {
       try {
           locateMortgage();
           if (!achouHipoteca) {
               throw new Exception("Mortgage not found");
           }


           processMortgage();
           mortgage.save();
       } catch (Exception var1) {
           Exception e = var1;
           System.out.println("***** Error: MortgageManager.manageMortgage() *****");
           System.out.println("\t" + String.valueOf(e));
       }


   }


   private static void processMortgage() {
       while(!encerrarProcesso) {
           printChoices();
           processChoice();
           if (!encerrarProcesso) {
               mortgage.print();
               UserInterface.pressEnter();
           }
       }


   }


   private static void processChoice() {
       try {
           char choice = UserInterface.getChar();
           switch (choice) {
               case '\n':
               case '8':
                   encerrarProcesso = true;
                   break;
               case '1':
                   mortgage.updateMortgageeName();
                   break;
               case '2':
                   mortgage.updatePrice();
                   break;
               case '3':
                   mortgage.updateDate();
                   break;
               case '4':
                   mortgage.updateWeeklyIncome();
                   break;
               case '5':
                   mortgage.updatePropertyTax();
                   break;
               case '6':
                   mortgage.updateInsurancePremium();
                   break;
               case '7':
                   mortgage.updateBalance();
                   break;
               default:
                   System.out.println("\n\nNot a valid choice\n");
                   UserInterface.pressEnter();
           }
       } catch (Exception var2) {
           Exception e = var2;
           System.out.println("***** Error: MortgageManager.manageMortgage() *****");
           System.out.println("\t" + String.valueOf(e));
       }


   }


   private static void printChoices() {
       UserInterface.clearScreen();
       System.out.println("\t           UPDATE MORTGAGES\n\n");
       System.out.println("\t MARTHA STOCKTON GREENGAGE FOUNDATION\n\n");
       System.out.println("\t        1. Update mortgagee name\n");
       System.out.println("\t        2. Update price of home\n");
       System.out.println("\t        3. Update date mortgage issued\n");
       System.out.println("\t        4. Update current weekly income\n");
       System.out.println("\t        5. Update property tax\n");
       System.out.println("\t        6. Update insurance premium\n");
       System.out.println("\t        7. Update mortgage balance\n");
       System.out.println("\t        8. Exit to mortgage menu\n\n");
       System.out.println("Enter your choice and press <ENTER>: ");
   }


   private static void locateMortgage() {
       while(!achouHipoteca && !encerrarProcesso) {
           System.out.println("Please enter the number of the mortgage to be updated (12 digits): ");
           String numeroHipoteca = UserInterface.getString();
           achouHipoteca = mortgage.find(numeroHipoteca);
           if (!achouHipoteca) {
               System.out.println("Mortgage " + numeroHipoteca + " was not found.");
               System.out.println("Would you like to enter another mortgage?");
               char choice = UserInterface.getChar();
               if (choice == 'n') {
                   encerrarProcesso = true;
               }
           }
       }


   }
}

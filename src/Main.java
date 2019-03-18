import java.lang.reflect.*;
import java.util.*;

public class Main
{
    public static Printer printer = Printer.getInstance();

    public static void main(String[] args)
    {
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        int useCaseId;
        char c;

        do {

            System.out.println();
            System.out.println(" Szkeleton\t\tby: smooth_set");
            System.out.println();

            System.out.println("<1>  Orangutan exits with two pandas");
            System.out.println("<2>  Orangutan with a chained panda hits a standalone Panda");
            System.out.println("<3>  Two Orangutans enter");
            System.out.println("<4>  Basic hitBy");
            System.out.println("<5>  Orangutan uses Wardrobe");
            System.out.println("<6>  Armchair notifies LazyPanda");
            System.out.println("<7>  VendingMachine notifies JumpingPanda");
            System.out.println("<8>  Gamemachine notifies ScaredPanda");
            System.out.println("<9>  Panda leaves chain");
            System.out.println("<10> Stucked Orangutan");
            System.out.println("<11> Orangutan hits chain");
            System.out.println("<12> Single Orangutan steps to an empty tile");
            System.out.println("<13> Controller steps an unchained panda randomly");
            System.out.println("<14> Panda breaks BrokenTile with its move and falls");
            System.out.println("<15> Orangutan steps with two Pandas");
            System.out.println("<16> Orangutan steps to Wardrobe with Panda chain");

            System.out.println();
            System.out.println("Valasszon forgatokonyvet! <1-16> :");
            useCaseId = sc.nextInt();

            try
            {
                /* A megadott számnak megfeleltethető függvény hívása reflexióval (Java 8+ kell hozzá)
                 * A függvény nevének felépítése: test + useCaseId
                 * Ha hibás a megadott input akkor NoSuchMethodExceptionnel elszáll a program
                 */
                Method method = Main.class.getDeclaredMethod("test" + useCaseId);
                method.invoke(Main.class);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            System.out.println("Uj forgatokonyv futtatasa? <I/N>");
            c = sc.next().charAt(0);

            if(!(c == 'i' || c == 'I'))
            {
                // Folytatni csak az i/I karakterek valamelyikével lehet. Ha mást választunk a program terminál.
                isRunning = false;
            }
            else
            {
                // Clearscreen IDE-ben is működő változata =)
                for(int i = 0; i < 50; i++)
                {
                    System.out.println();
                }
            }

        } while (isRunning);

        sc.close();

    }


    // Orangutan exits with two pandas
    static void test1()
    {

    }

    // Orangutan with a chained panda hits a standalone Panda
    static void test2()
    {

    }

    // Two Orangutans enter
    static void test3()
    {

    }

    // Basic hitBy
    static void test4()
    {

    }

    // Orangutan uses Wardrobe
    static void test5()
    {

    }

    // Armchair notifies LazyPanda
    static void test6()
    {

    }

    // VendingMachine notifies JumpingPanda
    static void test7()
    {

    }

    // Gamemachine notifies ScaredPanda
    static void test8()
    {

    }

    // Panda leaves chain
    static void test9()
    {

    }

    // Stucked Orangutan
    static void test10()
    {

    }

    // Orangutan hits chain
    static void test11()
    {

    }

    // Single Orangutan steps to an empty tile
    static void test12()
    {

    }

    // Controller steps an unchained panda randomly
    static void test13()
    {

    }

    // Panda breaks BrokenTile with its move and falls
    static void test14()
    {

    }

    // Orangutan steps with two Pandas
    static void test15()
    {

    }

    // Orangutan steps to Wardrobe with Panda chain
    static void test16()
    {

    }
}

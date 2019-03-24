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

            System.out.println();
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
    	Exit exit = new Exit();
        RegularTile exitTile = new RegularTile();
        exit.setIsOn(exitTile);
        exitTile.setContains(exit);
        
        
        Orangutan o = new Orangutan();
        RegularTile o_tile = new RegularTile();
        o.setIsOn(o_tile);
        o_tile.setContains(o);
       
        Panda p1 = new Panda();
        RegularTile p1_tile = new RegularTile();
        p1.setIsOn(p1_tile);
        p1_tile.setContains(p1);
        
        Panda p2 = new Panda();
        RegularTile p2_tile = new RegularTile();
        p2.setIsOn(p2_tile);
        p2_tile.setContains(p2);
        
        o.setPrevTile(p1_tile);
        p1.setPrevTile(p2_tile);
        
        o.setNextAnimal(p1);
        p1.setPrevAnimal(o);
        p1.setNextAnimal(p2);
        p2.setPrevAnimal(p1);
        
        
        printer.functionCall("o", "move", "exitTile");
        o.move(exitTile);
        printer.returnFromFunctionCall();
        
        
        System.out.print("\n**** 1 korrel kesobb ****\n\n");
        printer.functionCall("exit", "nextTurn");
        exit.nextTurn();
        printer.returnFromFunctionCall();
        
        System.out.print("\n**** 1 korrel kesobb ****\n\n");
        printer.functionCall("exit", "nextTurn");
        exit.nextTurn();
        printer.returnFromFunctionCall();
    }

    // Orangutan with a chained panda hits a standalone Panda
    static void test2()
    {
    	Orangutan o = new Orangutan();
        RegularTile o_tile = new RegularTile();
        o.setIsOn(o_tile);
        o_tile.setContains(o);
       
        Panda p1 = new Panda();
        RegularTile p1_tile = new RegularTile();
        p1.setIsOn(p1_tile);
        p1_tile.setContains(p1);
        
        Panda p2 = new Panda();
        RegularTile p2_tile = new RegularTile();
        p2.setIsOn(p2_tile);
        p2_tile.setContains(p2);
        
        o.setPrevTile(p1_tile);
        
        o.setNextAnimal(p1);
        p1.setPrevAnimal(o);
        
        printer.functionCall("o", "move", "tileOfFreePanda");
        o.move(p2_tile);
        printer.returnFromFunctionCall();
    }

    // Two Orangutans enter
    static void test3()
    {
    	
    	Tile entryTile = new Tile();
    	Tile emptyTile = new Tile();
    	Tile nextTile = new Tile();
    	
    	Entry e = new Entry(emptyTile);
    	
    	
    	emptyTile.addNeighbor(entryTile);
    	emptyTile.addNeighbor(nextTile);
    	nextTile.addNeighbor(emptyTile);
    	
    	//entryTile.placeThing(e);
    	
    	e.setIsOn(entryTile);
    	
    	printer.functionCall("e", "addOrangutan", "2");
    	e.addOrangutan(2);
        printer.returnFromFunctionCall();
        
        System.out.print("\n**** 1 korrel kesobb ****\n\n");
    	
    	
        printer.functionCall("e", "nextTurn");
    	e.nextTurn();
    	printer.returnFromFunctionCall();
    	
    	System.out.print("\n**** 1 korrel kesobb ****\n\n");
    	
    	
    	((Orangutan) emptyTile.contains).move(nextTile);
    	
    	printer.functionCall("e", "nextTurn");
    	e.nextTurn();
    	printer.returnFromFunctionCall();
    	
    }

    // Basic hitBy
    static void test4()
    {
		Orangutan orangutan = new Orangutan();
		Tile oTile = new RegularTile();
		Tile vmTile = new RegularTile();
		VendingMachine vm = new VendingMachine(1);

		orangutan.setIsOn(oTile);
		oTile.setContains(orangutan);

		vm.setIsOn(vmTile);
		vmTile.setContains(vm);


    }

    // Orangutan uses Wardrobe
    static void test5()
    {
    	Orangutan o = new Orangutan();
        RegularTile o_tile = new RegularTile();
        o.setIsOn(o_tile);
        o_tile.setContains(o);
        
    	Wardrobe w1 = null;
    	Wardrobe w2 = new Wardrobe(w1);
    	w1 = new Wardrobe(w2);

    	RegularTile w1_tile = new RegularTile();
    	w1.setIsOn(w1_tile);
        w1_tile.setContains(w1);

    	RegularTile w2_tile = new RegularTile();
    	w2.setIsOn(w2_tile);
        w2_tile.setContains(w2);
        
        w2_tile.addNeighbor(new Tile());
        
        printer.functionCall("o", "move", "w1_tile");
        o.move(w1_tile);
        printer.returnFromFunctionCall();
    }

    // Armchair notifies LazyPanda
    static void test6()
    {

    }

    // VendingMachine notifies JumpingPanda
    static void test7()
    {
        RegularTile tile = new RegularTile();
        RegularTile n1 = new RegularTile();
        RegularTile n2 = new RegularTile();                 // creating objects
        VendingMachine vm = new VendingMachine(0);
        JumpingPanda panda = new JumpingPanda();

        vm.setIsOn(tile);
        tile.setContains(vm);  // placing vm on tile

        panda.setIsOn(n1);
        n1.setContains(panda); // placing panda on n1

        tile.addNeighbor(n1);
        tile.addNeighbor(n2);  // blank neighbor

        printer.functionCall("vm", "effect");
        vm.effect();
        printer.returnFromFunctionCall();
    }

    // Gamemachine notifies ScaredPanda
    static void test8()
    {
    	RegularTile tile = new RegularTile();
    	RegularTile n1 = new RegularTile();
    	RegularTile n2 = new RegularTile();
    	GameMachine gm = new GameMachine(0);
    	ScaredPanda sPanda = new ScaredPanda();
    	
    	gm.setIsOn(tile);
    	tile.setContains(gm);
    	
    	sPanda.setIsOn(n1);
    	n1.setContains(sPanda);
    	
    	tile.addNeighbor(n1);
    	tile.addNeighbor(n2);
    	
    	printer.functionCall("gm", "effect");
    	gm.effect();
    	printer.returnFromFunctionCall();
    	
    }

    // Panda leaves chain
    static void test9()
    {
    	Panda panda = new Panda();
    	Panda nextPanda = new Panda();
    	Orangutan animal = new Orangutan();
    	
    	
    	animal.connectChain(nextPanda);
    	animal.connectChain(panda);
    	
    	printer.functionCall("panda", "unchain");
    	panda.unchain();
    	printer.returnFromFunctionCall();
    }

    // Stucked Orangutan
    static void test10()
    {
        Orangutan or = new Orangutan();
        Panda p1 = new Panda();
        GameMachine gm = new GameMachine(5);
        Tile t1 = new Tile();
        Tile t2 = new Tile();
        Tile t3 = new Tile();

        or.setIsOn(t1);
        or.setNextAnimal(p1);

        p1.setIsOn(t2);
        p1.setPrevAnimal(or);

        gm.setIsOn(t3);

        t1.setContains(or);
        t2.setContains(p1);
        t3.setContains(gm);

        or.move(t3);

    }

    // Orangutan hits chain
    static void test11()
    {
    	Orangutan o = new Orangutan();
        RegularTile o_tile = new RegularTile();
        o.setIsOn(o_tile);
        o_tile.setContains(o);
        
        Orangutan ch_o = new Orangutan();
        RegularTile ch_o_tile = new RegularTile();
        ch_o.setIsOn(ch_o_tile);
        ch_o_tile.setContains(ch_o);
        
        Panda p1 = new Panda();
        RegularTile p1_tile = new RegularTile();
        p1.setIsOn(p1_tile);
        p1_tile.setContains(p1);
        
        Panda p2 = new Panda();
        RegularTile p2_tile = new RegularTile();
        p2.setIsOn(p2_tile);
        p2_tile.setContains(p2);
        
        ch_o.setPrevTile(p1_tile);
        p1.setPrevTile(p2_tile);
        
        ch_o.setNextAnimal(p1);
        p1.setPrevAnimal(ch_o);
        p1.setNextAnimal(p2);
        p2.setPrevAnimal(p1);
        
        printer.functionCall("o", "move", "tileOfChainedPanda");
        o.move(p1_tile);
        printer.returnFromFunctionCall();
    }

    // Single Orangutan steps to an empty tile
    static void test12()
    {
    	Orangutan o = new Orangutan();
    	Tile oTile = new Tile();
    	Tile newTile = new Tile();
    	
    	oTile.placeThing(o);
    	oTile.addNeighbor(newTile);
    	
    	printer.functionCall("o", "move", "emptyTile");
    	o.move(newTile);
    	printer.returnFromFunctionCall();
    }

    // Controller steps an unchained panda randomly
    static void test13()
    {

    }

    // Panda breaks BrokenTile with its move and falls
    static void test14()
    {
        Panda p1 = new Panda();
        BrokenTile bt = new BrokenTile(1);
        Tile t1 = new Tile();

        p1.setIsOn(t1);
        t1.setContains(p1);

        p1.move(bt);

    }

    // Orangutan steps with two Pandas
    static void test15()
    {
    	Orangutan or = new Orangutan();
    	Panda p1 = new Panda();
    	Panda p2 = new Panda();
    	Tile t1 = new Tile();
    	Tile t2 = new Tile();
    	Tile t3 = new Tile();
    	Tile t4 = new Tile();
    	
    	or.setIsOn(t3);
    	or.setNextAnimal(p1);
    	
    	p1.setIsOn(t2);
    	p1.setPrevAnimal(or);
    	p1.setNextAnimal(p2);
    	
    	p2.setIsOn(t1);
    	p2.setNextAnimal(null);
    	p2.setPrevAnimal(p1);
    	
    	t1.setContains(p2);
    	t2.setContains(p1);
    	t3.setContains(or);
    	
    	or.move(t4);
    	
    	
    }

    // Orangutan steps to Wardrobe with Panda chain
    static void test16()
    {
    	Orangutan or = new Orangutan();
    	Panda p1 = new Panda();

    	Wardrobe in_wd = null;
    	Wardrobe out_wd = new Wardrobe(in_wd);
    	in_wd = new Wardrobe(out_wd);

    	Tile t1 = new Tile();
    	Tile t2 = new Tile();
    	Tile t3 = new Tile();
    	Tile t4 = new Tile();
    	Tile t5 = new Tile();
    	Tile t6 = new Tile();
    	
    	or.setIsOn(t1);
    	or.setNextAnimal(p1);
    	
    	p1.setIsOn(t2);
    	p1.setPrevAnimal(or);
    	
    	in_wd.setIsOn(t3);
    	
    	out_wd.setIsOn(t4);

    	t1.setContains(or);
    	t2.setContains(p1);
    	t3.setContains(in_wd);
    	t4.setContains(out_wd);
    	t4.addNeighbor(t5);
    	
    	or.move(t3);
    	or.move(t6);

    }
}

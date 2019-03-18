/* Call Tree kiírását végző singleton osztály.
 *
 *  Így használd:
 *
 *  Printer p = Printer.getInstance();                      // "példányosítás", ez akár lehet globális változó minden classban
 *  vagy simán
 *  Main.printer.functionCall(...) stb			            // De inkább: legyen az egyetlen példánya a Main classban globális mező és használjuk azt.
 *
 *  Main.printer.functionCall(aminHivjukPeldanyanakNeve, fuggvenyNeve);                                 // paraméter nélküli függvény felvétele a fába, a függvényhívás előtti sorba tedd
 *  Main.printer.functionCall(aminHivjukPeldanyanakNeve, fuggvenyNeve, ParameterNeve/KonkretErtek);     // egyparaméteres függvény felvétele, a függvényhívás előtti sorba tedd
 *  Main.printer.returnFromFunctionCall();                                                              // visszatérés a függvényből, a függvényhívás utáni sorba tedd
 *
 *
 */

public class Printer
{
    private static Printer instance = null;
    private int depth = 0;          // A szöveget megelőző tabulátorok száma kiíráskor

    private Printer()
    {

    }

    public static Printer getInstance()
    {
        if(instance == null)
        {
            instance = new Printer();
        }

        return instance;
    }

    public void functionCall(String name, String function)
    {
        for(int i = 0; i < depth; i++)
        {
            System.out.print("\t");
        }

        depth++;
        System.out.println(name + "." + function + "()");
    }

    public void functionCall(String name, String function, String paramName)
    {
        for(int i = 0; i < depth; i++)
        {
            System.out.print("\t");
        }

        depth++;
        System.out.println(name + "." + function + "(" + paramName + ")");
    }

    public void returnFromFunctionCall()
    {
        if(depth != 0)
        {
            depth--;
        }

        for(int i = 0; i < depth; i++)
        {
            System.out.print("\t");
        }

        System.out.println("return");
    }
}
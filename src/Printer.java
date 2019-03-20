public class Printer
{
	private static Printer instance = null;
	private int depth = 0;

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

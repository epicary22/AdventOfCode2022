import java.util.*;

public class Monkey {
	public final int index;
	public ArrayList<Integer> worryValuesHeld = new ArrayList<>();
	public final char operator;
	public final String secondOperand;
	public final int divisibleTest;
	public final int throwToIfTrue;
	public final int throwToIfFalse;
	public int numItemsInspected;
	public Monkey(ArrayList<String> params)
	{
		this.index = '0' - params.get(0).split(" ")[1].charAt(0);
		for (String w : params.get(1).split(": ")[1].split(", "))
			worryValuesHeld.add(Integer.parseInt(w));
		this.operator = params.get(2).split(": ")[1].split(" ")[3].charAt(0);
		this.secondOperand = params.get(2).split(": ")[1].split(" ")[4];
		this.divisibleTest = Integer.parseInt(params.get(3).split(": ")[1].split(" ")[2]);
		this.throwToIfTrue = Integer.parseInt(params.get(4).split(": ")[1].split(" ")[3]);
		this.throwToIfFalse = Integer.parseInt(params.get(5).split(": ")[1].split(" ")[3]);
		this.printProperties();
	}

	public void printProperties()
	{
		System.out.print("Worry values held: ");
		this.worryValuesHeld.forEach(s -> System.out.print(s + ", "));
		System.out.println("\b\b.");
		System.out.println("Items inspected: " + this.numItemsInspected);
		System.out.println(
				"Index " + this.index + ", operator " + this.operator + ", second operand " + this.secondOperand + ", divisible test " + this.divisibleTest + ", throw to  " + this.throwToIfTrue + " if true, throw to " + throwToIfFalse + " if false."
		);
	}
}

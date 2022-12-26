import java.util.*;
import java.math.*;

public class Monkey {
	private static final ArrayList<Monkey> monkeysList = new ArrayList<>();
	private final int index;
	private final ArrayList<BigInteger> worryValuesHeld = new ArrayList<>();
	private final char operator;
	private final String secondOperandString;
	private final int divisibleTest;
	private final int throwToIfTrue;
	private final int throwToIfFalse;
	private Integer numItemsInspected = 0;
	private final int solutionNum;
	public Monkey(ArrayList<String> params, int solutionNum)
	{
		this.index = params.get(0).split(" ")[1].charAt(0) - '0';
		for (String w : params.get(1).split(": ")[1].split(", "))
			worryValuesHeld.add(new BigInteger(w));
		this.operator = params.get(2).split(": ")[1].split(" ")[3].charAt(0);
		this.secondOperandString = params.get(2).split(": ")[1].split(" ")[4];
		this.divisibleTest = Integer.parseInt(params.get(3).split(": ")[1].split(" ")[2]);
		this.throwToIfTrue = Integer.parseInt(params.get(4).split(": ")[1].split(" ")[3]);
		this.throwToIfFalse = Integer.parseInt(params.get(5).split(": ")[1].split(" ")[3]);
		this.solutionNum = solutionNum;
	}

	public void printProperties()
	{
		System.out.println("Monkey " + this.index + ":");
		System.out.print("Worry values held: ");
		this.worryValuesHeld.forEach(s -> System.out.print(s + ", "));
		System.out.println("\b\b.");
		System.out.println("Items inspected: " + this.numItemsInspected + ".");
		System.out.println(
				"Operator " + this.operator + ", second operand " + this.secondOperandString + ", divisible test " + this.divisibleTest + ", throw to " + this.throwToIfTrue + " if true, throw to " + throwToIfFalse + " if false.\n"
		);
	}

	public void inspectAllWorryValues()
	{
		final int numWorryValues = this.worryValuesHeld.size();
		for (int i = 0; i < numWorryValues; i++)
		{
			this.inspectNextWorryValue();
		}
	}

	public void inspectNextWorryValue()
	{
		BigInteger startWorryValue, secondOperand, finalWorryValue;
		int finalWorryValueInt;

		this.numItemsInspected++;

		if (!this.worryValuesHeld.isEmpty())
		{
			startWorryValue = this.worryValuesHeld.get(0);

			if (this.secondOperandString.equals("old"))
				secondOperand = startWorryValue;
			else
				secondOperand = BigInteger.valueOf(Integer.parseInt(this.secondOperandString));

			if (this.operator == '+')
				finalWorryValue = startWorryValue.add(secondOperand);
			else
				finalWorryValue = startWorryValue.multiply(secondOperand);

//			if (solutionNum == 1)
//			{
//				finalWorryValueInt = Integer.parseInt(finalWorryValue.toString()) / 3;
//			}

			this.throwWorryValue(finalWorryValue, (finalWorryValue.mod(BigInteger.valueOf(this.divisibleTest)).equals(BigInteger.valueOf(0))));
		}
	}

	private void throwWorryValue(BigInteger worryValue, boolean testPassed)
	{
		this.worryValuesHeld.remove(0);
		if (testPassed)
			monkeysList.get(this.throwToIfTrue).receiveWorryValue(worryValue);
		else
			monkeysList.get(this.throwToIfFalse).receiveWorryValue(worryValue);
	}

	public void receiveWorryValue(BigInteger worryValue)
	{
		this.worryValuesHeld.add(worryValue);
	}

	public static ArrayList<Monkey> getMonkeysList()
	{
		return monkeysList;
	}

	public Integer getNumItemsInspected()
	{
		return this.numItemsInspected;
	}
}

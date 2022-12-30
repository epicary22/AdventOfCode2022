import java.util.*;

public class Monkey {
	private static final ArrayList<Monkey> monkeysList = new ArrayList<>();
	private final int monkeyIndex;
	private final ArrayList<Integer> heldItemIndices = new ArrayList<>();
	private final char operator;
	private final String secondOperandString;
	private final int divisibleTest;
	private final int throwToIfTrueIndex;
	private final int throwToIfFalseIndex;
	private Integer numItemsInspected = 0;
	private static final ArrayList<Integer> initialItemValues = new ArrayList<>();
	private final ArrayList<Integer> heldItemTestValues = new ArrayList<>();
	private final ArrayList<Integer> allItemTestValues = new ArrayList<>();

	public Monkey(ArrayList<String> params)
	{
		this.monkeyIndex = params.get(0).split(" ")[1].charAt(0) - '0';
		for (String w : params.get(1).split(": ")[1].split(", "))
		{
			this.heldItemIndices.add(initialItemValues.size());
			initialItemValues.add(Integer.parseInt(w));
		}
		this.operator = params.get(2).split(": ")[1].split(" ")[3].charAt(0);
		this.secondOperandString = params.get(2).split(": ")[1].split(" ")[4];
		this.divisibleTest = Integer.parseInt(params.get(3).split(": ")[1].split(" ")[2]);
		this.throwToIfTrueIndex = Integer.parseInt(params.get(4).split(": ")[1].split(" ")[3]);
		this.throwToIfFalseIndex = Integer.parseInt(params.get(5).split(": ")[1].split(" ")[3]);

	}

	public void initializeItemValues()
	{
		ArrayList<Integer> initialHeldItemValues = new ArrayList<>();
		this.heldItemIndices.forEach(i -> initialHeldItemValues.add(initialItemValues.get(i)));
		for (Integer value : initialHeldItemValues)
			this.heldItemTestValues.add(value % this.divisibleTest);

		for (Integer value : initialItemValues)
			this.allItemTestValues.add(value % this.divisibleTest);
	}

	public void refreshItemTestValues()
	{
		this.allItemTestValues.replaceAll(value -> value % this.divisibleTest);
		this.heldItemTestValues.clear();
		this.heldItemIndices.forEach(i -> this.heldItemTestValues.add(this.allItemTestValues.get(i)));
	}

	public void printHeldItems()
	{
		System.out.print("Monkey " + this.monkeyIndex + ": Held: ");
		this.heldItemTestValues.forEach(v -> System.out.print(v + ", "));
		System.out.print("\b\b. All: ");
		this.allItemTestValues.forEach(v -> System.out.print(v + ", "));
		System.out.println("\b\b.\n");
	}

	public void printProperties()
	{
		System.out.println("Monkey " + this.monkeyIndex + ":");
		System.out.println("Items inspected: " + this.numItemsInspected + ".");
		System.out.println(
				"Operator " + this.operator + ", second operand " + this.secondOperandString + ", divisible test " + this.divisibleTest + ", throw to " + this.throwToIfTrueIndex + " if true, throw to " + throwToIfFalseIndex + " if false."
		);
		this.printHeldItems();
	}

	public void inspectAllItems()
	{
		for (int itemIndex : this.heldItemIndices)
		{
			runOperation(itemIndex);
			if (this.allItemTestValues.get(itemIndex) == 0)
				this.throwIndexTo(this.throwToIfTrueIndex, itemIndex);
			else
				this.throwIndexTo(this.throwToIfFalseIndex, itemIndex);
			this.numItemsInspected++;
		}
		this.heldItemIndices.clear();
		this.refreshItemTestValues();
	}

	public void runOperation(int itemIndex)
	{
		for (Monkey m : getMonkeysList())
		{
			m.operateOnIndex(itemIndex, this.operator, this.secondOperandString);
		}
	}

	private void operateOnIndex(int itemIndex, char operator, String operandString)
	{
		int operand;

		if (operandString.equals("old"))
			operand = this.allItemTestValues.get(itemIndex);
		else
			operand = Integer.parseInt(operandString);

		if (operator == '+')
			this.allItemTestValues.set(itemIndex, this.allItemTestValues.get(itemIndex) + operand);
		else
			this.allItemTestValues.set(itemIndex, this.allItemTestValues.get(itemIndex) * operand);

		this.refreshItemTestValues();
	}

	public void throwIndexTo(int monkeyIndex, int itemIndex)
	{
		monkeysList.get(monkeyIndex).receiveIndex(itemIndex);
	}

	public void receiveIndex(int itemIndex)
	{
		this.heldItemIndices.add(itemIndex);
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

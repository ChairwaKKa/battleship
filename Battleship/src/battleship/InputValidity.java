package battleship;

public class InputValidity {
	private int minValue = 0, maxValue = 0, fieldSize = 0;
	private String input = "";

	InputValidity (int min, int max, String input)
	{
		this.minValue 	= min;
		this.maxValue 	= max;
		this.input		= input;
	}

	public void setMinValue(int min)
	{
		this.minValue = min;
	}

	public int getMinValue()
	{
		return this.minValue;
	}

	public void setMaxValue(int max)
	{
		this.maxValue = max;
	}

	public int getMaxValue()
	{
		return this.maxValue;
	}

	public void setFieldSize(int size)
	{
		this.fieldSize = size;
	}

	public int getFieldSize()
	{
		return this.fieldSize;
	}

	public void setInput(String input)
	{
		this.input = input;
	}

	public String getInput()
	{
		return this.input;
	}



	public boolean validInteger()
	{
		int number =  0;

		try
		{
			number = Integer.parseInt(input);
		}
		catch (Exception e)
		{
			return false;
		}

		return ((number >= minValue) && (number <= maxValue));
	}

	public int getInteger()
	{
		return Integer.parseInt(this.input);
	}

}

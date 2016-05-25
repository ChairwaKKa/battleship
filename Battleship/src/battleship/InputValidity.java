package battleship;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class InputValidity {
	private int minValue = 0, maxValue = 0, xCoord = 0, yCoord = 0;
	private String input = "";

	InputValidity (String input)
	{
		this.input = input;
	}

	InputValidity (int min, int max, String input)
	{
		this.minValue 	= min;
		this.maxValue 	= max;
		this.input		= input;
	}


	public boolean validInputPattern( int fieldSize)
	{
		/*  Die Eingabe wird auf ein Bestimmtes Muster untersucht, welches Leerzeichen erlaubt
		 *  \p{Blank}*	- Leerzeichen				| keins oder mehrmals
		 *  \d{1,2}		- [0-9] 					| ein oder zwei mal aufeinanderfolgend
		 *  \p{Alpha}	- Groß- und Kleinbuchstaben	| einmal
		 */
		char c			= 'A';
		boolean valid	= true;
		int x 	= 0, y 	= 0;
		String error 	= "Überprüfen Sie nochmal Ihre eingabe.";

		if (Pattern.matches("\\p{Blank}*\\d{1,2}\\p{Blank}*\\p{Alpha}\\p{Blank}*", this.input))
		{
			this.input = this.input.replaceAll(" ", "").toLowerCase();	// Es werden alle Leerzeichen aus der Eingabe entfernt und alle Buchstaben werden zu Kleinbuchstaben

			if (Pattern.matches("\\d\\p{Alpha}", this.input))
			{
				c = this.input.charAt(1);
					x = Integer.parseInt(this.input.substring(0,1));
					y = ((int)c)-96;
				}
				else if (Pattern.matches("\\d\\d\\p{Alpha}", this.input))
				{
					c = this.input.charAt(2);
					x = Integer.parseInt(this.input.substring(0,2));
					y = ((int)c)-96;
				}
				else
				{
					valid = false;
				}
			}

			else if (Pattern.matches("\\p{Blank}*\\p{Alpha}\\p{Blank}*\\d{1,2}\\p{Blank}*", this.input))
			{
				this.input = this.input.replaceAll(" ", "").toLowerCase();	// Es werden alle Leerzeichen aus der Eingabe entfernt und alle Buchstaben werden zu Kleinbuchstaben

				if (Pattern.matches("\\p{Alpha}\\d\\d", this.input))
				{
					c = this.input.charAt(0);
					x = Integer.parseInt(this.input.substring(1,3));
					y = ((int)c)-96;
				}
				else if (Pattern.matches("\\p{Alpha}\\d", this.input))
				{
					c = this.input.charAt(0);
					x = Integer.parseInt(this.input.substring(1));
					y = ((int)c)-96;
				}
				else
				{
					valid = false;
				}
			}
			else
			{
				valid = false;
			}


			if (((x <= 0) || (x > fieldSize)) && ((y <= 0) || (y > fieldSize)))
			{
				valid = false;
				error = "Guck nochmal auf die Spielfeldgröße und überlege, ob die Eingabe sinnvoll war...";
			}
			else if ((x <= 0) || (x > fieldSize))
			{
				valid = false;
				error = "Die Eingabe für die Y-Koordinate war ungültig!";
			}
			else if ((y <= 0) || (y > fieldSize))
			{
				valid = false;
				error = "Die Eingabe für die X-Koordinate war ungültig!";
			}

			if (!valid)
			{
				JOptionPane.showMessageDialog(null, error);
			}
			else
			{
				this.xCoord = x;
				this.yCoord = y;
			}

			return valid;
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

	public void setX( int x)
	{
		this.xCoord = x;
	}

	public int getX()
	{
		return this.xCoord;
	}

	public void setY( int y)
	{
		this.yCoord = y;
	}

	public int getY()
	{
		return this.yCoord;
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

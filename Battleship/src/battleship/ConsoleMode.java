package battleship;
/**
 * Konsolenmodus für das Spiel
 *
 * @author oliver2
 */
import java.util.Scanner;

public class ConsoleMode  extends RunGame{

	public static Scanner scanner;

	public ConsoleMode()
	{
		super();
		ConsoleMode.scanner = new Scanner(System.in);
	}

	@Override
	protected String getUserInput(String request) {
		System.out.print(request);
		String input = ConsoleMode.scanner.nextLine();
		return input;
	}

	@Override
	protected void gameInfo(String info)
	{
		System.out.print(info);
	}



	@Override
	public void gameLoop()
	{
		int x = 0, y = 0, shootCounter = 0, numberOfTries = 0;
		do
		{
			this.oc.showOcean();
			this.gameEnd = false;

			do
			{

				this.userInput = getUserInput("Geben Sie bitte die Zielkoordinate ein!\n");

				if (this.userInput.equals("ende"))
				{
					System.exit(0);
				}
				else
				{
					this.iv.setInput(this.userInput);
				}
				this.validUserInput = iv.validInputPattern(this.fieldSize);
				x = this.iv.getX();
				y = this.iv.getY();

			} while (!validUserInput);

			// Es wird nur auf ein Zielfeld geschossen, welches noch nicht beschossen wurde

			if (!this.oc.ocean[x][y].alreadyGotHit())
			{
				this.oc.ocean[x][y].shootField();
				this.fl.checkFleet(this.oc);
				this.oc.showOcean();
				shootCounter ++;
				numberOfTries = shootCounter;
				gameInfo("Es sind nur noch " + this.fl.getNumberOfShips() + " Schiffe auf dem Feld!");

			}
			else
			{
				gameInfo("Auf dieses Feld wurde bereits geschossen.\n");

			}

			// Es wird nach dem Schuss geprüft, ob die gesamte Flotte zerstört wurde

			if (this.fl.isDefeated())
			{
				this.gameEnd = true;
				gameInfo("Sie haben gewonnen und " + numberOfTries + " Versuche benötigt!\n");
			}

		} while(!this.gameEnd);

		ConsoleMode.scanner.close();
	}



}

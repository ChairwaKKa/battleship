package battleship;

/**
 * Schablone f�r den Spielablauf
 *
 * @author oliver2
 *
 */
public abstract class RunGame {


	// Spielvariablen werden deklariert und initialisiert
	/**
	 * fieldSize		-	Spielfeldgr��e
	 * minFieldSize		-	minimale Spielfeldgr��e
	 * maxFieldSize		-	maximale Spielfeldgr��e
	 * userInput		-	Benutzereingabe
	 * validUserInput	-	G�ltigkeitswert f�r die Benutzereingabe
	 * gameEnd			-	gibt an, ob ein Spiel zuende ist
	 */

	protected int fieldSize			=  0;
	protected int minFieldSize 		=  0;
	protected int maxFieldSize 		=  0;
	protected String userInput 		= "";
	protected String request		= "";
	protected boolean validUserInput= true;
	protected boolean gameEnd		= false;
	protected InputValidity iv = new InputValidity();
	Ocean oc;
	Fleet fl;

	public RunGame()
	{
		this.minFieldSize = 5;
		this.maxFieldSize = 20;
	}

	/**
	 * Beschreibt den kompletten Spielablauf
	 */
	public void run()
	{
		setFieldSize();
		initializeGame();
		gameLoop();
	}

	/**
	 * Das Spielfeld (Ocean) und die Schiffe (Fleet) werden initialisiert
	 */
	public void initializeGame()
	{
		this.oc = new Ocean(fieldSize);
		this.fl = new Fleet(fieldSize);

		this.oc.initFieldTypes();
		gameInfo("Der Ozean wurde mit Wasser gef�llt..\n");
		this.fl.setFleet(oc);
		gameInfo("Ganze "+ fl.getNumberOfShips() +" Schiffe wurden auf dem Ozean verteilt.\nOh ha!\n");
	}

	/**
	 * Die Eingabe wird validiert und die Feldgr��e ausgegeben
	 */
	public void setFieldSize()
	{
		boolean valid = true;

		do
		{
			this.userInput = getUserInput ("Geben Sie bitte die Spielfeldgr��e an (min "+this.minFieldSize+"/ max "+maxFieldSize+ ")!\n");
			iv.setInput(userInput);
			valid = iv.validInteger(minFieldSize, maxFieldSize);
		} while (!valid);

		this.fieldSize= iv.getInteger();
	}

	/**
	 * Die Benutzereingabe wird abgerufen
	 *
	 * @param request
	 * 		Eingabeaufforderungstext
	 *
	 * @return
	 * 		Eingabe wird als String wiedergegeben
	 */
	abstract String getUserInput (String request);


	/**
	 * Schleife, in welcher der Spieler auf das Spielfeld schie�t, solange noch Schiffe auf dem Feld sind
	 */
	abstract void gameLoop();

	/**
	 * Informationsanzeige
	 *
	 * @param info
	 * 		Infotext
	 */
	abstract void gameInfo(String info);



}

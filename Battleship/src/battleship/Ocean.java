package battleship;

/**
 * Spielfeld als dualer Array aus Feld Objekten
 * --> FieldType
 *
 * @author oliver2
 *
 */

public class Ocean {

	// Lokale Variablen werden deklariert und initialisiert

	public static  int size = 0;

	// Das Spielfeld ist ein Dualer Array aus Field-Objekten

	public Field [][] ocean;

	Ocean(int fieldSize)
	{

	// Spielfeld wird um Randfelder erweitert

		Ocean.size = fieldSize +2 ;
		this.ocean = new Field [size][size];

	// Ozean wird mit Wasserfeldern und Randfeldern belegt

		for (int x = 0; x < this.ocean.length; x ++)
			for (int y = 0; y < this.ocean[x].length; y ++)
			{
				char c = 'A';
				this.ocean[x][y] = new Water();

				c = (char) (y+ 64);							// ASCII Zeichen von A (065) bis maximal  T (084)
				this.ocean[0][y].setVisual(" " + c + " ");		// Beschriftung oben

				if ( x < 10)
				{
					this.ocean[x][0].setVisual("  " + x + " ");	// Beschriftung links
				}
				else
				{
					this.ocean[x][0].setVisual(" " + x + " ");	// Beschriftung links
				}

				if (y == (Ocean.size -1))
				{
					this.ocean[x][Ocean.size -1].setVisual("   ");			// Leere Anzeige rechts
					this.ocean[0][Ocean.size -1].setVisual("   ");			// Letzte Beschriftung wird geleert
				}

				if (x == (Ocean.size -1))
				{
					this.ocean[Ocean.size -1][y].setVisual("   ");			// Leere Anzeige unten
					this.ocean[Ocean.size -1][0].setVisual("   ");			// Letzte Beschriftung wird geleert
				}


			}
		this.ocean[0][0].setVisual("    ");						// Im Ursprung 4 Leerzeichen
	}

	/**
	 * Die Feldtypen werden initialisiert
	 * --> FieldType
	 */
	public void initFieldTypes()
	{
		for (int x = 0; x < this.ocean.length; x ++)
			for (int y = 0; y < this.ocean[x].length; y ++)
			{
				this.ocean[x][y].setFieldType(FieldType.Water);
				this.ocean[0][y].setFieldType(FieldType.Border);			// Rand oben
				this.ocean[x][0].setFieldType(FieldType.Border);			// Rand links
				this.ocean[Ocean.size -1][y].setFieldType(FieldType.Border);	// Rand unten
				this.ocean[x][Ocean.size -1].setFieldType(FieldType.Border);	// Rand rechts
			}
	}


	/**
	 * Stellt den Ozean auf dem Monitor dar
	 */
	public void showOcean()
	{
		System.out.print("\n\n\n\n\n\n");
		for (int x = 0; x < this.ocean.length; x ++)
		{
			for (int y = 0; y < this.ocean[x].length; y ++)
			{
				System.out.print(this.ocean[x][y].getVisual());
			}
			System.out.print("\n");
		}
	}


}

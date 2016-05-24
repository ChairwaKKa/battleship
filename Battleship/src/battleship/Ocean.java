package battleship;

public class Ocean {

	// Lokale Variablen werden deklariert und initialisiert

	private static  int size = 0;

	// Das Spielfeld ist ein Dualer Array aus Field-Objekten

	public Field [][] ocean;

	Ocean(int fieldSize)
	{

	// Spielfeld wird um Randfelder erweitert

		this.size = fieldSize +2 ;
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

				if (y == (this.size -1))
				{
					this.ocean[x][size -1].setVisual("   ");			// Leere Anzeige rechts
					this.ocean[0][size -1].setVisual("   ");			// Letzte Beschriftung wird geleert
				}

				if (x == (this.size -1))
				{
					this.ocean[size -1][y].setVisual("   ");			// Leere Anzeige unten
					this.ocean[size -1][0].setVisual("   ");			// Letzte Beschriftung wird geleert
				}


			}
		this.ocean[0][0].setVisual("    ");						// Im Ursprung 4 Leerzeichen
	}

	public void initFieldTypes()
	{
		for (int x = 0; x < this.ocean.length; x ++)
			for (int y = 0; y < this.ocean[x].length; y ++)
			{
				this.ocean[x][y].setFieldType(FieldType.Water);
				this.ocean[0][y].setFieldType(FieldType.Border);			// Rand oben
				this.ocean[x][0].setFieldType(FieldType.Border);			// Rand links
				this.ocean[size -1][y].setFieldType(FieldType.Border);	// Rand unten
				this.ocean[x][size -1].setFieldType(FieldType.Border);	// Rand rechts
			}
	}

// Zeigt den Ozean als Konsolenausgabe an

	public void showOcean()
	{
		System.out.print("\n\n\n\n\n\n");
		for (int x = 0; x < this.ocean.length; x ++)
		{
			for (int y = 0; y < this.ocean[x].length; y ++)
			{
				System.out.print(this.ocean[x][y].getVisual());
				//System.out.print(" " + ocean[x][y].fieldID + " ");
			}
			System.out.print("\n");
		}
	}

	// Gibt die Größe des Ozeans an

	static int getFieldSize()
	{
		return size;
	}
}

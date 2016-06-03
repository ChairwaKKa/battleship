package battleship;

/**
 *
 * @author oliver2
 *
 */

abstract class Field {


		protected boolean gotHit = false;
		protected int x, y , width =GameLogic.width, height =GameLogic.height;
		protected int fieldID;
		protected FieldType ft = FieldType.Null;
		protected String visual = GameLogic.water;


		/**
		 * Gibt an, ob ein Feld bereits getrofen wurde
		 * @return
		 * 		Wahrheitswert, ob getroffen
		 */
		public boolean alreadyGotHit(){return gotHit;}

		/**
		 * Das Feld wird getroffen
		 */
		public void shootField(){this.gotHit = true;}

		/**
		 * Ruft das Symbol ab für die Konsolendarstellung
		 * @return
		 * 		Symboltext
		 */
		public String getVisual(){return this.visual;}

		/**
		 * Legt ein neues Symbol für das Feld fest
		 * @param visual
		 * 		Neuer Symboltext
		 */
		public void setVisual(String visual){this.visual = visual;}

		/**
		 * Setzt die X-Feldkoordinate
		 * @param x
		 * 		Ankerpunkt auf der X - Achse
		 */
		public void setX(int x){this.x = x;}

		/**
		 * Setzt die Y-FeldKoordinate
		 * @param y
		 * 		Ankerpunkt auf der Y - Achse
		 */
		public void setY(int y){this.y = y;}

		/**
		 * Fragt die X-Koordinate ab
		 * @return
		 * 		Ankerpunkt auf der X-Achse
		 */
		public int getX(){return this.x;}

		/**
		 * Fragt die Y-Koordinate ab
		 * @return
		 * 		Ankerpunkt auf der Y-Achse
		 */
		public int getY(){return this.y;}

		/**
		 * Fragt den Feldtypen ab --> FieldType
		 * @return
		 * 		Feldtyp
		 */
		public FieldType getFieldType(){return this.ft;}

		/**
		 * Setzt für das Feld einen neuen Feldtypen --> FieldType
		 * @param ft
		 * 		Feldtyp
		 */
		public void setFieldType (FieldType ft){this.ft = ft;}

		/**
		 * Fragt die ID vom Feld ab
		 * @return
		 * 		Feld- ID
		 */
		public int getFieldID(){return this.fieldID;}

		/**
		 * Legt für das Feld eine neue ID fest
		 * @param id
		 * 		Feld- ID
		 */
		public void setFieldID(int id){this.fieldID = id;}








}

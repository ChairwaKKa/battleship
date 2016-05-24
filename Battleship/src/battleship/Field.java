package battleship;


abstract class Field {


		protected boolean gotHit = false;
		protected int x, y , width =GameLogic.width, height =GameLogic.height;
		protected int fieldID;
		protected FieldType ft = FieldType.Null;
		protected String visual = GameLogic.water;



		public boolean alreadyGotHit(){return gotHit;}
		public void shootField(){this.gotHit = true;}
		public String getVisual(){return this.visual;}
		public void setVisual(String visual){this.visual = visual;}
		public void setX(int x){this.x = x;}
		public void setY(int y){this.y = y;}
		public int getX(){return this.x;}
		public int getY(){return this.y;}
		public FieldType getFieldType(){return this.ft;}
		public void setFieldType (FieldType ft){this.ft = ft;}
		public int getFieldID(){return this.fieldID;}
		public void setFieldID(int id){this.fieldID = id;}








}

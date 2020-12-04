package ressource;

public class Position {
	private int posX;
	private int posY;
	
	/**
	 * Constructeur de la position
	 * @Modification Xavier Jacob--Guizon
	 * @param px position x de la future position � cr�er
	 * @param py position y de la future position � cr�er
	 */
	public Position(int px, int py) {
		this.posX=px;
		this.posY=py;
	}
	
	/**
	 * Constructeur de la position � partir d'une autre position
	 * @Modification Xavier Jacob--Guizon
	 * @param p Position
	 */
	public Position(Position p) {
		this.posX=p.getPosX();
		this.posY=p.getPosY();
	}
	
	/**
	 * Getteur de la position X
	 * @return int
	 */
	public int getPosX() {
		return this.posX;
	}
	
	/**
	 * Setter de la position X
	 * @param int px
	 */
	public void setPosX(int px) {
		this.posX=px;
	}
	
	/**
	 * Getteur de la position Y
	 * @return int
	 */
	public int getPosY() {
		return this.posY;
	}
	
	/**
	 * Setter de la position Y
	 * @param int py
	 */
	public void setPosY(int py) {
		this.posY=py;
	}
	
	/**
	 * le to string de la position;
	 * @Modification Xavier Jacob--Guizon
	 */
	public String toString() {
		return "["+this.posX+" : "+this.posY+"]";
	}

	public boolean estCase(Position pos) {
		return this.posX==pos.getPosX() && this.posY==pos.getPosY();
	}
}

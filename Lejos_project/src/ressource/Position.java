package ressource;

public class Position {
	private int posX;
	private int posY;
	
	/**
	 * Constructeur de la position
	 * @Modification Xavier Jacob--Guizon
	 * @param px position x de la future position à créer
	 * @param py position y de la future position à créer
	 */
	public Position(int px, int py) {
		this.posX=px;
		this.posY=py;
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
	public int setPosX(int px) {
		return this.posX=px;
	}
	
	/**
	 * Setter de la position Y
	 * @param int py
	 */
	public int setPosY(int py) {
		return this.posY=py;
	}
	
	/**
	 * le to string de la position;
	 * @Modification Xavier Jacob--Guizon
	 */
	public String toString() {
		return "position en x : "+this.posX+" : position en y :"+posY;
	}
}

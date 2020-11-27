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
		return "position en x : "+this.posX+" : position en y :"+posY;
	}

	public boolean estCase(Position pos) {
		if(this.posX==pos.getPosX() && this.posY==pos.getPosY()) {
			return true;
		}else {
			return false;
		}
	}
}

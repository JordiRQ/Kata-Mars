package tangelo.kata.mars.rover.domain;

public class Coordinates {
	
	private int x;
	private int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isEqualToComparingFieldByField(Coordinates other){
		return x==other.getX() && y==other.getY();
	}
	
	@Override
	public String toString(){
		String output = this.x + " X " + this.y;
		return output;
	}
	
	public void moveX(int i){
		x = x + i;
	}
	
	public void moveY(int i){
		y = y + i;
	}
	
}

/**
 * The purpose of this class is to represent the location of a pel object
 * 
 * @author Kevin Chau #251215166 Date: 2022-11-19
 */
public class Location {
	// Declare Private Variables
	private int x;
	private int y;

	/**
	 * Constructor class that initializes this Location object with the specified
	 * coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter Method that returns the x value of this Location
	 * 
	 * @return x
	 */
	public int getx() {
		return this.x;
	}

	/**
	 * Getter Method that returns the y value of this Location
	 * 
	 * @return
	 */
	public int gety() {
		return this.y;
	}

	/**
	 * Compares the location of this object and another object
	 * 
	 * @param p
	 * @return 1 if this object is greater than p
	 * @return 0 if this object is equal to p
	 * @return -1 otherwise
	 */
	public int compareTo(Location p) {
		if ((this.gety() > p.gety()) || (this.gety() == p.gety() && this.getx() > p.getx())) { // If greater
			return 1;
		} else if (this.getx() == p.getx() && this.gety() == p.gety()) { // If equal
			return 0;
		} else { // If less
			return -1;
		}
	}
}

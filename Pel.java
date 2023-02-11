/**
 * The purpose of this class is to represent the data items to be stored in the
 * nodes of BinarySearchTree
 * 
 * @author Kevin Chau #251215166 Date: 2022-11-19
 */
public class Pel {
	// Declare private variables
	private Location p;
	private int color;

	/**
	 * Constructor which initializes the new Pel with the specified coordinates and
	 * colour.
	 * 
	 * @param p
	 * @param color
	 */
	public Pel(Location p, int color) {
		this.p = p;
		this.color = color;
	}

	/**
	 * Getter method that returns the Location of this Pel
	 * 
	 * @return
	 */
	public Location getLocus() {
		return p;
	}

	/**
	 * Getter method that returns the colour of this Pel
	 * 
	 * @return
	 */
	public int getColor() {
		return color;
	}
}

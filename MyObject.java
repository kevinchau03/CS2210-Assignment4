/**
 * This class represents the object MyObject
 * 
 * @author Kevin Chau #251215166 Date: 2022-11-19
 */
public class MyObject implements MyObjectADT {
	private int id, width, height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;

	/**
	 * This constructor method intializes all the specific values for MyObject
	 * 
	 * @param id
	 * @param width
	 * @param height
	 * @param type
	 * @param pos
	 */
	public MyObject(int id, int width, int height, String type, Location pos) {
		tree = new BinarySearchTree();
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
	}

	/**
	 * Getter method that returns the width of enclosed MyObject Rectangle;
	 */
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	/**
	 * Getter method that returns the height of enclosed MyObject Rectangle
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	/**
	 * Getter method that returns the type of MyObject
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	/**
	 * Getter method that returns the type of Id of MyObject
	 */
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * Getter method that returns the locus of this MyObject
	 */
	@Override
	public Location getLocus() {
		// TODO Auto-generated method stub
		return this.pos;
	}

	/**
	 * Setter method that changes the locus of this MyObject to the specified value
	 */
	@Override
	public void setLocus(Location value) {
		// TODO Auto-generated method stub
		this.pos = value;
	}

	/**
	 * This method sets the type of MyObject to a certain type
	 * 
	 * @param t
	 */
	@Override
	public void setType(String t) {
		// TODO Auto-generated method stub
		this.type = t;
	}

	/**
	 * This method inserts pix into the binary search tree associated with this
	 * MyObject
	 * 
	 * @param pix
	 */
	@Override
	public void addPel(Pel pix) throws DuplicatedKeyException {
		// TODO Auto-generated method stub
		// Try to insert pix into the tree and if there is one that exists catch
		// exception
		try {
			tree.put(tree.getRoot(), pix);
		} catch (Exception e) {
			throw new DuplicatedKeyException("Already in tree");
		}
	}

	/**
	 * This method determines if this MyObject intersects with the one specified in
	 * the parameter
	 * 
	 * @param MyObject fig
	 * @return True if it intersects
	 * @return False if it does not
	 */
	@Override
	public boolean intersects(MyObject fig) {
		// TODO Auto-generated method stub
		// Check to see if there is a overlap of the rectangles
		if (checkRectangles(fig)) {
			try {
				Pel small = tree.smallest(tree.getRoot());

				// Check that f and f' have pels that would be displayed at precisely the same
				// position
				while (small != null) { // iterate through each data item in the BST (in order)
					int x, y;
					x = small.getLocus().getx() + this.getLocus().getx() - fig.getLocus().getx(); //
					y = small.getLocus().gety() + this.getLocus().gety() - fig.getLocus().gety();
					Location newLocation = new Location(x, y);

					if (fig.findPel(newLocation)) { // If there is a data item in the root tree with key x' and y' then
													// the objcets overlap
						return true;
					}
					small = tree.successor(tree.getRoot(), small.getLocus()); // Get next
				}
			} catch (EmptyTreeException e) { // Catch
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * This method checks the locus of both rectangles and determines if they
	 * intersect
	 * 
	 * @param fig
	 * @return true if they intersect
	 * @return false if they do not intersect
	 */
	private boolean checkRectangles(MyObject fig) {
		// Check to make sure that this MyObject is higher or lower than the fig
		// MyObject
		if ((pos.gety() > fig.getLocus().gety() + fig.getHeight()) || (pos.gety() + height < fig.getLocus().gety())) {
			return false;
			// Check to make sure that that the objects do not intersect on the x axis
			// (further left or further right)
		} else if ((pos.getx() > fig.getLocus().getx() + fig.getWidth())
				|| (pos.getx() + width < fig.getLocus().getx())) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * This method is used to determine if this MyObject has a Pel object in
	 * location p
	 * 
	 * @param p
	 * @return true there is a pel in MyObject
	 * @return false if there is no pel in MyObject
	 */
	private boolean findPel(Location p) {
		Pel pel = tree.get(tree.getRoot(), p);
		if (pel != null) {
			return true;
		} else {
			return false;
		}
	}
}

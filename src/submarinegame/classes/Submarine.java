package submarinegame.classes;

import java.awt.Point;
import java.io.Serializable;

public class Submarine implements Serializable{

	private static final long serialVersionUID = 7082466842140359248L;
	private Point[] locations;

	public Submarine(int length) {
		locations = new Point[length];
	}

	public void setLocations(Point[] locations) {
		this.locations = locations;
	}

	public Point[] getLocations() {
		return locations;
	}
}

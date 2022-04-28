package submarinegame.classes;

import java.awt.Point;

public class Submarine {
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

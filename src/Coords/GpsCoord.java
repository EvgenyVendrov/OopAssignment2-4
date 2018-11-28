package Coords;

import java.util.InvalidPropertiesFormatException;

import Geom.Geom_element;
import Geom.Point3D;

public class GpsCoord implements Geom_element {
	/**
	 * this class represents a GPS coordinate
	 */
	// MyCoords instance used to "get" the methods from the MyCoords class
	private MyCoords convertMethods;
	// Point3D instance that represents the internal coordinates of the GPS
	// coordinate
	private Point3D internalPoint;

	/**
	 * GPS coordinate constructor
	 * 
	 * @param lat latitude of this certain GPS point in a decimal degree
	 *            representation
	 * @param lon longitude of this certain GPS point in a decimal degree
	 *            representation
	 * @param alt altitude of this certain GPS point in a decimal meters
	 *            representation
	 * @throws InvalidPropertiesFormatException in case the Point3D created doesn't
	 *                                          represent a valid GPS coordinate
	 */
	public GpsCoord(double lat, double lon, double alt) throws InvalidPropertiesFormatException {
		this.convertMethods = new MyCoords();
		this.internalPoint = new Point3D(lat, lon, alt);
		if (!convertMethods.isValid_GPS_Point(internalPoint)) {
			throw new InvalidPropertiesFormatException(
					"the point: " + internalPoint + " dose not represent a valid GPS coord");
		}
	}
/**
 * 
 * @param inputToCopy
 * @throws InvalidPropertiesFormatException
 */
	public GpsCoord(Point3D inputToCopy) throws InvalidPropertiesFormatException {
		this.convertMethods=new MyCoords();
		if (convertMethods.isValid_GPS_Point(inputToCopy)) {
			this.internalPoint = new Point3D(inputToCopy);
		} else
			throw new InvalidPropertiesFormatException(
					"the point: " + inputToCopy + " dose not represent a valid GPS coord");
	}

	/**
	 * 
	 * @param inputCoord
	 * @param meterVector
	 * @return
	 */
	public Point3D add(Point3D meterVector) {
		return (convertMethods.add(this.getInternalPoint(), meterVector));
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "lat: " + this.internalPoint.x() + ", lon: " + this.internalPoint.y() + ", alt: "
				+ this.internalPoint.z();
	}

	/**
	 * calculates the 3D distance between two GPS coordinates
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 3D distance in meters
	 */
	public double distance3D(GpsCoord inPutCoord) {
		return (convertMethods.distance3d(this.internalPoint, inPutCoord.internalPoint));
	}

	/**
	 * calculates the 2D distance between two GPS coords
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 2D distance in meters
	 */
	public double distance2D(GpsCoord inPutCoord) {
		return convertMethods.distance2d(this.internalPoint, inPutCoord.internalPoint);
	}

	/**
	 * calculates the difference 3D vector between two points
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 3D vector which values are the difference between both GPS
	 *         coordinates in meters
	 */
	public Point3D vector3D(Point3D inPutMeterVector) {
		return convertMethods.vector3D(this.internalPoint, inPutMeterVector);
	}

	/**
	 * calculates the azimuth, elevation and distance between two GPS coordinates
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return a double array which has 3 elements as [0] is azimuth in decimal
	 *         degrees, [1] is elevation in decimal degrees and [2] is 3D distance
	 *         in meters
	 */
	public double[] azimuth_elevation_dist(GpsCoord inPutCoord) {
		return convertMethods.azimuth_elevation_dist(this.internalPoint, inPutCoord.internalPoint);
	}

	@Override
	public double distance3D(Point3D p) {
		return distance3D(p);
	}

	@Override
	public double distance2D(Point3D p) {
		return distance2D(p);
	}

	// getters:

	public Point3D getInternalPoint() {
		return internalPoint;
	}

	public double getLat() {
		return this.internalPoint.x();
	}

	public double getLon() {
		return this.internalPoint.y();
	}

	public double getAlt() {
		return this.internalPoint.z();
	}

}
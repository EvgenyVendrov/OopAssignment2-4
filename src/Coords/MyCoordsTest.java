package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {
	private static MyCoords tester;

	/**
	 * initializing the MyCoord instance for all tests
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tester = new MyCoords();
	}

	@Test
	void testAdd() {
		
	}

	@Test
	void testDistance3d() {
		double dev; // allowed deviation will be 1% of the result we're supposed to get
		double expected;
		double diff3D;
		// test close points
		Point3D gps1 = new Point3D(32.063607, 34.835309, 670);
		Point3D gps2 = new Point3D(32.062896, 34.840948, 7000);
		double dist = tester.distance3d(gps1, gps2); // should be 538+- according to google earth
		diff3D =Math.pow( gps2.z()-gps1.z(), 2);
		expected = 538.0;
		expected= Math.pow(expected, 2);
		expected+=diff3D;
		expected=Math.sqrt(expected);
		dev = expected * 0.01;
		if (dist > dev + expected || dist < expected - dev)
			fail("Distance function failed");
		// test far points
		gps1 = new Point3D(32.063607, 34.835309, 670);
		gps2 = new Point3D(31.990175, 34.800077, -345);
		dist = tester.distance3d(gps1, gps2);
		diff3D =Math.sqrt(Math.pow( gps2.z()-gps1.z(), 2));
		expected = 8796.0;
		expected+=diff3D;
		dev = expected * 0.01;
		if (dist > dev + expected || dist < expected - dev)
			fail("Distance function failed");
	}

	@Test
	void testDistance2d() {
		double dev; // allowed deviation will be 1% of the result we're supposed to get
		double expected;
		// test close points
		Point3D gps1 = new Point3D(32.063607, 34.835309, 670);
		Point3D gps2 = new Point3D(32.062896, 34.840948, 7000);
		double dist = tester.distance2d(gps1, gps2); // should be 538+- according to google earth
		expected = 538.0;
		dev = expected * 0.01;
		if (dist > dev + expected || dist < expected - dev)
			fail("Distance function failed");
		// test far points
		gps1 = new Point3D(32.063607, 34.835309, 670);
		gps2 = new Point3D(31.990175, 34.800077, -345);
		dist = tester.distance2d(gps1, gps2);
		expected = 8796.0;
		dev = expected * 0.01;
		if (dist > dev + expected || dist < expected - dev)
			fail("Distance function failed");
	}

//	@Test
//	void testVector3D() {
//		Point3D firstCoord = new Point3D(54.567, -56.78, 30);
//		Point3D secondCoord = new Point3D(53, -55.5, 20);
//		Point3D excpected = new Point3D(x, y, z)
//		Point3D check = tester.vector3D(firstCoord, secondCoord);
//	}

	@Test
	void testAzimuth_elevation_dist() {
		// distance was already tested before so we'll skip that
		//
		Point3D centre = new Point3D(32.098729, 35.207258, 670);// the centre point
		//testing elevation
		// 3 points distant 500+-eps meter away from centre
		// the elevation difference should be 24.70243023, allowed deviation will be 0.1
		// because the distances are exactly 500M
		// because the distance and the elevation is the same for all points it should
		// give the same
		// result +-eps in arr[1] representing the elevation difference in degrees
		double eps = 0.1;
		double expected = 24.70243023;// tan^-1(230/500)
		Point3D gps1 = new Point3D(32.102285, 35.210515, 900);
		Point3D gps2 = new Point3D(32.098921, 35.201961, 900);
		Point3D gps3 = new Point3D(32.094311, 35.206260, 900);
		double arr1[] = tester.azimuth_elevation_dist(centre, gps1);
		double arr2[] = tester.azimuth_elevation_dist(centre, gps2);
		double arr3[] = tester.azimuth_elevation_dist(centre, gps3);
		if (arr1[1] > expected + eps || arr1[1] < expected - eps)
			fail("Wrong value for elevation difference");
		if (arr2[1] > expected + eps || arr2[1] < expected - eps)
			fail("Wrong value for elevation difference");
		if (arr3[1] > expected + eps || arr3[1] < expected - eps)
			fail("Wrong value for elevation difference");

		// now testing azimuth
		gps1 = new Point3D(10, 50, 900);
		gps2 = new Point3D(10.1, 50, 900);
		// should be zero since longitude didn't change, we only moved to the north
		arr1 = tester.azimuth_elevation_dist(gps1, gps2);
		if (arr1[0] != 0)
			fail("Wrong value for azimuth");
		gps1 = new Point3D(10.1, 50, 900);
		gps2 = new Point3D(10, 50, 900);
		// should be 180 since we moved only to the south
		arr1 = tester.azimuth_elevation_dist(gps1, gps2);
		if (arr1[0] != 180)
			fail("Wrong value for azimuth");
		gps1 = new Point3D(10, 50, 900);
		gps2 = new Point3D(10, 50.000000001, 900);
		// should be 90 because we only moves to the east
		arr1 = tester.azimuth_elevation_dist(gps1, gps2);
		if (arr1[0] != 90)
			fail("Wrong value for azimuth");
		gps1 = new Point3D(10, 50.000000001, 900);
		gps2 = new Point3D(10, 50, 900);
		// should be 270 since we only moved to the west
		arr1 = tester.azimuth_elevation_dist(gps1, gps2);
		if (arr1[0] != 270)
			fail("Wrong value for azimuth");
	}

	@Test
	void testIsValid_GPS_Point() {
		// fail("Not yet implemented");
	}

}

package GIS;

import java.io.File;
import java.io.FileNotFoundException;

import File_format.Csv2Kml;

public class testing_Main {

	public static void main(String[] args) {
		String pathKML = "C:" + File.separator + "Users" + File.separator + "evgen" + File.separator
				+ "eclipse-workspace" + File.separator + "OopAssignment2-4" + File.separator + "kmlFilesOutPut"
				+ File.separator + "kml2.kml";
		String pathCSV = "/csvFilesTest/WigleWifi_20171201110209.csv";
		try {
			Csv2Kml.csv2kml(pathCSV, pathKML);
		} catch (FileNotFoundException e) {
			System.out.println("ERR in CSV2KML, ERR: " + e.getMessage());
		}
	}

}

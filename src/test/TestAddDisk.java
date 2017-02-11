package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.catalog.dao.exception.DaoException;

import com.epam.catalog.dao.impl.DiskDaoImpl;

public class TestAddDisk {
	@Test(dataProvider = "dp")

	public void f(String message) {
		Path datafile = Paths.get("data/units.txt").toAbsolutePath();
		DiskDaoImpl disk = new DiskDaoImpl();

		try {
		//	disk.addDisk(disk);

			String data = new String(Files.readAllBytes(datafile));
			System.out.println(data);
			Assert.assertTrue(data.contains(message));

		} catch (IOException  e) {

			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "disk,CD,Mexicanissimo(Yanni),2013,1.2" },
				new Object[] { "disk,,,0,0.0" }, };
	}

}

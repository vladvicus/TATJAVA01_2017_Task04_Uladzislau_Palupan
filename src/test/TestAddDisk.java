package test;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.DiskDaoImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestAddDisk {
    DiskDaoImpl ddi = new DiskDaoImpl();
    Disk addedDisk = new Disk();
    @Test(dataProvider = "dp")

    public void f(Disk disk) {

        List<Disk> addedDisks = new ArrayList<>();



        try {
            ddi.addDisk(disk);
            addedDisks = ddi.findDisksByName(disk.getName());
            addedDisk = addedDisks.get(0);

            System.out.println(addedDisk);

        } catch (DaoException e) {

            e.printStackTrace();
        }
        Assert.assertTrue(addedDisk.equals(disk));//without id

    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][]{{new Disk("CD", "UPS!!", 2013, 1.2)},
                };
    }
    @AfterTest
    public void afterTest() {
        try {
            ddi.delete(addedDisk.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}

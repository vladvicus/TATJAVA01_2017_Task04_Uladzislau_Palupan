package test;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.DiskDaoImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class TestDiskDelete {
    @Test(dataProvider = "dp")
    public void f(Disk disk) {

        DiskDaoImpl disk1 = new DiskDaoImpl();
        List<Disk> allDisk = new ArrayList<>();
        List<Disk> disksFindById = new ArrayList<>();

        try {
            disk1.addDisk(disk);

            allDisk = disk1.getAll();
            disk1.delete(allDisk.size() - 1);
            disksFindById = disk1.read(allDisk.size() - 1);
        } catch (DaoException e) {
            System.out.println("DAoexception "+e);
        }


          Assert.assertTrue(disksFindById.isEmpty());

    }


    @DataProvider
    public Object[][] dp() {
        return new Object[][]{{new Disk("CD", "Mexicanissimo(Yanni)", 2013, 1.2)},
        };
    }
}
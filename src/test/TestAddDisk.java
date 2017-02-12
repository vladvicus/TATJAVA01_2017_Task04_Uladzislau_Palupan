package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.epam.catalog.bean.Disk;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.epam.catalog.dao.exception.DaoException;

import com.epam.catalog.dao.impl.DiskDaoImpl;

public class TestAddDisk {
    @Test(dataProvider = "dp")

    public void f(Disk disk) {

        	List<Disk> addedDisks=new ArrayList<>();
        Disk addedDisk = new Disk();
        DiskDaoImpl ddi = new DiskDaoImpl();

        try {
            ddi.addDisk(disk);

            addedDisks = ddi.findDisksByName(disk.getName());
             addedDisk=addedDisks.get(0);



        } catch (DaoException e) {

            e.printStackTrace();
        }
        Assert.assertTrue(addedDisk.equals(disk));//without id

    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][]{{new Disk("CD", "Mexicanissimo(Yanni)", 2013, 1.2)},
                new Object[]{new Disk("", "", 0, 0.0)},};
    }

}

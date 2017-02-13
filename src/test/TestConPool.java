package test;

import com.epam.catalog.dao.connectionpool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestConPool {

    @Test
    public void method() {
        ConnectionPool cp1 = ConnectionPool.getInstance(3);
        ConnectionPool cp2 = ConnectionPool.getInstance(5);

        Assert.assertTrue(cp1 == cp2);
    }

}

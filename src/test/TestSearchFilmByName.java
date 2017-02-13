package test;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.FilmDaoImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearchFilmByName {

    FilmDaoImpl film = new FilmDaoImpl();


    @BeforeTest
    public void beforeTest() {

    }

    @Test(dataProvider = "dp")
    public void f(String name) {


        List<Film> result = null;
        try {

            result = film.findFilmsByName(name);
        } catch (DaoException e) {

            e.printStackTrace();
        }
        if (result.isEmpty()) {
            Assert.assertTrue(result.size() == 0);
        }
        for (Film film : result) {
            System.out.println(film);
            Assert.assertTrue(film.getName().contains(name));
        }

    }


    @DataProvider
    public Object[][] dp() {
        return new Object[][]{new Object[]{"Armagedon"}, new Object[]{"dgf"}, new Object[]{"mile"},};
    }
}

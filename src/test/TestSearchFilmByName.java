package test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.impl.FilmDaoImpl;

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

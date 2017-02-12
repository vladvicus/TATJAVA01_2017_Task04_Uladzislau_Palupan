package test;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.impl.DiskDaoImpl;
import com.epam.catalog.dao.impl.FilmDaoImpl;
import org.testng.annotations.Test;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class FirstTest {
    @Test
    public void f() throws DaoException {
        String datafile = Paths.get("data/units.txt").toAbsolutePath().toString();
        DaoFactory daoFactory = DaoFactory.getInstance();
        //Book book=new Book();
        List<Film> bookFind = new ArrayList<>();
        //Connection con = daoFactory.getConnection();
        List<Disk> diskFind = new ArrayList<>();
        BookDao bookDao = daoFactory.getBookDao();
        FilmDao filmDao = daoFactory.getFilmDao();
        //DiskDao diskDao=daoFactory.getDiskDao();
        DiskDaoImpl disk = new DiskDaoImpl();
        FilmDaoImpl film = new FilmDaoImpl();
        try {
            //	bookDao.delete(7);
            //	bookDao.delete(8);
            //   film.readFile(datafile);
            //bookDao.addBook(new Book("Tolstoj","War and Peace",1000,25.7));
            //bookDao.addBook(new Book("Tolkien","Hobbit",220,15.7));
            //bookDao.addBook(new Book("Roaling","Harry Porter",320,18.7));
            //	bookDao.addBook(new Book("Beth Lewis","The Wolf Road",342,10.91));
            //bookFind = bookDao.getAll();
            bookFind = filmDao.findFilmsByName("Ava");

        } catch (DaoException e) {

            e.printStackTrace();
        }

        for (Film oneBook : bookFind) {
            System.out.println(oneBook);

        }

        //System.out.println(book);


        //assertEquals(book.getPages(),new Integer(220));
        // assertEquals(book.getPrice(),new Double( 15.7));

        assertNotNull(bookFind);
        assertTrue(bookFind.size() > 0);
    }

}
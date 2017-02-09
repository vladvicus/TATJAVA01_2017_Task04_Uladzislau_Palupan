package test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
	  DaoFactory daoFactory = DaoFactory.getInstance();
		Book book=new Book();
		List<Book> bookFind=new ArrayList<>();
		//Connection con = daoFactory.getConnection();

		BookDao bookDao = daoFactory.getBookDao();
		
		try {
		//	bookDao.delete(7);
		//	bookDao.delete(8);

		//	bookDao.addBook(new Book("Tolstoj","War and Peace",1000,25.7));
		//	bookDao.addBook(new Book("Tolkien","Hobbit",220,15.7));
		//	bookDao.addBook(new Book("Roaling","Harry Porter",320,18.7));
		//	bookDao.addBook(new Book("Beth Lewis","The Wolf Road",342,10.91));
			bookFind = bookDao.getAll();
			 book=bookDao.read(2);
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		for (Book oneBook : bookFind) {
			System.out.println(oneBook);

		}
		
		System.out.println(book);
		
		
	assertEquals(book.getPages(),new Integer(220));
     assertEquals(book.getPrice(),new Double( 15.7));
		
		assertNotNull(bookFind);
		assertTrue(bookFind.size() > 0);
  }
}

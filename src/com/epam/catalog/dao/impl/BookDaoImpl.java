package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.db.DBWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final Connection connection;

    public BookDaoImpl() {
        DBWork db = new DBWork();
        this.connection = db.getCn();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public List<Book> read(int key) throws DaoException {
        List<Book> list = null;
        String sql = "SELECT * FROM catalog.books WHERE id = ?";

        PreparedStatement ps = null;

        try {
            list = new ArrayList<Book>();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, key);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setName(rs.getString("name"));
            book.setPages(rs.getInt("pages"));
            book.setPrice(rs.getDouble("price"));
            list.add(book);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            closePrepareStatement(ps);
        }
        return list;

    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM catalog.books WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deletion successful!!");
        } catch (SQLException e) {
            System.out.println("Error while deleting by id" + e);
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public List<Book> getAll() throws DaoException {
        String sql = "SELECT * FROM catalog.books;";
        List<Book> list = new ArrayList<Book>();
        PreparedStatement ps =null;
        try {
            ps=connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));

                book.setPages(rs.getInt("pages"));
                book.setPrice(rs.getDouble("price"));

                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            closePrepareStatement(ps);
        }
        return list;

    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Book> findBooksLessThenPrice(Double price) throws DaoException {

        final String sql = "SELECT * FROM catalog.books WHERE price < ?";
        List<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setPages(rs.getInt("pages"));
                book.setPrice(rs.getDouble("price"));

                list.add(book);

            }
        } catch (SQLException e) {

            throw new DaoException("error while finding books less than price" + e);

        }finally {

            closePrepareStatement(ps);
        }
        return list;
    }


    @Override
    public List<Book> findBooksByAuthor(String author) throws DaoException {
        List<Book> list = null;
        String sql = "SELECT * FROM catalog.books WHERE author = ?";

        PreparedStatement ps = null;

        try {
            list = new ArrayList<Book>();
            ps = connection.prepareStatement(sql);
            ps.setString(1, author);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setPages(rs.getInt("pages"));
                book.setPrice(rs.getDouble("price"));
                list.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            closePrepareStatement(ps);
        }
        return list;

    }

    @Override
    public void addBook(Book book) throws DaoException {
        String sql = "INSERT INTO catalog.books (`author`, `name`, `pages`, `price`) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getName());
            ps.setInt(3, book.getPages());
            ps.setDouble(4, book.getPrice());
            ps.executeUpdate();
            System.out.println("Insert is successful!!");
        } catch (SQLException e) {

            System.out.println("Error while inserting to DB" + e);

        } finally {

            closePrepareStatement(ps);
        }
    }

}

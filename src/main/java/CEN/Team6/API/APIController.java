package CEN.Team6.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class APIController {

        static class Book{
            public String ISBN;
            public String bookName;
            public String genre;
            public String description;
            public String rating;
            public String price;
            public String author;
            public String publisher;
            public String yearPublished;
            public String copiesSold;
            public String bookID;

            public Book(String ISBN, String bookName, String description, String price, String author, String publisher, String yearPublished, String genre, String copiesSold, String rating, String bookID){
                this.ISBN = ISBN;
                this.bookName = bookName;
                this.description = description;
                this.price = price;
                this.author = author;
                this.publisher = publisher;
                this.yearPublished = yearPublished;
                this.genre = genre;
                this.copiesSold = copiesSold;
                this.rating = rating;
                this.bookID = bookID;
            }
        }

        private final JdbcTemplate db;

        @Autowired
        public APIController(JdbcTemplate db){
            this.db = db;
        }
        /**
         * Retrieves all books from the database whose genre is 'x' where 'x' is an input of the user's choice.
         * @param x desired genre of the books to be returned
         * */
        //localhost8080:/bookswithxgenre?x=mystery
        @GetMapping("/bookswithxgenre")
        public List<Book> booksWithXGenre(@RequestParam String x){
            String query = "SELECT * FROM Books WHERE LOWER(`Genre`) = LOWER(\"" + x + "\")";
            List<Book> res = new ArrayList<>();
            db.query(query, (resultSet, i) -> res.add(new Book(resultSet.getString("ISBN"),
                    resultSet.getString("Book Name"), resultSet.getString("Description"),
                    resultSet.getString("Price"), resultSet.getString("Author"),
                    resultSet.getString("Publisher"), resultSet.getString("Year Published"),
                    resultSet.getString("Genre"), resultSet.getString("Copies Sold"),
                    resultSet.getString("Ratings"), resultSet.getString("BookID"))));
            return res;
        }
        /**
         * Retrieves the top 'k' books with the most copies sold where 'k' is an integer of the user's choice.
         * @param k the top number of books with the copies sold
         * */
        //localhost:8080/topksellers?k=5
        @GetMapping("/topksellers")
        public List<Book> topKSellers(@RequestParam int k){
            List<Book> books = new ArrayList<>();
            String query = "SELECT * FROM Books ORDER BY `Copies Sold` DESC LIMIT " + k;
            db.query(query, (resultSet, i) -> books.add(new Book(resultSet.getString("ISBN"),
                    resultSet.getString("Book Name"), resultSet.getString("Description"),
                    resultSet.getString("Price"), resultSet.getString("Author"),
                    resultSet.getString("Publisher"), resultSet.getString("Year Published"),
                    resultSet.getString("Genre"), resultSet.getString("Copies Sold"),
                    resultSet.getString("Ratings"), resultSet.getString("BookID"))));
            return books;
        }

        /**
         * Retrieves all of the books that have a rating of 'x' and higher where 'x' is an integer from 1 - 5 and
         * it is an input of the user's choice.
         * @param x the desired rating of the books.
         * *** Note: the rating 'x' is included in the output ***
         * */
        //localhost:8080/booksofxratingandhigher?x=2
        @GetMapping("/booksofxratingandhigher")
        public List<Book> booksOfXRatingAndHigher(@RequestParam int x){
            if (x < 1 || x > 5)
                throw new IllegalArgumentException("Rating can only be from 1 - 5");
            String query = "SELECT * FROM Books WHERE `Ratings` >= " + x +  " ORDER BY `Ratings` ASC";
            List<Book> res = new ArrayList<>();
            db.query(query, (resultSet, i) -> res.add(new Book(resultSet.getString("ISBN"),
                    resultSet.getString("Book Name"), resultSet.getString("Description"),
                    resultSet.getString("Price"), resultSet.getString("Author"),
                    resultSet.getString("Publisher"), resultSet.getString("Year Published"),
                    resultSet.getString("Genre"), resultSet.getString("Copies Sold"),
                    resultSet.getString("Ratings"), resultSet.getString("BookID"))));
            return res;
        }

        /**
         * Retrieves 'x' books from the database where the indices of the rows fall within the interval [start, start + x),
         * where 'x' is the input of the user's choice and 'start' is also an input of the user's choice.
         * @param x the number of books
         * @param start starting row
         * */
        // localhost:8080/xbooksatatime?x=5&start=1
        @GetMapping("/xbooksatatime")
        public List<Book> XBooksAtATime(@RequestParam int x,@RequestParam int start){
            List<Book> res = new ArrayList<>();
            String query = "SELECT * FROM Books LIMIT " + start + ", " + x;
            db.query(query, (resultSet, i) -> res.add(new Book(resultSet.getString("ISBN"),
                    resultSet.getString("Book Name"), resultSet.getString("Description"),
                    resultSet.getString("Price"), resultSet.getString("Author"),
                    resultSet.getString("Publisher"), resultSet.getString("Year Published"),
                    resultSet.getString("Genre"), resultSet.getString("Copies Sold"),
                    resultSet.getString("Ratings"), resultSet.getString("BookID"))));
            return res;
        }
    }
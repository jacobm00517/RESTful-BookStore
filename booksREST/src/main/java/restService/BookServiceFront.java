package restService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.BookDAO;
import dao.BookDAOImpl;
import model.Book;
import model.Category;

@Path("/Books")
public class BookServiceFront {

    @Context
    private ServletContext context; // Inject the ServletContext

    private BookDAO bookDao;

    @PostConstruct
    public void init() {
        // Get the real path of the database file
        String dbPath = context.getRealPath("/WEB-INF/Books.db");
        if (dbPath == null) {
            throw new IllegalStateException("Database file not found at the specified path.");
        }
        bookDao = new BookDAOImpl(dbPath); // Initialize the DAO with the database path
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryNames() {
        try {
            List<Category> categoryList = bookDao.findAllCategories();
            return Response.ok(categoryList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error retrieving categories: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        try {
            List<Book> bList = bookDao.findAllBooks();
            return Response.ok(bList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error retrieving books: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/searchByCat/{catid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooksByCategory(@PathParam("catid") String category) {
        try {
            List<Book> bList = bookDao.findBooksByCategory(category);
            return Response.ok(bList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error searching books by category: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/searchByKey/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooksByKeyword(@PathParam("keyword") String keyword) {
        try {
            List<Book> bList = bookDao.searchBooksByKeyword(keyword);
            return Response.ok(bList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error searching books by keyword: " + e.getMessage())
                           .build();
        }
    }
}
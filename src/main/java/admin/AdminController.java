package admin;

import data.BlogPost;
import database.Database;
import database.DatabaseHandler;
import role.LoggedInRole;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static login.LoginController.ATTR_NAME;
import static login.LoginController.ATTR_ROLE;
import static login.LoginController.isAdmin;
import static util.ViewUtil.render;

public class AdminController {

    public static Route serveAdminHomePage = (Request request, Response response) -> {
        if (isAdmin(request)) {
            Map<String, Object> model = new HashMap<>();
            model.put("page_title", "Web-Template");
            model.put("text_title", "Välkommen admin");
            model.put("home_link", Path.Web.ADMIN_HOME);
            model.put(ATTR_ROLE, LoggedInRole.ADMIN.getRoleName());
            model.put(ATTR_NAME, request.session().attribute(ATTR_NAME));
            return render(model, Path.Template.ADMIN_HOME);
        } else {
            response.redirect(Path.Web.LOGIN);
            return null;
        }
    };
    public static Route serveAdminBlogPage = (Request request, Response response) -> {
        if (isAdmin(request)) {
            Map<String, Object> model = new HashMap<>();
            model.put("page_title", "Web-Template");
            model.put("text_title", "Välkommen admin");
            model.put("home_link", Path.Web.ADMIN_HOME);
            Database db = DatabaseHandler.getDatabase();
            List<BlogPost> blogPosts = db.getSelector().getAllBlogPosts();
            model.put("all_blog_posts", blogPosts);
            model.put(ATTR_ROLE, LoggedInRole.ADMIN.getRoleName());
            model.put(ATTR_NAME, request.session().attribute(ATTR_NAME));
            return render(model, Path.Template.ADMIN_BLOG);
        } else {
            response.redirect(Path.Web.LOGIN);
            return null;
        }
    };


    public static Route handleAddNewBlogPost = (Request request, Response response) -> {
        if (isAdmin(request)) {
            Database db = DatabaseHandler.getDatabase();
            String title = getQueryBlogTitle(request);
            String content = getQueryBlogContent(request);
            String author = getQueryBlogAuthor(request);
            db.getInserter().addNewBlogPost(new BlogPost(title, content, author));
            return serveAdminBlogPage.handle(request, response);
        }else{
            response.redirect(Path.Web.ADMIN_HOME);
            return null;
        }

    };

    private static String getQueryBlogTitle(Request request) {
        return request.queryParams("blogTitle");
    }
    private static String getQueryBlogContent(Request request) {
        return request.queryParams("blogContent");
    }
    private static String getQueryBlogAuthor(Request request) {
        return request.queryParams("blogAuthor");
    }
}

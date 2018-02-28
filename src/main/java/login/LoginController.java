package login;

import role.LoggedInRole;


import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import util.Path;

import java.util.HashMap;
import java.util.Map;

import static auth.Authenticator.authenticateAdmin;


import static util.ViewUtil.render;

public class LoginController {

    public static String ATTR_ROLE = "role";
    public static String ATTR_NAME = "name";


    public static Route handleLoginPost = (Request request, Response response) -> {
        String email = getQueryEmail(request);
        String password = getQueryPassword(request);
        if (authenticateAdmin(email, password)) {
            setLoginAttributes(request.session(), email, LoggedInRole.ADMIN);
            response.redirect(Path.Web.HOME);
            return null;
            
        }
        else {
            Map<String, Object> model = new HashMap<>();
            model.put("page_title", "Title");
            model.put("text_title", "Logga in");
            model.put("login_failed", true);
            model.put("email_input", email);
            return render(model, Path.Template.INDEX);
        }

    };

    public static Route handleLogoutPost = (Request request, Response response) -> {
        if (isLoggedIn(request)) {
            request.session().removeAttribute(ATTR_ROLE);
            request.session().removeAttribute(ATTR_NAME);
            response.redirect(Path.Web.HOME);
            return null;
        }
        response.redirect(Path.Web.HOME);
        return null;
    };

    public static boolean isLoggedIn(Request request) {
        return request.session().attribute(ATTR_ROLE) != null;
    }

    public static boolean isAdmin(Request request) {
        return isRole(LoggedInRole.ADMIN, request);
    }

    private static boolean isRole(LoggedInRole role, Request request) {
        Object roleAttribute = request.session().attribute(ATTR_ROLE);
        return roleAttribute != null && roleAttribute.equals(role);
    }

    private static String getQueryEmail(Request request) {
        return request.queryParams("username");
    }

    private static String getQueryPassword(Request request) {
        return request.queryParams("password");
    }

    public static void setLoginAttributes(Session session, String email, LoggedInRole role) {
        session.attribute(ATTR_ROLE, role);
        session.attribute(ATTR_NAME, email);
    }

}

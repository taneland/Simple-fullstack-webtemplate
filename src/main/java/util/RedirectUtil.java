package util;

import spark.Request;
import spark.Response;

import static login.LoginController.isAdmin;

public class RedirectUtil {

    public static void redirectToRoleHome(Request request, Response response) {
        if (isAdmin(request)) {
            response.redirect(Path.Web.ADMIN_HOME);
        }
    }

}

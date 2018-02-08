package main;

import blog.BlogController;
import contact.ContactController;
import index.IndexController;
import login.LoginController;
import util.Filters;
import util.Path;
import util.ViewUtil;

import static spark.Spark.*;



public class Main {

    public static void main(String[] args) throws Exception {
        initSpark();
    }

    private static void initSpark() {
        ipAddress("localhost");
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600);

        before("*", Filters.addTrailingSlashes);
        before("*", Filters.setSessionTimeout);

        get(Path.Web.HOME, IndexController.serveIndexPage);
        get(Path.Web.CONTACT, ContactController.serveContactPage);
        get(Path.Web.BLOG, BlogController.serveBlogPage);
        get(Path.Web.LOGIN, LoginController.serveLoginPage);

        notFound(ViewUtil.notFound);
        internalServerError(ViewUtil.internalServerError);
    }


}

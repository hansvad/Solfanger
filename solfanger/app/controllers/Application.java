package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	response.cacheFor("10mn");
    	render();
    }
    
    public static void temp() {
    	response.cacheFor("10mn");
        render();
    }
    
    public static void history() {
    	response.cacheFor("1h");
        render();
    }

}
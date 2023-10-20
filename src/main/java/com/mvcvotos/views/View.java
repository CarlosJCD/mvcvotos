package com.mvcvotos.views;

import javafx.application.Application;
import com.mvcvotos.controllers.Controller;

public abstract class View extends Application implements Observer {
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

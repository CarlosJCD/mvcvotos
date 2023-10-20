package com.mvcvotos.controllers;

import com.mvcvotos.models.Model;

public abstract class Controller {
    protected Model model;

    public void setModel(Model mod) {
        this.model = mod;
    }

    public Model getModelo() {
        return this.model;
    }
}

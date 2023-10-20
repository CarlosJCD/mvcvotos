package com.mvcvotos;

import com.mvcvotos.controllers.VotesController;
import com.mvcvotos.models.VotesModel;
import com.mvcvotos.views.BarChartView;
import com.mvcvotos.views.PieChartView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class VotesWindow extends Application {
    private VotesController controller;

    @Override
    public void start(Stage stage) throws Exception {

        VotesModel model = new VotesModel();

        PieChartView pieChart = new PieChartView();
        BarChartView barChart = new BarChartView();

        controller = new VotesController();
        controller.setModel(model);

        displayView(pieChart);
        displayView(barChart);

        model.addObserver(pieChart);
        model.addObserver(barChart);

        Label l = new Label("Vista Candidatos");

        Button botonCandidato1 = new Button("Candidato 1");
        Button botonCandidato2 = new Button("Candidato 2");
        Button botonCandidato3 = new Button("Candidato 3");

        attachVoteEvent(botonCandidato1);
        attachVoteEvent(botonCandidato2);
        attachVoteEvent(botonCandidato3);

        ButtonBar botonesCandidatos = new ButtonBar();
        botonesCandidatos.getButtons().addAll(botonCandidato1, botonCandidato2, botonCandidato3);

        TilePane tilePane = new TilePane(l);
        tilePane.getChildren().add(botonesCandidatos);

        tilePane.setOrientation(Orientation.VERTICAL);
        tilePane.setTileAlignment(Pos.CENTER);

        Scene scene = new Scene(tilePane, tilePane.widthProperty().get(), tilePane.heightProperty().get());

        stage.setScene(scene);
        stage.show();

    }

    private void attachVoteEvent(Button candidateButton) {
        EventHandler<ActionEvent> event = e -> controller.executeVote(candidateButton.getText());

        candidateButton.setOnAction(event);

    }

    private void displayView(Application view) throws Exception {
        Stage stage = new Stage();
        view.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}

package com.mvcvotos.views;

import java.util.Arrays;
import java.util.Map;

import com.mvcvotos.models.Model;
import com.mvcvotos.models.VotesModel;
import com.mvcvotos.utilities.ModelTransformer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class BarChartView extends View {
    private BarChart<String, Number> barChart;

    public BarChartView() {
        // Empty Constructor
    }

    @Override
    public void start(Stage stage) throws Exception {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(
                FXCollections.<String>observableArrayList(Arrays.asList("votos")));
        xAxis.setLabel("Candidatos");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Votos");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Votos Candidatos");

        XYChart.Series<String, Number> seriesCandidate1 = new XYChart.Series<>();
        seriesCandidate1.setName("Candidato 1");
        seriesCandidate1.getData().add(generateCandidateVoteData());

        XYChart.Series<String, Number> seriesCandidate2 = new XYChart.Series<>();
        seriesCandidate2.setName("Candidato 2");
        seriesCandidate2.getData().add(generateCandidateVoteData());

        XYChart.Series<String, Number> seriesCandidate3 = new XYChart.Series<>();
        seriesCandidate3.setName("Candidato 3");
        seriesCandidate3.getData().add(generateCandidateVoteData());

        barChart.getData().addAll(seriesCandidate1, seriesCandidate2, seriesCandidate3);

        Group root = new Group(barChart);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Graficas de Barras");

        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void update(Model model) {
        VotesModel votesModel = ModelTransformer.modelToVotesModel(model);

        Map<String, Integer> registeredVotes = votesModel.getRegisteredVotes();

        for (Map.Entry<String, Integer> votesEntry : registeredVotes.entrySet()) {
            updateCandidate(votesEntry.getKey(), votesEntry.getValue());
        }
    }

    private XYChart.Data<String, Number> generateCandidateVoteData() {
        return new XYChart.Data<>("votos", 0);
    }

    private void updateCandidate(String candidate, Integer candidateVotes) {
        ObservableList<Series<String, Number>> chartData = barChart.getData();

        for (Series<String, Number> candidateSeries : chartData) {
            if (candidateSeries.getName().equals(candidate)) {
                candidateSeries.getData().get(0).setYValue(candidateVotes);
            }
        }
    }

}

package com.mvcvotos.views;

import java.util.Map;
import java.util.Optional;

import com.mvcvotos.models.Model;
import com.mvcvotos.models.VotesModel;
import com.mvcvotos.utilities.ModelTransformer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PieChartView extends View {

    private PieChart pieChart;

    public PieChartView() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("Candidato 1", 0));
        data.add(new PieChart.Data("Candidato 2", 0));
        data.add(new PieChart.Data("Candidato 3", 0));

        pieChart = new PieChart(data) {
            // Data Labels Code Extracted From:
            // https://stackoverflow.com/questions/35479375/display-additional-values-in-pie-chart
            @Override
            protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
                if (getLabelsVisible()) {
                    getData().forEach(d -> {
                        Optional<Node> opTextNode = pieChart.lookupAll(".chart-pie-label").stream()
                                .filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName())).findAny();
                        if (opTextNode.isPresent() && d.getPieValue() != 0) {
                            ((Text) opTextNode.get()).setText(d.getName() + " - " + d.getPieValue() + " Votes");
                        }
                    });
                }
                super.layoutChartChildren(top, left, contentWidth, contentHeight);
            }
        };
        pieChart.setTitle("Votos Candidatos");
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitleSide(Side.TOP);
        pieChart.setLabelLineLength(60);
        pieChart.setLabelsVisible(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane(pieChart);
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Grafica de Pastel Votos");
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

    private void updateCandidate(String candidate, Integer candidateVotes) {
        ObservableList<PieChart.Data> chartData = pieChart.getData();

        for (PieChart.Data candidateData : chartData) {
            if (candidateData.getName().equals(candidate)) {
                candidateData.setPieValue(candidateVotes);
                return;
            }
        }
        chartData.add(new PieChart.Data(candidate, candidateVotes));
    }

}

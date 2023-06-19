package client;

import java.io.InputStream;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private static final int MONOPOLY_FIELD_SIZE = 11;
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;

    private static final double LOG_WIDTH_RATIO = 1. / 4.;
    private static final double LOG_HEIGHT_RATIO = 1;
    private static final double FIELD_WIDTH_RATIO = 1 - LOG_WIDTH_RATIO;
    private static final double FIELD_HEIGHT_RATIO = 1;

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupWindowAppereance(primaryStage);
        setupWindowLayout(primaryStage);

        primaryStage.show();
    }

    private void setupWindowAppereance(Stage stage) {
        stage.setTitle("Monopoly");

        InputStream iconStream = getClass().getResourceAsStream("/icons/monopoly.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
    }

    private void setupWindowLayout(Stage stage) {
        BorderPane root = new BorderPane();

        VBox stats = new VBox();
        root.setLeft(stats);

        Pane log = generateLog(stage);
        root.setRight(log);

        GridPane field = generateField(stage);
        root.setCenter(field);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/log.css").toExternalForm());
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/field.css").toExternalForm());

        stage.setMinWidth(WINDOW_WIDTH);
        stage.setMinHeight(WINDOW_HEIGHT);

        stage.setScene(scene);
    }

    private Pane generateLog(Stage stage) {
        Pane pane = new Pane();

        Label log = new Label("Game log...");
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                log.setPrefWidth((double) number2 * LOG_WIDTH_RATIO);
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number,
                    Number number2) {
                log.setPrefHeight((double) number2 * LOG_HEIGHT_RATIO);
            }
        });
        pane.getChildren().add(log);
        log.setAlignment(Pos.TOP_LEFT);

        pane.getStyleClass().add("pane");

        return pane;
    }

    private GridPane generateField(Stage stage) {
        GridPane field = new GridPane();

        Integer index = 1;
        for (int y = 0; y < MONOPOLY_FIELD_SIZE; y++) {
            for (int x = 0; x < MONOPOLY_FIELD_SIZE; x++) {
                boolean xCond = x == 0 || x == MONOPOLY_FIELD_SIZE - 1;
                boolean yCond = y == 0 || y == MONOPOLY_FIELD_SIZE - 1;

                if (!xCond && !yCond)
                    continue;

                String fmt = index.toString();
                if (index < 10) {
                    fmt = "0" + fmt;
                }

                Button card = new Button(fmt);
                field.prefWidthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2) {
                        card.setPrefWidth((double) field.getPrefWidth() / MONOPOLY_FIELD_SIZE);
                    }
                });
                field.prefHeightProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2) {
                        card.setPrefHeight((double) field.getPrefHeight() / MONOPOLY_FIELD_SIZE);
                    }
                });
                card.getStyleClass().add("card");

                field.add(card, x, y);
                index++;
            }
        }

        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number,
                    Number number2) {
                field.setPrefWidth((double) number2 * FIELD_WIDTH_RATIO);
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number,
                    Number number2) {
                field.setPrefHeight((double) number2 * FIELD_HEIGHT_RATIO);
            }
        });

        field.getStyleClass().add("gridpane");

        return field;
    }

}

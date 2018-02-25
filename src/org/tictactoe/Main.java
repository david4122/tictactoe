package org.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{
	private TicTacToePane mainPane;

	@Override
	public void start(Stage stage){
		this.mainPane = new TicTacToePane();
		stage.setTitle("TicTacToe");
		Scene scene = new Scene(mainPane, 300, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}

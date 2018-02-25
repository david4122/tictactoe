package org.tictactoe;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import static org.tictactoe.FieldContent.*;

public class TicTacToePane extends GridPane {
	private class FieldListener implements EventHandler<ActionEvent> {

		private Field f;
		private int x;
		private int y;

		public FieldListener(Field f, int x, int y) {
			this.f = f;
			this.x = x;
			this.y = y;
		}

		@Override
		public void handle(ActionEvent e){
			try {
				f.setContent(current);
				if(TicTacToePane.this.check(this.x, this.y)){
					TicTacToePane.this.gameOver();
				}
				TicTacToePane.this.current = (current == CROSS) ? CIRCLE : CROSS;
			} catch(FieldBusyException ex){
				TicTacToePane.this.illegalMove();
			}
		}
	}

	private FieldContent current;
	private Field fields[][];

	TicTacToePane(){
		this.current = FieldContent.CROSS;
		this.fields = new Field[3][3];
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				Field f = new Field();
				f.setOnAction(new FieldListener(f, i, j));
				fields[i][j] = f;
				f.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				GridPane.setHgrow(f, Priority.ALWAYS);
				GridPane.setVgrow(f, Priority.ALWAYS);
				add(f, i, j);
			}
		}
	}

	void gameOver(){
		Alert alert = new Alert(AlertType.INFORMATION, "You won!", ButtonType.OK);
		alert.setHeaderText(null);
		alert.showAndWait();
		Platform.exit();
	}

	void illegalMove(){
		Alert alert = new Alert(AlertType.ERROR, "This field is busy!", ButtonType.OK);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	private boolean check(int x, int y) {
		FieldContent fc = fields[x][y].getContent();
		boolean found = true;
		for(int i=0; i<3; i++) {
			if(!fields[x][i].getContent().equals(fc)){
				found = false;
				break;
			}
		}
		if(found)
			return true;
		found = true;
		for(int i=0; i<3; i++) {
			if(!fields[i][y].getContent().equals(fc)){
				found = false;
				break;
			}
		}
		if(found)
			return true;
		if(x == y){
			found = true;
			for(int i=0; i<3; i++){
				if(!fields[i][i].getContent().equals(fc)){
					found = false;
					break;
				}
			}
		}
		if(found)
			return true;
		if(x+y == 2){
			found = true;
			for(int i=0; i<3; i++){
				if(!fields[i][2-i].getContent().equals(fc)){
					found = false;
					break;
				}
			}
		}
		return found;
	}
}

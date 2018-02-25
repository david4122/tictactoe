package org.tictactoe;

import javafx.scene.control.Button;

enum FieldContent {
	EMPTY(" "), CROSS("x"), CIRCLE("o");

	private String chr;

	FieldContent(String chr){
		this.chr = chr;
	}

	@Override
	public String toString(){
		return this.chr;
	}
}

class Field extends Button {

	private FieldContent content;

	Field(){
		this(FieldContent.EMPTY);
	}

	Field(FieldContent content) {
		this.content = content;
	}

	void setContent(FieldContent content) throws FieldBusyException {
		if(this.content != FieldContent.EMPTY)
			throw new FieldBusyException();
		this.content = content;
		this.setText(content.toString());
	}

	FieldContent getContent() {
		return content;
	}

}

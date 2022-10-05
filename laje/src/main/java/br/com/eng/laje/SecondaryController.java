package br.com.eng.laje;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Group;

public class SecondaryController {
	
	PrimaryController primaryController;
	
	@FXML
	Group groupYNegativo = new Group();
	
	@FXML
	Group groupXNegativo = new Group();
	
	@FXML
	Group groupY1Negativo = new Group();
	
	@FXML
	Group groupX1Negativo = new Group();

	public SecondaryController() {
		
	}
	
	@FXML
	private void btRetornaTelaDeCalculos() throws IOException {
		
		App.setRoot("primary");
		
	}
	
	public void mostraEngastes(boolean b) {
		
		groupYNegativo.setVisible(b);
		
	}

	public PrimaryController getPrimaryController() {
		return primaryController;
	}

	public Group getGroupYNegativo() {
		return groupYNegativo;
	}

	public Group getGroupXNegativo() {
		return groupXNegativo;
	}

	public Group getGroupY1Negativo() {
		return groupY1Negativo;
	}

	public Group getGroupX1Negativo() {
		return groupX1Negativo;
	}

	public void setPrimaryController(PrimaryController primaryController) {
		this.primaryController = primaryController;
	}

	public void setGroupYNegativo(Group groupYNegativo) {
		this.groupYNegativo = groupYNegativo;
	}

	public void setGroupXNegativo(Group groupXNegativo) {
		this.groupXNegativo = groupXNegativo;
	}

	public void setGroupY1Negativo(Group groupY1Negativo) {
		this.groupY1Negativo = groupY1Negativo;
	}

	public void setGroupX1Negativo(Group groupX1Negativo) {
		this.groupX1Negativo = groupX1Negativo;
	}
		
}
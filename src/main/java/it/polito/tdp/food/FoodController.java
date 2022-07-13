/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.db.DBConnect;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.FoodGrassi;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Portion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrassi"
    private Button btnGrassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<FoodGrassi> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n");
    	try {
    		int n =Integer.parseInt(txtPorzioni.getText());
    		this.model.creaGrafo(n);
    		txtResult.appendText("Grafo creato con successo!\n");
    		txtResult.appendText("#Vertici: "+ this.model.getNVertici()+"\n");
    		txtResult.appendText("#Archi: "+ this.model.getNArchi()+"\n");
    		
    		for(FoodGrassi f : this.model.getVertici(n))
    		{
    			boxFood.getItems().add(f);
    		}
    	
    		
			
		} catch (NumberFormatException e) {
			txtResult.appendText("Bisonga inserire un numero!");
			return;
			
		}
    }

    @FXML
    void doGrassi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi grassi...");
    	
    	if(this.model.grafoCreato())
    	{
    		for(FoodGrassi fg1: this.model.getMingrassi(boxFood.getValue()))
    		{
    			for(FoodGrassi fg2: this.model.getMingrassi(boxFood.getValue()))
        		{
    				if(fg1.getGrassi()<fg2.getGrassi())
    				{
    					txtResult.appendText(fg1.toString());
    				}
    				else 
    				{
    					txtResult.appendText(fg2.toString());
    				}
    					
        		}
    		}
    	}
    	else
    	{
    		txtResult.appendText("bisogna prima creare il grafo!");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnGrassi != null : "fx:id=\"btnGrassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

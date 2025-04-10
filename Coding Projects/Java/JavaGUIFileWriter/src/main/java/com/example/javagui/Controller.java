package com.example.javagui;
import com.example.javagui.datamodel.Customer;
import com.example.javagui.datamodel.CustomerData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Button submitButton;
    @FXML
    private Button finishButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipField;
    @FXML
    private CheckBox appBox;
    @FXML
    private CheckBox musicBox;
    @FXML
    private ComboBox musicCombo;
    @FXML
    private RadioButton gameRadio;
    @FXML
    private RadioButton prodRadio;
    @FXML
    private RadioButton eduRadio;
    @FXML
    private TextField titleField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField accField;
    @FXML
    private String comboChoose;
    @FXML
    private Label musicTypeLabel;
    @FXML
    private Label appTypeLabel;
    @FXML
    private ToggleGroup radio;


    @FXML
    private void onSubmitClick() {


        boolean completeFlag = true;
        boolean appFlag = false;
        boolean musicFlag = false;
        ArrayList<Customer> customerList;
        Customer customer = new Customer();
        CustomerData customerData = new CustomerData();


        if(!nameField.getText().isEmpty() && !nameField.getText().isBlank()) {
            System.out.println("Name: " + nameField.getText());
            customer.setName(nameField.getText());
        }
        else {
            System.out.println("Name is blank.");
            nameField.requestFocus();
            showBlankAlert("Name");
            return;
        }

        if(!streetField.getText().isEmpty() && !streetField.getText().isBlank()) {
            System.out.println("Street: " + streetField.getText());
            customer.setStreet(streetField.getText());
        }
        else {
            System.out.println("Street is blank.");
            streetField.requestFocus();
            showBlankAlert("Street");
            return;
        }

        if(!cityField.getText().isEmpty() && !cityField.getText().isBlank()) {
            System.out.println("City: " +cityField.getText());
            customer.setCity(cityField.getText());
        }
        else {
            System.out.println("City is blank.");
            cityField.requestFocus();
            showBlankAlert("City");
            return;
        }

        if(!stateField.getText().isEmpty() && !stateField.getText().isBlank()) {
            System.out.println("State: " + stateField.getText());
            customer.setState(stateField.getText());
        }
        else {
            System.out.println("State is blank.");
            stateField.requestFocus();
            showBlankAlert("State");
            return;
        }

        if(!zipField.getText().isEmpty() && !zipField.getText().isBlank()) {
            System.out.println("Zip Code: " + zipField.getText());
            customer.setZip(zipField.getText());
        }
        else {
            System.out.println("Zip is blank.");
            zipField.requestFocus();
            showBlankAlert("Zip");
            return;
        }

        if(appBox.isSelected() && !musicBox.isSelected()) {
            System.out.println("Type selected: App");
            customer.setType("App");
        }
        else if (musicBox.isSelected() && !appBox.isSelected()) {
            System.out.println("Type selected: Music");
            customer.setType("Music");
        }
        else if (appBox.isSelected() && musicBox.isSelected()) {
            System.out.println("Type selected: Both, Error");
            return;
        }
        else if (!appBox.isSelected() && !musicBox.isSelected()) {
            System.out.println("Neither app nor music selected.");
            appBox.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Neither app nor music is selected.");
            alert.show();
            return;
        }


        if(appBox.isSelected()) {

            musicFlag = false;

            if (gameRadio.isSelected()) {
                System.out.println("App type: Game");
                customer.setSubtype("Game");
                appFlag = true;
            } else if (prodRadio.isSelected()) {
                System.out.println("App type: Productivity");
                customer.setSubtype("Productivity");
                appFlag = true;
            } else if (eduRadio.isSelected()) {
                System.out.println("App type: Education");
                customer.setSubtype("Education");
                appFlag = true;
            } else if (!gameRadio.isSelected() && !prodRadio.isSelected() && !eduRadio.isSelected()) {
                System.out.println("No app type selected.");
                gameRadio.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR, "No app type is selected.");
                alert.show();
                return;
            }
        }
        else if(musicBox.isSelected()){

            appFlag = false;

            if(musicCombo.getValue()==comboChoose){
                System.out.println("Type of music is blank.");
                musicCombo.requestFocus();
                showBlankAlert("Music");
                return;
            }
            else{
                customer.setSubtype(musicCombo.getValue().toString());
                musicFlag = true;
            }
        }

        if(!titleField.getText().isEmpty() && !titleField.getText().isBlank()) {
            System.out.println("Title: " + titleField.getText());
            customer.setTitle(titleField.getText());
        }
        else {
            System.out.println("Title is blank.");
            titleField.requestFocus();
            showBlankAlert("Title");
            return;
        }

        if(!dateField.getText().isEmpty() && !dateField.getText().isBlank()) {
            System.out.println("Date Purchased: " + dateField.getText());
            customer.setDate(dateField.getText());
        }
        else {
            System.out.println("Date Purchased is blank.");
            dateField.requestFocus();
            showBlankAlert("Date");
            return;
        }

        if(!accField.getText().isEmpty() && !accField.getText().isBlank()) {
            System.out.println("Account Number: " + accField.getText());
            customer.setAccNumber(accField.getText());
        }
        else {
            System.out.println("Account Number is blank.");
            accField.requestFocus();
            showBlankAlert("Account Number");
            return;
        }

        if(completeFlag){
            //Append data to customer array
            System.out.println("Complete flag triggered");

            customerList = new ArrayList<>();
            customerList.add(customer);
            customerData.setCustomerItems(customerList);

            if(appFlag){
                try {
                    customerData.storeAppCustomerItems();
                    System.out.println("App flag triggered");
                } catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(musicFlag){
                try {
                    customerData.storeMusicCustomerItems();
                    System.out.println("Music flag triggered");

                } catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }

            nameField.setText("");
            nameField.requestFocus();
            streetField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");
            appBox.setSelected(false);
            musicBox.setSelected(false);
            titleField.setText("");
            dateField.setText("");
            accField.setText("");
            musicCombo.setValue(comboChoose);
            musicCombo.setDisable(false);
            radio.selectToggle(null);
        }

    }

    @FXML
    private void onFinishClick() {
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCheckBoxes() {
        if(appBox.isSelected()){
            musicCombo.setDisable(true);
            musicTypeLabel.setDisable(true);
            musicCombo.setValue(comboChoose);
        }
        else{
            musicCombo.setDisable(false);
            musicTypeLabel.setDisable(false);
        }

        if(musicBox.isSelected()){
            gameRadio.setDisable(true);
            prodRadio.setDisable(true);
            eduRadio.setDisable(true);
            appTypeLabel.setDisable(true);
            radio.selectToggle(null);
        }
        else{
            gameRadio.setDisable(false);
            prodRadio.setDisable(false);
            eduRadio.setDisable(false);
            appTypeLabel.setDisable(false);
        }
    }

    private void showBlankAlert(String field){
        Alert alert = new Alert(Alert.AlertType.ERROR, field + " field is blank.");
        alert.show();
    };
}
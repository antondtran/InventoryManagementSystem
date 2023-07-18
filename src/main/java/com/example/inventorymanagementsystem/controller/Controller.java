package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    @FXML
    private TableView<Part> productPartTableView;

    private static int assignedPartID = 0;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partInventoryList;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TextField searchPart;

    @FXML
    private TableView<Product> productTableView;



    @FXML
    private TableColumn<Product, Integer> productID;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, Integer> productInvList;

    @FXML
    private TableColumn<Product, Double> productPrice;

    @FXML
    private TextField searchProduct;

    @FXML
    private TextField searchProductPart;


    @FXML
    private TextField productIDTextField;

    @FXML
    private TextField productInventoryTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productMinTextField;

    @FXML
    private TextField productMaxTextField;



    private Inventory inventory = new Inventory();

    private TableView<Part> userPartTableView;



    private FilteredList<Part> filteredParts;

    private FilteredList<Product> filteredProducts = new FilteredList<>(inventory.getAllProducts());

    private ObservableList<Part> partList = FXCollections.observableArrayList();




    public void initialize() {


        // used to set each data to its respective cell.

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryList.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvList.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));





        // search bar for parts and product table view

        filteredParts = new FilteredList<>(inventory.getAllParts(), p -> true);

        searchPart.setPromptText("Search by Part ID or Name");
        searchPart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();
                return part.getName().toLowerCase().contains(searchText) || String.valueOf(part.getId()).contains(searchText);
            });
            partTableView.setItems(filteredParts);
        });

        searchProduct.setPromptText("Search by Product ID or Name");
        searchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();
                return product.getName().toLowerCase().contains(searchText) || String.valueOf(product.getId()).contains(searchText);
            });
            productTableView.setItems(filteredProducts);
        });
    }

    /**
     *
     * @param event when user clicks on the add button a form will be display to allow the user to enter in parts information.
     */

    @FXML
    public void partAddBtn(ActionEvent event) {



        // radio button for outsource and inhouse
        RadioButton partOutsourcedBtn = new RadioButton("Outsourced");
        RadioButton partInHouseBtn = new RadioButton("In-House");

        // label for parts
        Label partIDLabel = new Label("Part ID");
        Label partNameLabel = new Label("Part Name");
        Label partInventoryLevelLabel = new Label("Inventory Level");
        Label partPriceLabel = new Label("Price");
        Label partMinLabel = new Label("min");
        Label partMaxLabel = new Label("max");
        Label partSourceLabel = new Label("Machine ID");


        // textfield for parts
        TextField partIDTextField = new TextField();
        partIDTextField.setEditable(false);
        partIDTextField.setPromptText("Auto Gen");
        TextField partNameTextField = new TextField();
        TextField partInventoryTextField = new TextField();
        TextField partPriceTextField = new TextField();
        TextField partMinTextField = new TextField();
        TextField partMaxTextField = new TextField();
        TextField partSourceTextField = new TextField();


        ToggleGroup toggleGroup = new ToggleGroup();
        partOutsourcedBtn.setToggleGroup(toggleGroup);
        partInHouseBtn.setToggleGroup(toggleGroup);
        partInHouseBtn.setSelected(true);



        partOutsourcedBtn.setOnAction(actionEvent -> {
            if (partOutsourcedBtn.isSelected()){
                partSourceLabel.setText("Company Name");
            }
        });


        partInHouseBtn.setOnAction(actionEvent -> {
            if (partInHouseBtn.isSelected()) {

                partSourceLabel.setText("Machine ID");
            }
        });

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");



        // gridpane that holds label and textfields together

        GridPane partsGridPane = new GridPane();
        partsGridPane.add(partOutsourcedBtn, 0, 0);
        partsGridPane.add(partInHouseBtn, 1, 0);
        partsGridPane.add(partIDLabel, 0, 1);
        partsGridPane.add(partIDTextField,1, 1 );
        partsGridPane.add(partNameLabel, 0,2);
        partsGridPane.add(partNameTextField, 1,2);
        partsGridPane.add(partInventoryLevelLabel, 0,3);
        partsGridPane.add(partInventoryTextField, 1,3);
        partsGridPane.add(partPriceLabel, 0,4);
        partsGridPane.add(partPriceTextField, 1,4);
        partsGridPane.add(partMinLabel, 0,5);
        partsGridPane.add(partMinTextField, 1, 5);
        partsGridPane.add(partMaxLabel, 0, 6);
        partsGridPane.add(partMaxTextField, 1, 6);
        partsGridPane.add(partSourceLabel, 0, 7);
        partsGridPane.add(partSourceTextField, 1, 7);
        partsGridPane.add(saveBtn, 0, 8);
        partsGridPane.add(cancelBtn, 1, 8);

        Stage stage1 = new Stage();
        Scene scene = new Scene(partsGridPane, 400, 300);
        stage1.setScene(scene);

        stage1.setTitle("Add Parts");
        stage1.show();


        cancelBtn.setOnAction(actionEvent -> {
            stage1.close();
        });


        saveBtn.setOnAction(actionEvent -> {


            try {
                Integer partInvInput = Integer.parseInt(partInventoryTextField.getText());

                Integer partIDInput = generatePartID();
                String partNameInput = partNameTextField.getText();
                Double partPriceInput = Double.valueOf(partPriceTextField.getText());
                Integer partMinInput = Integer.valueOf(partMinTextField.getText());
                Integer partMaxInput = Integer.valueOf(partMaxTextField.getText());

                // Add the part to the list only if the input is valid
                if (partInHouseBtn.isSelected()) {

                    if (partInvInput <= partMaxInput && partInvInput >= partMinInput){
                        InHouse inHouse = new InHouse(partIDInput, partNameInput, partPriceInput, partInvInput, partMinInput, partMaxInput, 5 );
                        inHouse.setMachineID(Integer.parseInt(partSourceTextField.getText()));
                        inventory.addPart(inHouse);

                        partTableView.setItems(inventory.getAllParts());
                        partTableView.refresh();
                        stage1.close();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a value that is within the min-max");
                        alert.showAndWait();

                    }

                } else if (partOutsourcedBtn.isSelected()) {


                    if (partInvInput <= partMaxInput && partInvInput >= partMinInput){
                        Outsourced outsourced = new Outsourced(partIDInput, partNameInput, partPriceInput, partInvInput, partMinInput, partMaxInput, "HI");
                        outsourced.setCompanyName(partSourceTextField.getText());
                        inventory.addPart(outsourced);

                        partTableView.setItems(inventory.getAllParts());
                        partTableView.refresh();
                        stage1.close();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a value that is within the min-max");
                        alert.showAndWait();

                    }

                }




            } catch (NumberFormatException exception) {
                // Handle the case when the input is not a valid integer
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid integer");
                alert.showAndWait();
            }








        });
    }

    /**
     *
     * @param event When a user clicks on the part modify button a form will be populated with the data that the user selected for modification.
     */

    @FXML
    public void partModifyBtn(ActionEvent event) {

        // Create the modification window
        Stage modifyStage = new Stage();
        modifyStage.setTitle("Modify Part");

        // Retrieve the selected row from the TableView
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {

            return;
        }

        // radio button for outsource and inhouse
        RadioButton partOutsourcedBtn = new RadioButton("Outsourced");
        RadioButton partInHouseBtn = new RadioButton("In-House");

        // label for parts
        Label partIDLabel = new Label("Part ID");
        Label partNameLabel = new Label("Part Name");
        Label partInventoryLevelLabel = new Label("Inventory Level");
        Label partPriceLabel = new Label("Price");
        Label partMinLabel = new Label("min");
        Label partMaxLabel = new Label("max");
        Label partSourceLabel = new Label("Machine ID");


        // textfield for parts
        TextField partIDTextField = new TextField();
        partIDTextField.setEditable(false);
        partIDTextField.setPromptText("Auto Gen");
        TextField partNameTextField = new TextField();
        TextField partInventoryTextField = new TextField();
        TextField partPriceTextField = new TextField();
        TextField partMinTextField = new TextField();
        TextField partMaxTextField = new TextField();
        TextField partSourceTextField = new TextField();


        ToggleGroup toggleGroup = new ToggleGroup();
        partOutsourcedBtn.setToggleGroup(toggleGroup);
        partInHouseBtn.setToggleGroup(toggleGroup);
        partInHouseBtn.setSelected(true);


        // Set the initial selected state based on the selectedPart
        if (selectedPart instanceof InHouse) {
            partInHouseBtn.setSelected(true);
            partSourceLabel.setText("Machine ID");
            partSourceTextField.setText(Integer.toString(((InHouse) selectedPart).getMachineID()));
        } else if (selectedPart instanceof Outsourced) {
            partOutsourcedBtn.setSelected(true);
            partSourceLabel.setText("Company Name");
            partSourceTextField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        partIDTextField.setText(String.valueOf(selectedPart.getId()));
        partNameTextField.setText(selectedPart.getName());
        partInventoryTextField.setText(String.valueOf(selectedPart.getStock()));
        partPriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        partMinTextField.setText(String.valueOf(selectedPart.getMin()));
        partMaxTextField.setText(String.valueOf(selectedPart.getMax()));




        partOutsourcedBtn.setOnAction(actionEvent -> {
            if (partOutsourcedBtn.isSelected()){
                partSourceLabel.setText("Company Name");
            }
        });


        partInHouseBtn.setOnAction(actionEvent -> {
            if (partInHouseBtn.isSelected()) {

                partSourceLabel.setText("Machine ID");
            }
        });

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");




        // gridpane that holds label and textfields together
        GridPane partsGridPane = new GridPane();
        partsGridPane.add(partOutsourcedBtn, 0, 0);
        partsGridPane.add(partInHouseBtn, 1, 0);
        partsGridPane.add(partIDLabel, 0, 1);
        partsGridPane.add(partIDTextField,1, 1 );
        partsGridPane.add(partNameLabel, 0,2);
        partsGridPane.add(partNameTextField, 1,2);
        partsGridPane.add(partInventoryLevelLabel, 0,3);
        partsGridPane.add(partInventoryTextField, 1,3);
        partsGridPane.add(partPriceLabel, 0,4);
        partsGridPane.add(partPriceTextField, 1,4);
        partsGridPane.add(partMinLabel, 0,5);
        partsGridPane.add(partMinTextField, 1, 5);
        partsGridPane.add(partMaxLabel, 0, 6);
        partsGridPane.add(partMaxTextField, 1, 6);
        partsGridPane.add(partSourceLabel, 0, 7);
        partsGridPane.add(partSourceTextField, 1, 7);
        partsGridPane.add(saveBtn, 0, 8);
        partsGridPane.add(cancelBtn, 1, 8);

        Scene modifyScene = new Scene(partsGridPane, 400, 300);
        modifyStage.setScene(modifyScene);



        cancelBtn.setOnAction(actionEvent -> {
            modifyStage.close();
        });


        saveBtn.setOnAction(actionEvent -> {





            try {
                Integer partInvInput = Integer.parseInt(partInventoryTextField.getText());

                Integer partIDInput = Integer.valueOf(partIDTextField.getText());
                String partNameInput = partNameTextField.getText();
                Double partPriceInput = Double.valueOf(partPriceTextField.getText());
                Integer partMinInput = Integer.valueOf(partMinTextField.getText());
                Integer partMaxInput = Integer.valueOf(partMaxTextField.getText());

                selectedPart.setId(partIDInput);
                selectedPart.setName(partNameInput);
                selectedPart.setStock(partInvInput);
                selectedPart.setPrice(partPriceInput);
                selectedPart.setMin(partMinInput);
                selectedPart.setMax(partMaxInput);

                // Add the part to the list only if the input is valid


                if (partInHouseBtn.isSelected()) {

                    if (partInvInput <= partMaxInput && partInvInput >= partMinInput){
                        InHouse inHouse = new InHouse(partIDInput, partNameInput, partPriceInput, partInvInput, partMinInput, partMaxInput, 5 );
                        inHouse.setMachineID(Integer.parseInt(partSourceTextField.getText()));
                        inventory.updatePart(inventory.lookupPart(partIDInput).getId(), inHouse);

                        partTableView.setItems(inventory.getAllParts());
                        partTableView.refresh();
                        modifyStage.close();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a value that is within the min-max");
                        alert.showAndWait();

                    }

                } else if (partOutsourcedBtn.isSelected()) {


                    if (partInvInput <= partMaxInput && partInvInput >= partMinInput){
                        Outsourced outsourced = new Outsourced(partIDInput, partNameInput, partPriceInput, partInvInput, partMinInput, partMaxInput, "HI");
                        outsourced.setCompanyName(partSourceTextField.getText());
                        inventory.updatePart(inventory.lookupPart(partIDInput).getId(), outsourced);

                        partTableView.setItems(inventory.getAllParts());
                        partTableView.refresh();
                        modifyStage.close();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a value that is within the min-max");
                        alert.showAndWait();

                    }

                }






            } catch (NumberFormatException exception) {
                // Handle the case when the input is not a valid integer
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid integer");
                alert.showAndWait();
            }






        });

        modifyStage.show();
    }

    /**
     *
     * @param event When a user clicks on the part delete button it will delete the selected row.
     */

    @FXML
    public void partDeleteBtn(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to proceed?");

        // Show and wait for the user to click a button
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                if (selectedPart != null) {
                    inventory.deletePart(selectedPart);
                }
            } else {
                // User clicked Cancel, do nothing or handle the cancellation
                System.out.println("Cancelled");
            }
        });


    }

    /**
     * RUNTIME ERROR.
     * the loader.load method throws a 'java.io.IOException' and since it is a checked exception it needs to be handled. I fixed this by adding the 'throws IOException' in the method signature.
     * @param event used to retrieve a user click. Product add button will load the product scene to the view for users to input product data.
     * @throws IOException required when loading in the productpane.fxml file
     */

    public void productAddBtn(ActionEvent event) throws IOException{


        // loads in the productpane.fxml view

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/inventorymanagementsystem/productpane.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Label productLabel = (Label) loader.getNamespace().get("productLabel");
        productLabel.setText("Add Product");

        TextField searchPartTextField = (TextField) loader.getNamespace().get("searchProductPart");

        filteredParts = new FilteredList<>(inventory.getAllParts(), p -> true);

        searchPartTextField.setPromptText("Search by Part ID or Name");
        searchPartTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();
                return part.getName().toLowerCase().contains(searchText) || String.valueOf(part.getId()).contains(searchText);
            });
            productPartTableView.setItems(filteredParts);
        });

        TableView<Part> userPartTableView = (TableView<Part>) root.lookup("#userPartTableView");
        userPartTableView.setItems(partList);


        TableView<Part> productPartTableView = (TableView<Part>) root.lookup("#productPartTableView");
        productPartTableView.setItems(inventory.getAllParts());

        // Access the userPartTableView columns directly
        TableColumn<Part, Integer> userPartIDColumn = (TableColumn<Part, Integer>) userPartTableView.getColumns().get(0);
        TableColumn<Part, String> userPartNameColumn = (TableColumn<Part, String>) userPartTableView.getColumns().get(1);
        TableColumn<Part, Integer> userPartInventory = (TableColumn<Part, Integer>) userPartTableView.getColumns().get(2);
        TableColumn<Part, Double> userPartPrice = (TableColumn<Part, Double>) userPartTableView.getColumns().get(3);

        // Set cell value factories for the columns
        userPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        userPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        userPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));



        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.show();
        productIDTextField.setEditable(false);
        productIDTextField.setPromptText("Auto Gen");

        Button productSaveBtn = (Button) loader.getNamespace().get("productSaveBtn");
        Button productCancelBtn = (Button) loader.getNamespace().get("productCancelBtn");
        Button productPartAddBtn = (Button) loader.getNamespace().get("productPartAddBtn");
        Button removePartBtn = (Button) loader.getNamespace().get("removePartBtn");


        removePartBtn.setOnAction(event1 -> {
            Part selectedPart = userPartTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Do you want to proceed?");

            // Show and wait for the user to click a button
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (selectedPart != null) {
                        partList.remove(selectedPart);
                    }
                } else {
                    // User clicked Cancel, do nothing or handle the cancellation
                    System.out.println("Cancelled");
                }
            });
        });


        productPartAddBtn.setOnAction(event1 -> {
            Part selectedPart = productPartTableView.getSelectionModel().getSelectedItem();

            if (selectedPart != null) {
                // Add the selected part to the userPartTableView and selectedProduct's associatedParts
                userPartTableView.getItems().add(selectedPart);

            } else {
                // Handle the case when selectedProduct is null
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Product Selected");
                alert.setHeaderText("Please select a product before adding parts.");
                alert.showAndWait();
            }

        });




        productCancelBtn.setOnAction(event1 -> {
            stage.close();
        });

        productSaveBtn.setOnAction(event1 -> {
            try {
                Integer partIDInput = generatePartID();
                Integer productInvInput = Integer.parseInt(productInventoryTextField.getText());
                String productNameInput = productNameTextField.getText();
                Double productPriceInput = Double.valueOf(productPriceTextField.getText());
                Integer productMinInput = Integer.valueOf(productMinTextField.getText());
                Integer productMaxInput = Integer.valueOf(productMaxTextField.getText());

                // Add the part to the list only if the input is valid

                if (productInvInput <= productMaxInput && productInvInput >= productMinInput){
                    Product product = new Product(partIDInput, productNameInput, productPriceInput, productInvInput, productMinInput, productMaxInput);

                    product.setAssociatedParts(userPartTableView.getItems());
                    inventory.addProduct(product);
                    productTableView.setItems(inventory.getAllProducts());
                    productTableView.refresh();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter a value that is within the min-max");
                    alert.showAndWait();

                }



            } catch (NumberFormatException exception) {
                // Handle the case when the input is not a valid integer
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid integer");
                alert.showAndWait();
            }
        });


    }


    /**
     *
     * @param event used to retrieve a user click. Product modify button will load the product scene to the view with the user selected data for modification.
     * @throws IOException required when loading in the productpane.fxml file
     */

    @FXML
    public void productModifyBtn(ActionEvent event) throws IOException {
        // Retrieve the selected row from the TableView
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            // Handle the case when no product is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("Please select a product to modify.");
            alert.showAndWait();
            return;
        }


        // Load the FXML file for the product pane
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/inventorymanagementsystem/productpane.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Label productLabel = (Label) loader.getNamespace().get("productLabel");
        productLabel.setText("Modify Product");

        TextField searchPartTextField = (TextField) loader.getNamespace().get("searchProductPart");

        filteredParts = new FilteredList<>(inventory.getAllParts(), p -> true);

        searchPartTextField.setPromptText("Search by Part ID or Name");
        searchPartTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();
                return part.getName().toLowerCase().contains(searchText) || String.valueOf(part.getId()).contains(searchText);
            });
            productPartTableView.setItems(filteredParts);
        });

        TableView<Part> userPartTableView = (TableView<Part>) root.lookup("#userPartTableView");
        userPartTableView.setItems(selectedProduct.getAssociatedParts());

        TableColumn<Part, Integer> userPartIDColumn = (TableColumn<Part, Integer>) userPartTableView.getColumns().get(0);
        TableColumn<Part, String> userPartNameColumn = (TableColumn<Part, String>) userPartTableView.getColumns().get(1);
        TableColumn<Part, Integer> userPartInventory = (TableColumn<Part, Integer>) userPartTableView.getColumns().get(2);
        TableColumn<Part, Double> userPartPrice = (TableColumn<Part, Double>) userPartTableView.getColumns().get(3);

        // After creating the columns, set the cell value
        userPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        userPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        userPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableView<Part> productPartTableView = (TableView<Part>) root.lookup("#productPartTableView");




        productPartTableView.setItems(inventory.getAllParts());
        Stage stage = new Stage();
        stage.setTitle("Modify Product"); // Set a relevant title for the stage
        productIDTextField.setEditable(false);

        // Get references to the existing TextFields in the FXML
        productIDTextField = (TextField) loader.getNamespace().get("productIDTextField");
        productNameTextField = (TextField) loader.getNamespace().get("productNameTextField");
        productInventoryTextField = (TextField) loader.getNamespace().get("productInventoryTextField");
        productPriceTextField = (TextField) loader.getNamespace().get("productPriceTextField");
        productMinTextField = (TextField) loader.getNamespace().get("productMinTextField");
        productMaxTextField = (TextField) loader.getNamespace().get("productMaxTextField");

        // Populate the TextFields with the data from the selected product
        productIDTextField.setText(String.valueOf(selectedProduct.getId()));
        productNameTextField.setText(selectedProduct.getName());
        productInventoryTextField.setText(String.valueOf(selectedProduct.getStock()));
        productPriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
        productMinTextField.setText(String.valueOf(selectedProduct.getMin()));
        productMaxTextField.setText(String.valueOf(selectedProduct.getMax()));

        Button productCancelBtn = (Button) loader.getNamespace().get("productCancelBtn");

        productCancelBtn.setOnAction(event1 -> {
            stage.close();
        });

        Button productPartAddBtn = (Button) loader.getNamespace().get("productPartAddBtn");


        productPartAddBtn.setOnAction(event1 -> {
            Part selectedPart = productPartTableView.getSelectionModel().getSelectedItem();

            if (selectedPart != null) {
                // Add the selected part to the userPartTableView and selectedProduct's associatedParts
                userPartTableView.getItems().add(selectedPart);

            } else {
                // Handle the case when selectedProduct is null
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Product Selected");
                alert.setHeaderText("Please select a product before adding parts.");
                alert.showAndWait();
            }

        });

        Button removePartBtn = (Button) loader.getNamespace().get("removePartBtn");

        removePartBtn.setOnAction(event1 -> {
            Part selectedPart = userPartTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Do you want to proceed?");

            // Show and wait for the user to click a button
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (selectedPart != null) {
                        partList.remove(selectedPart);
                    }
                } else {
                    // User clicked Cancel, do nothing or handle the cancellation
                    System.out.println("Cancelled");
                }
            });
        });


        // Retrieve the Save button from the FXML and set its action
        Button productSaveBtn = (Button) loader.getNamespace().get("productSaveBtn");
        productSaveBtn.setOnAction(event1 -> {
            try {
                // Get the modified data from the TextFields
                Integer productIDInput = Integer.valueOf(productIDTextField.getText());
                Integer productInvInput = Integer.parseInt(productInventoryTextField.getText());
                String productNameInput = productNameTextField.getText();
                Double productPriceInput = Double.valueOf(productPriceTextField.getText());
                Integer productMinInput = Integer.valueOf(productMinTextField.getText());
                Integer productMaxInput = Integer.valueOf(productMaxTextField.getText());




                if (productInvInput <= productMaxInput && productInvInput >= productMinInput){
                    // Update the selected product with the modified data

                    selectedProduct.setId(productIDInput);
                    selectedProduct.setName(productNameInput);
                    selectedProduct.setStock(productInvInput);
                    selectedProduct.setPrice(productPriceInput);
                    selectedProduct.setMin(productMinInput);
                    selectedProduct.setMax(productMaxInput);


                    inventory.updateProduct(inventory.lookupProduct(productIDInput).getId(), selectedProduct);

                    productTableView.setItems(inventory.getAllProducts());
                    productTableView.refresh();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter a value that is within the min-max");
                    alert.showAndWait();

                }







            } catch (NumberFormatException exception) {
                // Handle the case when the input is not a valid integer
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid integer");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     *
     * @param event used to retrieve a user click. Product delete button will delete the selected row.
     */

    @FXML
    public void productDeleteBtn(ActionEvent event) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to proceed?");

        // Show and wait for the user to click a button
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                if (selectedProduct != null) {
                    inventory.deleteProduct(selectedProduct);
                }
            } else {
                // User clicked Cancel, do nothing or handle the cancellation
                System.out.println("Cancelled");
            }
        });
    }

    /**
     * LOGICAL ERROR
     * returned the same id and the cause was I forgot to add the prepend ++ to the variable. After fixing this the id was be incremented correctly.
     * @return a user generated id for the specific item.
     */
    public static int generatePartID(){

        return ++assignedPartID;
    }







}

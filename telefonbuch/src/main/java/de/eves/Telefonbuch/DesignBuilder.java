package de.eves.Telefonbuch;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author mbittermann
 *
 */
public class DesignBuilder extends Main {

	/**
	 * all variables declaration
	 */
	private Label firmaLabel, vornamelabel, nachnameLabel, pflichtFeld, telefonLabel, adresseLabel, strassenLabel,
			hausnummerLabel, plzLabel, stadtLabel;
	static TextField firmaTextField, vornameTextField, nachnameTextField, phoneTextField, adressTextField,
			strassenTextField, hausnummerTextField, plzTextField, stadtTextField;

	private Button save;
	static VBox linkeBox;
	private static VBox PhoneContent;
	static Pane space;

	static Accordion acc;
	static TitledPane tp;
	private TableColumn<Contact, String> companyName, firstName, surname, phone, street, haus, plz, stadt;

	private static TableColumn<Contact, String> address;
	private static ObservableList<Contact> datas = FXCollections.observableArrayList();
	private static ObservableList<Contact> contactData = FXCollections.observableArrayList();
	private TableView<Contact> tableView;
	static TitledPane adressTitlePane;
	private List<TextField> list;
	private List<TextField> numberList = new ArrayList<>();
	private List<TextField> adressenList = new ArrayList<>();
	private List<TextField> checkAdressList = new ArrayList<>();
	private List<VBox> vboxList = new ArrayList<>();
	VBox adressCotent;

	/**
	 * TODO
	 */
	public static void addToTable() {

		for (Contact contact : ContactDataHandler.getContacts()) {

//			String[] cutmehrereAdressen = contact.getAddressen().split("\\|");
//			for (String string : cutmehrereAdressen) {
//				String[] einzelneAdresse = string.split(",");

			datas.remove(contact);
			datas.add(contact);

		}

	}

	/**
	 * 
	 * @param primaryStage
	 */
	@SuppressWarnings("unchecked")
	public void buildDesign(Stage primaryStage) {
		addToTable();

		// root
		BorderPane links = new BorderPane();
		BorderPane ground = new BorderPane();
		HBox hbox = new HBox();
		VBox firmaEingabe = new VBox();
		VBox vornameEingabe = new VBox();
		VBox nachnameEingabe = new VBox();
		VBox phoneEingabe = new VBox();
		VBox adresseEingabe = new VBox();
		VBox strassenEingabe = new VBox();
		VBox hausnummerEingabe = new VBox();
		VBox plzEingabe = new VBox();
		VBox stadtEingabe = new VBox();
		HBox savebtn = new HBox();
		hbox.setPrefHeight(600);
		hbox.setPrefWidth(1000);
		ground.setCenter(hbox);

		// links
		save = new Button("save");
		save.setOpacity(1);
		save.setStyle("-fx-background-color: #123456");
		save.setStyle("-fx-font-color: #ffffff");

		space = new Pane();
		HBox.setHgrow(space, Priority.ALWAYS);
		space.setMinSize(10, 1);
		savebtn.getChildren().addAll(space, save);
		linkeBox = new VBox();

		/**
		 * input fields
		 */
		firmaLabel = new Label("Enter Company (OPTIONAL)");
		firmaTextField = new TextField();
		firmaEingabe.getChildren().addAll(firmaLabel, firmaTextField);
		vornamelabel = new Label("Enter Vorname*");
		vornameTextField = new javafx.scene.control.TextField();
		vornameEingabe.getChildren().addAll(vornamelabel, vornameTextField);
		nachnameLabel = new Label("Enter Name*");
		nachnameTextField = new javafx.scene.control.TextField();
		nachnameEingabe.getChildren().addAll(nachnameLabel, nachnameTextField);
		pflichtFeld = new Label("*Pflichtfelder");
		telefonLabel = new Label("Enter Phone");
		phoneTextField = new TextField();
		phoneEingabe.getChildren().addAll(telefonLabel, phoneTextField);
		adresseLabel = new Label("Enter address");
		adressTextField = new TextField();
		adresseEingabe.getChildren().addAll(adresseLabel, adressTextField);
		strassenLabel = new Label("Enter Street");
		strassenTextField = new TextField();
		strassenEingabe.getChildren().addAll(strassenLabel, strassenTextField);
		hausnummerLabel = new Label("Enter housenumber");
		hausnummerTextField = new TextField();
		hausnummerEingabe.getChildren().addAll(hausnummerLabel, hausnummerTextField);
		plzLabel = new Label("Enter ZIP");
		plzTextField = new TextField();
		plzEingabe.getChildren().addAll(plzLabel, plzTextField);
		stadtLabel = new Label("Enter City");
		stadtTextField = new TextField();

		stadtEingabe.getChildren().addAll(stadtLabel, stadtTextField);

		acc = new Accordion();

		PhoneContent = new VBox();
		adressCotent = new VBox();
		tp = new TitledPane();
		ScrollPane phoneScrollPane = new ScrollPane();

		adressTitlePane = new TitledPane();
		tp.setText("Name und Phone");
		adressTitlePane.setText("Adressen");

		VBox nameBox = new VBox();
		nameBox.getChildren().addAll(vornameEingabe, nachnameEingabe);

		PhoneContent.getChildren().addAll(phoneEingabe);
		VBox nameBoxAndPhoneBox = new VBox();
		phoneScrollPane.setContent(PhoneContent);
		nameBoxAndPhoneBox.getChildren().addAll(nameBox, phoneScrollPane);

		/**
		 * scrollPane VALUES
		 */
		phoneScrollPane.setMaxHeight(Double.MAX_VALUE);
		phoneScrollPane.setMaxWidth(Double.MAX_VALUE);
		phoneScrollPane.setMinHeight(Region.USE_COMPUTED_SIZE);
		phoneScrollPane.setMinWidth(Region.USE_COMPUTED_SIZE);
		phoneScrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
		phoneScrollPane.setPrefWidth(Region.USE_COMPUTED_SIZE);

		/**
		 * TitledPane (Name and Phone VALUES)
		 */
		tp.setMaxHeight(Double.MAX_VALUE);
		tp.setMaxWidth(Double.MAX_VALUE);
		tp.setPrefHeight(Region.USE_COMPUTED_SIZE);
		tp.setPrefWidth(Region.USE_COMPUTED_SIZE);
		tp.setMinHeight(Region.USE_COMPUTED_SIZE);
		tp.setMinWidth(Region.USE_COMPUTED_SIZE);

		/**
		 * Accordion Values
		 */
		acc.setMaxHeight(Double.MAX_VALUE);
		acc.setMaxWidth(Double.MAX_VALUE);
		acc.setPrefHeight(Region.USE_COMPUTED_SIZE);
		acc.setPrefWidth(Region.USE_COMPUTED_SIZE);
		acc.setMinHeight(Region.USE_COMPUTED_SIZE);
		acc.setMinWidth(Region.USE_COMPUTED_SIZE);

		adressCotent.getChildren().addAll(strassenLabel, strassenTextField, hausnummerLabel, hausnummerTextField,
				plzLabel, plzTextField, stadtLabel, stadtTextField);
		adressTitlePane.setContent(adressCotent);
		tp.setContent(nameBoxAndPhoneBox);
		acc.getPanes().addAll(tp, adressTitlePane);

		linkeBox.getChildren().addAll(firmaEingabe, acc, savebtn);

		/**
		 * event for adding new TextFields if user does an input
		 */
		phoneTextField.textProperty().addListener(listenerHandling());

		for (Node tf : adressCotent.getChildren()) {
			if (tf instanceof TextField) {
				TextField tfield = (TextField) tf;
				tfield.focusedProperty().addListener(listener());
			}
		}

		/**
		 * creating columns for the Table
		 */
		companyName = new TableColumn<>("Firma");
//		companyName.setCellValueFactory(new PropertyValueFactory<>("noCompany"));
		companyName.setMinWidth(100);
		companyName.setCellValueFactory(cell -> {
			if (!cell.getValue().getNoCompany().isEmpty()) {
				return cell.getValue().firmaProperty();
			} else {
				return new SimpleStringProperty("");
			}
		});
		firstName = new TableColumn<>("Vorname");
//		firstName.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		firstName.setMinWidth(100);
		firstName.setCellValueFactory(cell -> {
			if (!cell.getValue().getVorname().isEmpty()) {
				return cell.getValue().vornameProperty();
			} else {
				return new SimpleStringProperty("");
			}
		});
		surname = new TableColumn<>("Nachname");
//		surname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		surname.setMinWidth(100);
		surname.setCellValueFactory(cell -> {
			if (!cell.getValue().getNachname().isEmpty()) {
				return cell.getValue().nachnameProperty();
			} else {
				return new SimpleStringProperty("");
			}
		});
		phone = new TableColumn<>("Telefon");
		phone.setCellValueFactory(cell -> {
			if (cell.getValue().getPhoneNumberArray().size() >= 1) {
				return cell.getValue().getPhoneNumberArray().get(0).getTeleNr();
			} else {
				return new SimpleStringProperty("");
			}
		});

		phone.setMinWidth(100);

//		address = new TableColumn<>("Adresse");
//
//		/**
//		 * setting up the values for each column
//		 */
//		address.setCellValueFactory(cell -> {
//			return cell.getValue().getAdressen().get(0).getStrasseProp();
//		});
//		address.setMinWidth(300);

		street = new TableColumn<>("Strasse");
		street.setCellValueFactory(cell -> {
			if (cell.getValue().getAdressen().size() >= 1) {
				return cell.getValue().getAdressen().get(0).getStrasseProp();
			} else {
				return new SimpleStringProperty("");
			}
		});
		street.setMinWidth(75);

		haus = new TableColumn<>("Haus");
		haus.setCellValueFactory(cell -> {
			if (cell.getValue().getAdressen().size() >= 1) {
				return cell.getValue().getAdressen().get(0).getHausnummerProp();
			} else {
				return new SimpleStringProperty("");
			}
		});
		haus.setMinWidth(75);

		plz = new TableColumn<>("PLZ");
		plz.setCellValueFactory(cell -> {
			if (cell.getValue().getAdressen().size() >= 1) {
				return cell.getValue().getAdressen().get(0).getPostleitzahlProp();
			} else {
				return new SimpleStringProperty("");
			}
		});
		plz.setMinWidth(75);

		stadt = new TableColumn<>("Stadt");
		stadt.setCellValueFactory(cell -> {
			if (cell.getValue().getAdressen().size() >= 1) {
				return cell.getValue().getAdressen().get(0).getStadtProp();
			} else {
				return new SimpleStringProperty("");
			}
		});
		stadt.setMinWidth(75);

		/**
		 * creating Table
		 */
		tableView = new TableView<>();
		tableView.setEditable(true);

		tableView.getColumns().addAll(companyName, firstName, surname, phone, street, haus, plz, stadt);

//		tableView.getItems().addAll(datas);
		tableView.setItems(datas);

		// toolBars
		Label toolBarLabel = new Label("Toolbar");
		ToolBar toolBar = new ToolBar();
		toolBar.setPrefHeight(50);
		toolBar.setPrefWidth(1000);
		toolBar.setOrientation(Orientation.HORIZONTAL);
		toolBar.setStyle("-fx-background-color: lightcyan");
		toolBar.getItems().add(toolBarLabel);
		ground.setTop(toolBar);

		/**
		 * show Toolbar
		 */
		Label showToolbarLabel = new Label("SHOW");
		Button senseless = new Button("senseless Button");
		ToolBar showToolbar = new ToolBar();
		showToolbar.setPrefHeight(20);
		showToolbar.setPrefWidth(700);
		showToolbar.setOrientation(Orientation.HORIZONTAL);
		showToolbar.setStyle("-fx-background-color: silver");
		showToolbar.getItems().add(showToolbarLabel);
		showToolbar.getItems().add(senseless);

		/**
		 * Edit Toolbar
		 */
		Label editToolbarLabel = new Label("EDIT");
		Button anotherSenseless = new Button("senseless Button");
		ToolBar editToolbar = new ToolBar();
		editToolbar.setPrefHeight(20);
		editToolbar.setPrefWidth(700);
		editToolbar.setOrientation(Orientation.HORIZONTAL);
		editToolbar.setStyle("-fx-background-color: silver");
		editToolbar.getItems().add(editToolbarLabel);
		editToolbar.getItems().add(anotherSenseless);

		/**
		 * search Toolbar
		 */
		Label searchToolbarLabel = new Label("SEARCH");
		Button test = new Button("senseless Button");
		ToolBar searchToolbar = new ToolBar();
		searchToolbar.setPrefHeight(20);
		searchToolbar.setPrefWidth(700);
		searchToolbar.setOrientation(Orientation.HORIZONTAL);
		searchToolbar.setStyle("-fx-background-color: silver");
		searchToolbar.getItems().add(searchToolbarLabel);
		searchToolbar.getItems().add(test);

		/**
		 * BorderPanes
		 */
		BorderPane borderPaneShow = new BorderPane();
		borderPaneShow.setTop(showToolbar);

		borderPaneShow.setCenter(tableView);
		BorderPane borderPaneEdit = new BorderPane();
		borderPaneEdit.setTop(editToolbar);
		BorderPane borderPaneSearch = new BorderPane();
		borderPaneSearch.setTop(searchToolbar);

		primaryStage.setTitle("Arztpraxis Bittermann");
		TabPane tabPane = new TabPane();
		tabPane.setPrefHeight(600);
		tabPane.setPrefWidth(700);

		/**
		 * creating Tabs for TabPane
		 */
		Tab show = new Tab();

		Tab edit = new Tab();
		Tab search = new Tab();
		search.setText("Suchen");
		edit.setText("Edit/Delete");
		show.setText("Ansicht");

		/**
		 * styling the BorderPane
		 */
		borderPaneEdit.setPrefHeight(600);
		borderPaneEdit.setPrefWidth(700);
		borderPaneEdit.setStyle("-fx-background-color: cyan");
		borderPaneShow.setStyle("-fx-background-color: lightblue");
		borderPaneShow.setPrefHeight(600);
		borderPaneShow.setPrefWidth(700);

		borderPaneSearch.setPrefHeight(600);
		borderPaneSearch.setPrefWidth(700);
		borderPaneSearch.setStyle("-fx-background-color: lightcyan");

		/**
		 * setting up TabPanes
		 */
		edit.setContent(borderPaneEdit);
		show.setContent(borderPaneShow);
		search.setContent(borderPaneSearch);
		tabPane.getTabs().add(show);
		tabPane.getTabs().add(edit);
		tabPane.getTabs().add(search);

		/**
		 * set up closable false, that the user cant close tabs
		 */
		show.setClosable(false);
		edit.setClosable(false);
		search.setClosable(false);

		linkeBox.setPrefHeight(600);
		linkeBox.setPrefWidth(300);
		linkeBox.setStyle("-fx-background-color: lightblue");
		tabPane.setStyle("-fx-background-color: grey");
		links.setCenter(linkeBox);
		links.setBottom(pflichtFeld);
		hbox.getChildren().add(links);
		hbox.getChildren().add(tabPane);
		hbox.setHgrow(tabPane, Priority.ALWAYS);
		Scene scene = new Scene(ground, 1200, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public VBox getAdressCotent() {
		return adressCotent;
	}

	/**
	 * checks whether a textfield is empty or not
	 * 
	 * @return
	 */
	public boolean isfieldsEmpty() {
		for (TextField textField : checkAdressList) {
			if (!textField.getText().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * if the user inputs a value in on of the textfields a new titledpane with
	 * inputfields for adress will open if the user deletes input it deletes the
	 * titledpane
	 * 
	 * @return
	 */
	private ChangeListener<Boolean> listener() {

		return (bser, old, newvalue) -> {
			if (!newvalue) {
				List<Node> tpanes = new ArrayList<>();
				for (Node tPane : acc.getPanes()) {
					if (tPane instanceof TitledPane) {
						TitledPane newTitledPane = (TitledPane) tPane;
						if (!newTitledPane.getText().equals("Name und Phone")) {
							Node node = newTitledPane.getContent();

							if (node instanceof VBox) {
								boolean isEmpty = false;
								for (Node node2 : ((VBox) node).getChildren()) {
									if (node2 instanceof TextField) {
										TextField textField = (TextField) node2;

										if (!textField.getText().isEmpty())
											isEmpty = true;

									}
								}
								if (!isEmpty) {
									tpanes.add(newTitledPane);

								}
							}
						}
					}
				}
				acc.getPanes().removeAll(tpanes);
				for (Node node : tpanes) {
					if (node instanceof TitledPane) {
						vboxList.remove(((TitledPane) node).getContent());
					}
				}
			}
			int paneSize = acc.getPanes().size();
			if (!doesEmptyAdressExist() || paneSize < 2) {

				{
					TitledPane titledPane = new TitledPane();
					titledPane.setText("Adresse");
					titledPane.setMaxHeight(Double.MAX_VALUE);
					titledPane.setMaxWidth(Double.MAX_VALUE);
					titledPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
					titledPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
					titledPane.setMinHeight(Region.USE_COMPUTED_SIZE);
					titledPane.setMinWidth(Region.USE_COMPUTED_SIZE);

					VBox contactInfoBox = new VBox();
					Label streetL = new Label("Strasse");

					TextField streetF = new TextField();
					Label houseL = new Label("Hausnummer");
					TextField houseTF = new TextField();
					Label plzL = new Label("Plz");
					TextField plzF = new TextField();
					Label stadtL = new Label("Stadt");
					TextField stadtF = new TextField();
					streetF.focusedProperty().addListener(listener());
					houseTF.focusedProperty().addListener(listener());
					plzF.focusedProperty().addListener(listener());
					stadtF.focusedProperty().addListener(listener());
					contactInfoBox.getChildren().addAll(streetL, streetF, houseL, houseTF, plzL, plzF, stadtL, stadtF);
					titledPane.setContent(contactInfoBox);
					vboxList.add(contactInfoBox);
					checkAdressList.add(streetF);
					checkAdressList.add(houseTF);
					checkAdressList.add(plzF);
					checkAdressList.add(stadtF);
					acc.getPanes().addAll(titledPane);
					if (paneSize < 2)
						acc.setExpandedPane(titledPane);
				}
			}
		};
	}

	public List<VBox> getVboxList() {
		return vboxList;
	}

	private ChangeListener<String> listenerHandling() {
		return new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty()) {
					list = new ArrayList<>();
					for (Node textnode : PhoneContent.getChildren()) {
						if (textnode instanceof TextField) {
							TextField tf = (TextField) textnode;

							if (tf.getText().isEmpty()) {
								list.add(tf);

							}
						}
					}
					PhoneContent.getChildren().removeAll(list);

				} else {
					boolean isValid = false;
					for (Node textNode : PhoneContent.getChildren()) {
						if (textNode instanceof TextField) {
							TextField tf = (TextField) textNode;
							if (tf.getText().isEmpty()) {
								isValid = true;

							}
						}
					}
					if (!isValid) {
						TextField textField = new TextField();
						textField.textProperty().addListener(listenerHandling());
						PhoneContent.getChildren().addAll(textField);
						numberList.add(textField);

					}
				}

			}
		};
	}

	private boolean doesEmptyAdressExist() {
		for (Node tPane : acc.getPanes()) {
			if (tPane instanceof TitledPane) {
				TitledPane newTitledPane = (TitledPane) tPane;
				if (!newTitledPane.getText().equals("Name und Phone")) {
					Node node = newTitledPane.getContent();

					if (node instanceof VBox) {
						boolean isEmpty = false;
						for (Node node2 : ((VBox) node).getChildren()) {
							if (node2 instanceof TextField) {
								TextField textField = (TextField) node2;
								if (!textField.getText().isEmpty()) {
									isEmpty = true;
									break;
								}

							}
						}
						if (!isEmpty) {
							return true;
						}

					}
				}
			}
		}
		return false;
	}

	private ChangeListener<String> textlistener() {

		return (bser, old, newvalue) -> {
			if (newvalue.isEmpty()) {

				int paneSize = acc.getPanes().size();
				if (!doesEmptyAdressExist() || paneSize < 2) {

					{
						TitledPane titledPane = new TitledPane();
						titledPane.setText("Adresse");
						titledPane.setMaxHeight(Double.MAX_VALUE);
						titledPane.setMaxWidth(Double.MAX_VALUE);
						titledPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
						titledPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
						titledPane.setMinHeight(Region.USE_COMPUTED_SIZE);
						titledPane.setMinWidth(Region.USE_COMPUTED_SIZE);

						VBox contactInfoBox = new VBox();
						Label streetL = new Label("Strasse");

						TextField streetF = new TextField();
						Label houseL = new Label("Hausnummer");
						TextField houseTF = new TextField();
						Label plzL = new Label("Plz");
						TextField plzF = new TextField();
						Label stadtL = new Label("Stadt");
						TextField stadtF = new TextField();
//						streetF.focusedProperty().addListener(listener());
						streetF.focusedProperty().addListener(listener());
//						houseTF.focusedProperty().addListener(listener());
						houseTF.focusedProperty().addListener(listener());
						plzF.focusedProperty().addListener(listener());
						stadtF.focusedProperty().addListener(listener());
//						plzF.focusedProperty().addListener(listener());
//						stadtF.focusedProperty().addListener(listener());
						contactInfoBox.getChildren().addAll(streetL, streetF, houseL, houseTF, plzL, plzF, stadtL,
								stadtF);
						titledPane.setContent(contactInfoBox);
						vboxList.add(contactInfoBox);
						checkAdressList.add(streetF);
						checkAdressList.add(houseTF);
						checkAdressList.add(plzF);
						checkAdressList.add(stadtF);
						acc.getPanes().addAll(titledPane);
						if (paneSize < 2)
							acc.setExpandedPane(titledPane);
					}
				}
			}
		};
	}

	public List<TextField> getPhoneList() {

		return numberList;
	}

	public List<TextField> getAdressenList() {
		return adressenList;
	}

	public Button getSaveButton() {
		return save;
	}

	public static ObservableList<Contact> getDatas() {
		return datas;
	}

	public TableView<Contact> getTableView() {
		return tableView;
	}

	public TextField getVornameTextField() {
		return vornameTextField;
	}

	public TextField getNachnameTextField() {
		return nachnameTextField;
	}

	public TextField getPhoneTextField() {
		return phoneTextField;
	}

	public TextField getStrassenTextField() {
		return strassenTextField;
	}

	public TextField getHausnummerTextField() {
		return hausnummerTextField;
	}

	public TextField getPlzTextField() {
		return plzTextField;
	}

	public TextField getStadtTextField() {
		return stadtTextField;
	}

	public TextField getFirmaTextField() {
		return firmaTextField;
	}

}

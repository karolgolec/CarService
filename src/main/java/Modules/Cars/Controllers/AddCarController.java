package Modules.Cars.Controllers;

import java.net.URL;
import javafx.fxml.FXML;
import Modules.Cars.Models.Car;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import Application.Services.TabsService;
import Application.Services.LanguageService;
import Application.Interfaces.IControllerTab;
import Modules.Cars.Repositories.CarRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class AddCarController implements Initializable, IControllerTab {

    /** tab of this controller */
    private Tab tab;

    /** last opened tab */
    private Tab lastTab;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField registrationNumber;

    @FXML
    private TextField vin;

    @FXML
    private TextArea note;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Cars/Resources/Languages/cars";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public AddCarController()
    {

    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * loaded after initialized controller
     *
     * @param options for controller
     * @param tab of controller
     * @param lastTab opened
     */
    @Override
    public void loaded(Object options, Tab tab, Tab lastTab) {
        configurationTab(tab, lastTab);
    }

    /**
     * Add repair to database
     */
    @FXML
    public void add(){
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber.getText());
        car.setVin(vin.getText());
        car.setNote(note.getText());
        car = CarRepository.add(car);
        ListCarsController.cars.add(0, car);
        close();
    }

    /**
     * Close tab
     */
    public void close(){
        TabsService.tabPane.getTabs().remove(tab);
        TabsService.tabPane.getSelectionModel().select(lastTab);
    }

    /**
     * Configuration tab
     */
    private void configurationTab(Tab tab, Tab lastTab){
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(resourceBundle.getString("tab.add_car.title"));
    }
}


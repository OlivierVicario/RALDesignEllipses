package sample;

import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public VBox vBoxSuitesHolder;
    public HashMap dico;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int gamme6 = 1;
        Recueil recueil = new Recueil(gamme6 % 6);
        dico = recueil.nuancierRalDe.dico;

        //ArrayList<Suite> aAfficher = recueil.searchNbColors(4,"aumoins");
        //ArrayList<Suite> aAfficher = recueil.searchTemperature("warm");
        //ArrayList<Suite> aAfficher = recueil.searchAverageValue("light");
        ArrayList<Suite> aAfficher = recueil.searchAverageChroma("bright");
        //ArrayList<Suite> aAfficher = recueil.searchContrastValue("strong");
        affichage(aAfficher);
        System.out.println("nb suites : " + recueil.suites.size() + " gamme6 : " + gamme6);
    }

    public void affichage(ArrayList<Suite> suites) {

        for (int i = 0; i < suites.size(); i++) {
            if (suites.get(i).patches.size() > 0) {
                HBox hboxSuite = new HBox();
                for (int j = 0; j < suites.get(i).patches.size(); j++) {
                    Patch patch = suites.get(i).patches.get(j);
                    Patch fromDico = (Patch) dico.get(patch.ral);
                    patch.hex = fromDico.hex;
                    Color color = Color.web(patch.hex);
                    Rectangle r = new Rectangle(100, 25, color);
                    r.setStroke(Color.TRANSPARENT);
                    r.setStrokeWidth(4);
                    hboxSuite.getChildren().add(r);
                }
                vBoxSuitesHolder.getChildren().add(hboxSuite);
            }
        }
    }
}


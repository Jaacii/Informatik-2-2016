/**
 * Created by Markus Alpers on 20.10.2016.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Textadventure extends Application {

    private int aktuellerAbschnitt;
    private String[] abschnitte;
    private String[][] beschriftungen;
    private int[][] naechsterAbschnitt;
    private Text beschreibung;
    private Button button1;
    private Button button2;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Textadventure");

        int abenteuerlaenge = 10;
        aktuellerAbschnitt = 0;

        abschnitte = new String[abenteuerlaenge];
        beschriftungen = new String[abenteuerlaenge][2];
        naechsterAbschnitt = new int[abenteuerlaenge][2];

        // 0: Startpunkt
        abschnitte[0] = "Du wachst auf und erinnerst dich nicht, wie du herkamst. Du siehst eine große Klippe hinunter. Auf der Anderen Seite ist ein Haus.";
        beschriftungen[0][0] = "Du fliegst auf die andere Seite.";
        naechsterAbschnitt[0][0] = 1;
        beschriftungen[0][1] = "Du balancierst \u00fcber einen Baumstamm um auf die andere Seite zu kommen.";
        naechsterAbschnitt[0][1] = 2;

        // 1: Die Klippe 
        abschnitte[1] = "Menschen können nicht fliegen....also f\u00e4llst du die Klippe runter und landest einem großen See.";
        beschriftungen[1][0] = "Du kletterst die die Klippe mit dem Haus herauf.";
        naechsterAbschnitt[1][0] = 2;
        beschriftungen[1][1] = "Du kletterst die Klippe herauf, auf der du vorher standest.";
        naechsterAbschnitt[1][1] = 0;

        // 2: Das Haus
        abschnitte[2] = "Du betrittst das Gel\u00e4nde des Hauses. Es ist sehr alt und gro\u00df.";
        beschriftungen[2][0] = "Du drückst den T\u00fcrklinke und gehst hinein.";
        naechsterAbschnitt[2][0] = 3;
        beschriftungen[2][1] = "Du gehst lieber anderswo weiter.";
        naechsterAbschnitt[2][1] = 4;

        // 3: Im Haus
        abschnitte[3] = "Du hast das Haus betreten und schaust dich um.";
        beschriftungen[3][0] = ".";
        naechsterAbschnitt[3][0] = 5;
        beschriftungen[3][1] = "Du bist dir nicht sicher und drehst nochmal um.";
        naechsterAbschnitt[3][1] = 6;

        // 4: Alternativer Weg
        abschnitte[4] = "Du rennst ziellos in den Wald. Hinter einem Baum siehst du einen B\u00e4ren.";
        beschriftungen[4][0] = "Du springst entschlossen auf den R\u00fccken des B\u00e4ren.";
        naechsterAbschnitt[4][0] = 9;
        beschriftungen[4][1] = "Du drehst um und flüchtest in irgendeine Richtung. ";
        naechsterAbschnitt[4][1] = 5;

        // 5: Die Flucht
        abschnitte[5] = "Du rennst und rennst immer weiter.";
        beschriftungen[5][0] = "und drehst dann irgendwann um, weil du nicht denkst, dass dies der richtige Weg ist.";
        naechsterAbschnitt[5][0] = 6;
        beschriftungen[5][1] = "und hörst auch nicht damit auf.";
        naechsterAbschnitt[5][1] = 8;

        // 6: Rückkehr
        abschnitte[6] = "Du kommst wieder an der Stelle mit dem B\u00e4ren vorbei";
        beschriftungen[6][0] = "Du springst jetzt entschlossen auf den R\u00fccken des B\u00e4ren.";
        naechsterAbschnitt[6][0] = 9;
        beschriftungen[6][1] = "Du gehst doch lieber in das Haus.";
        naechsterAbschnitt[6][1] = 5;

        //
        abschnitte[7] = "Testeintrag...";
        beschriftungen[7][0] = "Neustart";
        naechsterAbschnitt[7][0] = 0;
        beschriftungen[7][1] = "Neustart";
        naechsterAbschnitt[7][1] = 2;

        // 8: Game Over
        abschnitte[8] = "Du rennst und rennst und siehst schon die ersten H\u00e4user. Deine Freude verfliegt, als du bemerkst, dass du wieder an der Klippe mit dem Haus stehst.";
        beschriftungen[8][0] = "Nochmal von vorne.";
        naechsterAbschnitt[8][0] = 0;
        beschriftungen[8][1] = "Nochmal von vorne.";
        naechsterAbschnitt[8][1] = 0;

        // 9: Ende
        abschnitte[9] = "Tatsächlich ist es ein Zirkusb\u00e4r und du reitest auf ihm in den Sonnenuntergang bis du zur Zivilisation zur\u00fcck findest.";
        beschriftungen[9][0] = "Nochmal von vorne.";
        naechsterAbschnitt[9][0] = 0;
        beschriftungen[9][1] = "Nochmal von vorne.";
        naechsterAbschnitt[9][1] = 0;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 1200, 400);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Kleines Textadventure");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        beschreibung = new Text(abschnitte[aktuellerAbschnitt]);
        grid.add(beschreibung, 0, 1, 2, 2);

        button1 = new Button(beschriftungen[aktuellerAbschnitt][0]);
        grid.add(button1, 0, 3);
        grid.setHalignment(button1, HPos.RIGHT);

        button2 = new Button(beschriftungen[aktuellerAbschnitt][1]);
        grid.add(button2, 1, 3);
        grid.setHalignment(button2, HPos.LEFT);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                aktuellerAbschnitt = naechsterAbschnitt[aktuellerAbschnitt][0];
                rewriteEntries(aktuellerAbschnitt);
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                aktuellerAbschnitt = naechsterAbschnitt[aktuellerAbschnitt][1];
                rewriteEntries(aktuellerAbschnitt);
            }
        });

        primaryStage.show();
    }

    private void rewriteEntries(int abschnnitt){
        beschreibung.setText(abschnitte[aktuellerAbschnitt]);
        button1.setText(beschriftungen[aktuellerAbschnitt][0]);
        button2.setText(beschriftungen[aktuellerAbschnitt][1]);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

/**
 * Grundsätzlich nötig:
 * Textanzeige
 * Buttons für Auswahl der Antwort
 *
 * Texte aus Array von Abschnitten
 * Dazu Array mit Anzahl Buttons zum Abschnitt
 * Dazu Array mit Texten für jeden der Buttons
 */
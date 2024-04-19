/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.pig;

import java.io.File;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author venkat
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button play_game, submit, settings_back;
    @FXML
    private Text title, score1, score2, current, current2, win1, win2;
    @FXML
    private Pane currentPane;
    @FXML
    private AnchorPane main, dice;
    @FXML
    private ImageView roll1, roll2, turn1, turn2;
    @FXML
    private GridPane instructionsPane, game, Settings;
    @FXML
    private TextField WinScore, WinScore2; 
    Random rand = new Random();
    int turn = 1;
    int dice1;
    int dice2;
    int currentTotal;
    int currentTotal2;
    int holdTotal1 = 0;
    int holdTotal2 = 0;
    int winScore = 50;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button btnPressed = (Button) event.getSource();

            if (btnPressed.getId().equalsIgnoreCase("play")) {
                if (currentPane != null) {
                    currentPane.setVisible(false);
                }
                game.setVisible(true);
                currentPane = game;
            }
            if (btnPressed.getId().equalsIgnoreCase("instructions")) {
                if (currentPane != null) {
                    currentPane.setVisible(false);
                }
                instructionsPane.setVisible(true);
                currentPane = instructionsPane;
            }
            if (btnPressed.getId().equalsIgnoreCase("settings")) {
                if (currentPane != null) {
                    currentPane.setVisible(false);
                }
                Settings.setVisible(true);
                currentPane = Settings;
            }
            if (btnPressed.getId().equalsIgnoreCase("instructions_back")) {
                currentPane.setVisible(false);
                main.setVisible(true);
                currentPane = main;
            }
            if (btnPressed.getId().equalsIgnoreCase("settings_back")) {
                currentPane.setVisible(false);
                main.setVisible(true);
                currentPane = main;
            }
        }
    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
        ArrayList<Integer> diceRoll = new ArrayList();
        diceRoll.add(1);
        diceRoll.add(2);
        diceRoll.add(3);
        diceRoll.add(4);
        diceRoll.add(5);
        diceRoll.add(6);
        if (event.getSource() instanceof Button) {
            Button btnPressed = (Button) event.getSource();

            String FilePath = "C:\\Users\\venkat\\Documents\\NetBeansProjects\\Game_Of_Pig\\src\\game\\of\\pig\\";
            File file = null;
            if (btnPressed.getId().equalsIgnoreCase("roll")) {
                dice1 = diceRoll.get(rand.nextInt(diceRoll.size()));
                dice2 = diceRoll.get(rand.nextInt(diceRoll.size()));

                switch (dice1) {
                    case 1:
                        file = new File(FilePath + "one.png");
                        break;
                    case 2:
                        file = new File(FilePath + "two.png");
                        break;
                    case 3:
                        file = new File(FilePath + "three.png");
                        break;
                    case 4:
                        file = new File(FilePath + "four.png");
                        break;
                    case 5:
                        file = new File(FilePath + "five.png");
                        break;
                    case 6:
                        file = new File(FilePath + "six.png");
                        break;
                }

                Image image = new Image(file.toURI().toString());
                System.out.println(file.toURI().toString());
                roll1.setVisible(true);
                roll2.setVisible(true);
                roll1.setImage(image);

                switch (dice2) {
                    case 1:
                        file = new File(FilePath + "one.png");
                        break;
                    case 2:
                        file = new File(FilePath + "two.png");
                        break;
                    case 3:
                        file = new File(FilePath + "three.png");
                        break;
                    case 4:
                        file = new File(FilePath + "four.png");
                        break;
                    case 5:
                        file = new File(FilePath + "five.png");
                        break;
                    case 6:
                        file = new File(FilePath + "six.png");
                        break;
                }
                Image image2 = new Image(file.toURI().toString());
                System.out.println(image2);
                roll1.setVisible(true);
                roll2.setVisible(true);
                roll2.setImage(image2);

                calculateRoll();
                

            }

            if (btnPressed.getId().equalsIgnoreCase("hold")) {
                if (turn == 1) {
                    holdTotal1 += currentTotal;
                    score1.setText(Integer.toString(holdTotal1));
                    currentTotal = 0;
                    current.setText(Integer.toString(currentTotal));

                } else if (turn == 2) {
                    holdTotal2 += currentTotal2;
                    score2.setText(Integer.toString(holdTotal2));
                    currentTotal2 = 0;
                    current2.setText(Integer.toString(currentTotal2));

                }
                win();
                turn();
                
            }

        }
    }
    
    @FXML
    private void handleButtonAction3(ActionEvent event){
       if (event.getSource() instanceof Button) {
            Button btnPressed = (Button) event.getSource();
            
            if(btnPressed.getId().equalsIgnoreCase("submit")){
                String myscore = WinScore.getText();
                WinScore2.setText(myscore);
                winScore = Integer.parseInt(myscore);
            }
        }
    }

    private void turn() {
        if (turn == 1 ) {
            turn = 2;
            turn1.setVisible(false);
            turn2.setVisible(true);
        } else if (turn == 2) {
            turn = 1;
            turn1.setVisible(true);
            turn2.setVisible(false);
        }
       

    }
    private void win(){
        if (turn == 1){
            if (holdTotal1 >= winScore){
                roll1.setVisible(false);
                roll2.setVisible(false);
                win1.setVisible(true);
                
            }
        }
        else if (turn == 2){
            if (holdTotal2 >= winScore){
                roll1.setVisible(false);
                roll2.setVisible(false);
                win2.setVisible(true);
                
            }
        }
    }

    private void calculateRoll() {
        if (turn == 1) {
            if (dice1 != 1 && dice2 != 1) {
                currentTotal += dice1 + dice2;

            } else {
                currentTotal = 0;
                turn();
            }
            current.setText(Integer.toString(currentTotal));
        }
        else if (turn == 2) {
            if (dice1 != 1 && dice2 != 1) {
                currentTotal2 += dice1 + dice2;

            } else {
                currentTotal2 = 0;
                turn();
            }
            current2.setText(Integer.toString(currentTotal2));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentPane = main;
        main.setVisible(true);
        instructionsPane.setVisible(false);
        game.setVisible(false);
        turn1.setVisible(true);
        turn2.setVisible(false);
        Settings.setVisible(false);
        currentTotal = 0;
        currentTotal2 = 0;
        holdTotal1 = 0;
        holdTotal2 = 0;
        roll1.setVisible(false);
        roll2.setVisible(false);
        win1.setVisible(false);
        win2.setVisible(false);

    }
}

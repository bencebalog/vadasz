package com.example.vadasz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane pnJatek;
    @FXML private Label lbLoves;
    @FXML private Label lbRoka;
    private String[] iconNev = {"dark","dead","fox","fox","home","tree"};
    private Image[] icon = new Image[5];

    private final int DARK = 0;
    private final int ROKA = 0;
    private final int TREE = 0;

    private ImageView[][] lt= new ImageView[16][32];
    private int[][]  t=new int[16][32];

    private int roka = 0;
    private int rokaMax = 0;
    public void initialize(){
        for(int i=0;i<5;i++) icon[i] = new Image(getClass().getResourceAsStream("icons/"+iconNev[i]+".png"));
        for(int s=0;s<16;s++) for(int o=0;o<32;o++){
            lt[s][o]= new ImageView(icon[DARK]);
            lt[s][o].setLayoutX(10+o*48);
            lt[s][o].setLayoutY(10+s*48);
            pnJatek.getChildren().add(lt[s][o]);
        }
        generalErdo();
    }

    private void generalErdo(){
        roka=0;
        for(int s=0;s<16;s++) for(int o=0;o<32;o++){
            if(Math.random()<0.1) t[s][o] = ROKA; else t[s][o] = TREE;
        }
        rokaMax=roka;
        lbRoka.setText(roka+" / "+rokaMax+" róka");
    }
}
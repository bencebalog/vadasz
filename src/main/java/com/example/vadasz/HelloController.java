package com.example.vadasz;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane pnJatek;
    @FXML private Label lbLoves;
    @FXML private Label lbRoka;
    private String[] iconNev = {"dark","dead","fox","home","tree"};
    private Image[] icon = new Image[5];

    private final int DARK = 0;
    private final int DEAD = 1;
    private final int ROKA = 2;
    private final int HOME = 3;
    private final int TREE = 4;

    private ImageView[][] lt= new ImageView[16][32];
    private int[][]  t=new int[16][32];
    private int es=-1,eo=-1;

    private AnimationTimer timer = null;
    private long tt = 0;private long most = 0;


    private int roka = 0;
    private int rokaMax = 0;
    private int loves = 0;
    private int talalt=0;


    public void initialize(){
        for(int i=0;i<5;i++) icon[i] = new Image(getClass().getResourceAsStream("icons/"+iconNev[i]+".png"));
        for(int s=0;s<16;s++) for(int o=0;o<32;o++){
            int ss=s,oo=o;
            lt[s][o]= new ImageView(icon[DARK]);
            lt[s][o].setLayoutX(10+o*48);
            lt[s][o].setLayoutY(10+s*48);
            lt[s][o].setOnMouseEntered(e -> vilagit(ss,oo));
            lt[s][o].setOnMousePressed(e -> loves(ss,oo));
            pnJatek.getChildren().add(lt[s][o]);
        }
        generalErdo();
        timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                most = now;
                if(now>tt) elbujik();
            }
        };
        timer.start();
    }

    private void generalErdo(){
        roka=0;loves=0;talalt=0;
        for(int s=0;s<16;s++) for(int o=0;o<32;o++){
            if(Math.random()<0.1) {t[s][o] = ROKA; roka++;} else t[s][o] = TREE;
        }
        rokaMax=roka;
        lbLoves.setText(loves+" lövés / "+talalt+" találat");
        lbRoka.setText(roka+" / "+rokaMax+" róka");
    }

    private void vilagit(int s,int o){
        if(s!=es || o!=eo){
            for(int dS=-2;dS<=+2;dS++)for(int dO=-2;dO<=+2;dO++){
                int ss=es+dS,oo=eo+dO;
                if(ss>=0 && ss<=15 && oo>=0 && oo<=31 && !(Math.abs(dS)==2 && Math.abs(dO)==2)){
                    lt[ss][oo].setImage(icon[DARK]);
                }
            }
            for(int dS=-2;dS<=+2;dS++)for(int dO=-2;dO<=+2;dO++){
                int ss=s+dS,oo=o+dO;
                if(ss>=0 && ss<=15 && oo>=0 && oo<=31 && !(Math.abs(dS)==2 && Math.abs(dO)==2)){
                    lt[ss][oo].setImage(icon[t[ss][oo]]);
                }
            }
            es=s;eo=o;
            tt = most+500000000;
        }
    }
    private void elbujik(){
        for(int dS=-2;dS<=+2;dS++)for(int dO=-2;dO<=+2;dO++) {
            int ss = es + dS, oo = eo + dO;
            if (ss >= 0 && ss <= 15 && oo >= 0 && oo <= 31 && !(Math.abs(dS) == 2 && Math.abs(dO) == 2) && t[ss][oo] == ROKA) {
                lt[ss][oo].setImage(icon[HOME]);
                t[ss][oo] = HOME;
                roka--;
            }
        }
        lbRoka.setText(roka+" / "+rokaMax+" róka");
    }

    private void loves(int s,int o){
        loves++;
        if(t[s][o] == ROKA){
            lt[s][o].setImage(icon[DEAD]);t[s][o]=DEAD;roka--;
            lbRoka.setText(roka+" / "+rokaMax+" róka");
            talalt++;

        }
        lbLoves.setText(loves+" lövés / "+talalt+" találat");
    }
}
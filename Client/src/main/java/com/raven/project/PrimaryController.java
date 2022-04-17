package com.raven.project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class PrimaryController implements ClientStatus {

    private double x = 0;
    private double y = 0;
    private double mainWindowWidth,mainWindowHeight;
    private Client client;
    private Remote remote = Remote.getInstance();
    public String connectedThrough;
    private ImageView qrCodeImage,logoutButton;
    private Label codeLabel;
    private Label typeCodeLabel;
    
    
    @FXML
    private ImageView iconfiedBtn;
    @FXML
    private ImageView settingBtn,aboutusBtn,supportusBtn;
    private ImageView iconifiedBtn;
    @FXML
    private ImageView closeBtn,maximizedBtn;
    @FXML
    private ImageView generatecodeBtn,generateqrcodeBtn;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label connectionName;
    @FXML
    private Label connectionTimeLabel;
    @FXML
    private Label connectionDateLabel;
    @FXML
    private ImageView backButton;

   
    public PrimaryController()
    {
        this.client = Client.getInstance();
        try
        {
        logoutButton=new ImageView();
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setImage(new Image(getClass().getResourceAsStream("images/logoutBtn.png")));
        logoutButton.setId("logoutBtn");
        logoutButton.setOnMouseEntered((MouseEvent event) -> {
            onMouseEntered(event);
        });
        logoutButton.setOnMouseExited((MouseEvent event) -> {
            onMouseExited(event);
        });
        logoutButton.setOnMouseClicked(event->{
            remote.stopConnection();
            connectedThrough="LOUT";
            mainPane.getChildren().remove(logoutButton);

        });
        this.connectedThrough = null;
        remote.addListener(this);
        remote.start();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
   
    private void restartRemote()
    {
        this.client = Client.getInstance();
        remote=Remote.getInstance();
        remote.addListener(this);
        remote.start();        
    }
    
    @FXML
    private void onMouseEntered(javafx.scene.input.MouseEvent event) {
        String componentId = event.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
        if(componentId.equals("settingBtn"))settingBtn.setImage(new Image(getClass().getResourceAsStream("images/settingbtnglow.png")));
        if(componentId.equals("aboutusBtn"))aboutusBtn.setImage(new Image(getClass().getResourceAsStream("images/aboutusbtnglow.png")));
        if(componentId.equals("supportusBtn"))supportusBtn.setImage(new Image(getClass().getResourceAsStream("images/supportusbtnglow.png")));
        if(componentId.equals("generatecodeBtn"))generatecodeBtn.setImage(new Image(getClass().getResourceAsStream("images/generatecodebuttonglow.png")));
        if(componentId.equals("generateqrcodeBtn"))generateqrcodeBtn.setImage(new Image(getClass().getResourceAsStream("images/generateqrcodebuttonglow.png")));
        if(componentId.equals("iconifiedBtn"))iconifiedBtn.setImage(new Image(getClass().getResourceAsStream("images/iconifiedbtnglow.png")));
        if(componentId.equals("closeBtn"))closeBtn.setImage(new Image(getClass().getResourceAsStream("images/exitbtnglow.png")));
        if(componentId.equals("maximizedBtn"))maximizedBtn.setImage(new Image(getClass().getResourceAsStream("images/maximizebtnglow.png")));
        if(componentId.equals("logoutBtn"))logoutButton.setImage(new Image(getClass().getResourceAsStream("images/logoutBtnGlow.png")));
        if(componentId.equals("backButton"))backButton.setImage(new Image(getClass().getResourceAsStream("images/backBtnGlow.png")));
        
    }
    @FXML
    private void onMouseExited(javafx.scene.input.MouseEvent event) {
        String componentId = event.getSource().toString();
        if(componentId.contains("logoutBtn"))logoutButton.setImage(new Image(getClass().getResourceAsStream("images/logoutBtn.png")));
        if(componentId.contains("backButton"))backButton.setImage(new Image(getClass().getResourceAsStream("images/backBtn.png")));
        if(componentId.contains("settingBtn"))settingBtn.setImage(new Image(getClass().getResourceAsStream("images/settingbtn.png")));
        if(componentId.contains("aboutusBtn"))aboutusBtn.setImage(new Image(getClass().getResourceAsStream("images/aboutusbtn.png")));
        if(componentId.contains("supportusBtn"))supportusBtn.setImage(new Image(getClass().getResourceAsStream("images/supportusbtn.png")));
        if(componentId.contains("generatecodeBtn"))generatecodeBtn.setImage(new Image(getClass().getResourceAsStream("images/generatecodebutton.png")));
        if(componentId.contains("generateqrcodeBtn"))generateqrcodeBtn.setImage(new Image(getClass().getResourceAsStream("images/generateqrcodebutton.png")));
        if(componentId.contains("iconifiedBtn"))iconifiedBtn.setImage(new Image(getClass().getResourceAsStream("images/iconifiedbtn.png")));
        if(componentId.contains("closeBtn"))closeBtn.setImage(new Image(getClass().getResourceAsStream("images/exitbtn.png")));
        if(componentId.contains("maximizedBtn"))maximizedBtn.setImage(new Image(getClass().getResourceAsStream("images/maximizebtn.png")));

    }
    
    @FXML
    public void generateqrcodeBtnActionPerformed() {                                             
        
        try {
         
            mainWindowWidth=mainPane.getPrefWidth();
            mainWindowHeight=mainPane.getPrefHeight();

            if(remote.isInterrupted()){
                restartRemote();
            }
            qrCodeImage=new ImageView();
            qrCodeImage.setImage(new Image(getClass().getResourceAsStream(client.getQRCode())));
            qrCodeImage.setLayoutX(mainWindowWidth*0.38);
            qrCodeImage.setLayoutY(mainWindowHeight*0.2);
            mainPane.getChildren().remove(generateqrcodeBtn);
            mainPane.getChildren().remove(generatecodeBtn);
            mainPane.getChildren().add(qrCodeImage);
            connectedThrough="QR";
            backButton.setDisable(false);
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }                                            

    @FXML
    public void generatecodeBtnActionPerformed() {
        try {
            mainWindowWidth=mainPane.getPrefWidth();
            mainWindowHeight=mainPane.getPrefHeight();
            if(remote.isInterrupted()){
                restartRemote();
            }
            mainPane.getChildren().remove(generateqrcodeBtn);
            mainPane.getChildren().remove(generatecodeBtn);
            codeLabel=new Label();
            codeLabel.setBackground(new Background(new BackgroundFill(Color.rgb(51, 51, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            codeLabel.setFont(Font.font("Rockwell",48));
            codeLabel.setTextFill(Color.rgb(0, 255,204));
            codeLabel.setAlignment(Pos.CENTER);
            codeLabel.setText(client.getLocalIpAddress());
            codeLabel.setBorder(new Border(new BorderStroke(Color.rgb(0, 255,204), BorderStrokeStyle.SOLID, null, new BorderWidths(1,1,1,1))));


            codeLabel.setLayoutX(mainWindowWidth*0.38);
            codeLabel.setLayoutY(mainWindowHeight*0.40);
            
            typeCodeLabel=new Label();
            
            typeCodeLabel.setFont(Font.font("Rockwell",18)); // NOI18N
            typeCodeLabel.setAlignment(Pos.CENTER);
            typeCodeLabel.setText("Enter this code on your Android device.");
            typeCodeLabel.setTextFill(Color.WHITE);

            typeCodeLabel.setLayoutX(mainWindowWidth*0.38);
            typeCodeLabel.setLayoutY(mainWindowHeight*0.55);
            
            connectedThrough="CODE";
            backButton.setDisable(false);


            mainPane.getChildren().add(codeLabel);
            mainPane.getChildren().add(typeCodeLabel);

            


        } catch (Exception exception) {

        }
            
    }
    
    @FXML
    public void backButtonActionPerformed()
    {
        client.deleteImage();
        if(connectedThrough.equals("QR")) mainPane.getChildren().remove(qrCodeImage);
        if(connectedThrough.equals("CODE"))
        {
             mainPane.getChildren().remove(codeLabel);
             mainPane.getChildren().remove(typeCodeLabel);            
        }
        connectedThrough=null;
        backButton.setDisable(true);
        mainPane.getChildren().add(generateqrcodeBtn);
        mainPane.getChildren().add(generatecodeBtn);
        
    }  
    
    @Override
    public void onClientLoggedIn(String name, String address) {
        Platform.runLater(() -> {

        backButton.setDisable(true);
        if(connectedThrough==null)
        {
            mainPane.getChildren().remove(generateqrcodeBtn);
            mainPane.getChildren().remove(generatecodeBtn);
        }
        else if(connectedThrough.equals("QR"))
        {
            mainPane.getChildren().remove(qrCodeImage);
            client.deleteImage();
//            mainPane.getChildren().remove(generatecodeBtn);
        }
        else if(connectedThrough.equals("CODE"))
        {
            mainPane.getChildren().remove(this.codeLabel);
            mainPane.getChildren().remove(this.typeCodeLabel);            
        }
        connectionName.setText(name);

        logoutButton.setLayoutX(479.18);
        logoutButton.setLayoutY(318.6);


        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();

        connectionTimeLabel.setText("Time : "+dtf.format(localTime));

            
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate localDate = LocalDate.now();

        connectionDateLabel.setText(dtf2.format(localDate));

        mainPane.getChildren().add(logoutButton);


        });
    }

    @Override
    public void onClientLoggedOut(String name, String address) {
        
        Platform.runLater(() -> {
        connectedThrough="DCN";//DCN - Disconnected
        backButton.setDisable(true);
        mainPane.getChildren().remove(logoutButton);
        connectionName.setText("");
        connectionTimeLabel.setText("");
        connectionDateLabel.setText("");
        
        mainPane.getChildren().add(generateqrcodeBtn);
        mainPane.getChildren().add(generatecodeBtn);
       });

    }
    @FXML
    public void closeButtonActionPerformed() { 
        client.deleteImage();
        remote.stopConnection();
        Platform.exit();
        System.exit(0);
    }
    @FXML
    public void iconifiedButtonActionPerformed()
    {
        Stage stage = (Stage) iconfiedBtn.getScene().getWindow();
        stage.setIconified(true);
        
    }
    
    @FXML
    public void onMousePressed(MouseEvent event)
    {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    @FXML
    public void onMouseDragged(MouseEvent event)
    {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
}

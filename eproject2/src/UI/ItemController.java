package UI;


import Data.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Trang Tran
 */
public class ItemController {

    @FXML
    private Label Name;

    @FXML
    private ImageView Img;
    
    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(item);
    }
    
    private Food item;
    private MyListener myListener;
    
    public void setData(Food item, MyListener myListener){
        this.item = item;
        this.myListener = myListener;
        Name.setText(item.getName());
        Image image = new Image("file:/D:/Java/eproject2/src/img/"+item.getFoodIMG());
        Img.setImage(image);
        
    }
}

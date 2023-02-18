package UI;

import Data.Cart;
import Data.Category;
import Data.CategoryDAOimpl;
import Data.CategoryDOB;
import Data.Food;
import Data.FoodDAOimpl;
import Data.FoodDOB;
import static UI.ShoppingController.Orders;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Trang Tran
 */
public class MenuController implements Initializable {

    CategoryDOB CategoryDAO = new CategoryDAOimpl();
    FoodDOB FoodDAO = new FoodDAOimpl();

    int column = 0, row = 1;
    int c;
    int from = 0, to = 0;
    int itempage = 6;

    private Image image;
    private MyListener myListener;
    private List<Food> items = FoodDAO.selectAll();

    private Food orderNow;
    private Cart card;

    @FXML
    private VBox ChosenCard;

    @FXML
    private Pagination pagination;

    @FXML
    private ScrollPane Scroll;

    @FXML
    private Label NameBox;

    @FXML
    private ImageView ImageBox;

    @FXML
    private Label QuantityBox;

    @FXML
    private Label PriceBox;

    @FXML
    private GridPane Gird;

    @FXML
    private ComboBox<String> dropcate;

    @FXML
    private Label txtIntOrder;

    @FXML
    void Clickdropcate(ActionEvent event) {

    }

    @FXML
    void ClickMinus(ActionEvent event) {
        orderNow.setQuantity(orderNow.getQuantity() - 1);
        if (orderNow.getQuantity() <= 0) {
            orderNow.setQuantity(1);
        }
        QuantityBox.setText(String.valueOf(orderNow.getQuantity()));
        PriceBox.setText("$" + String.valueOf(Double.parseDouble(orderNow.getPrice().substring(1)) * orderNow.getQuantity()));
    }

    @FXML
    void ClickPlus(ActionEvent event) {
        orderNow.setQuantity(orderNow.getQuantity() + 1);
        QuantityBox.setText(String.valueOf(orderNow.getQuantity()));
        PriceBox.setText("$" + String.valueOf(Double.parseDouble(orderNow.getPrice().substring(1)) * orderNow.getQuantity()));
    }

    @FXML
    void ClickOrder(ActionEvent event) {
        Orders.add(orderNow);
        c = Orders.size();

        if (Orders.size() > 0) {
            txtIntOrder.setText(String.valueOf(c));
            txtIntOrder.setStyle("-fx-background-color: red" + ";\n"
                    + "-fx-background-radius: 30;");
        } else {
            txtIntOrder.setVisible(false);
        }
    }

    @FXML
    void ClickShopping(ActionEvent event) throws IOException {
        Navigator.getInstance().goToShopping();
    }

    @FXML
    void ClickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resaurces) {
        if (Orders.size() > 0) {
            txtIntOrder.setText(String.valueOf(Orders.size()));
            txtIntOrder.setStyle("-fx-background-color: red" + ";\n"
                    + "-fx-background-radius: 30;");
        }
        dropcate.getItems().addAll("ALL");
        dropcate.getSelectionModel().select("ALL");
        if (dropcate.getValue().equals("ALL")) {
            pagination.setPageCount((FoodDAO.countFood() / itempage) + 1);
            pagination.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    Gird.getChildren().clear();
                    column = 0;
                    row = 1;
                    return createpage(pageIndex);
                }
            });
        }
        dropcate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (dropcate.getValue().equals("ALL")) {
                pagination.setPageCount((FoodDAO.countFood() / itempage) + 1);
                pagination.setPageFactory(new Callback<Integer, Node>() {
                    @Override
                    public Node call(Integer pageIndex) {
                        Gird.getChildren().clear();
                        column = 0;
                        row = 1;
                        return createpage(pageIndex);
                    }
                });
            }else{
                pagination.setPageCount((FoodDAO.countFoodCategory(dropcate.getValue())/ itempage) + 1);
                pagination.setPageFactory(new Callback<Integer, Node>() {
                    @Override
                    public Node call(Integer pageIndex) {
                        Gird.getChildren().clear();
                        column = 0;
                        row = 1;
                        return createpageCategoryName(pageIndex);
                    }
                });
            
            }
            });

            if (items.size() > 0) {
//                setChosenCard(items.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Food item) {
                        item.setQuantity(1);
                        setChosenCard(item);
                        orderNow = item;
                    }
                };

            }
//            dropcate.valueProperty().addListener((observable, oldValue, newValue) -> {
//                items = FoodDAO.selectAllCategoryName(dropcate.getValue());
//                ObservableList<Category> ListCategory = CategoryDAO.selectAll();
//                ObservableList<String> List = FXCollections.observableArrayList();
//                for (Category C : ListCategory) {
//                    List.add(C.getCategoryName());
//                }
//
//                try {
//                    for (int i = 0; i < items.size(); i++) {
//                        FXMLLoader fxmlLoader = new FXMLLoader();
//                        fxmlLoader.setLocation(getClass().getResource("/UI/Item.fxml"));
//                        AnchorPane anchorPane = fxmlLoader.load();
//
//                        ItemController itemController = fxmlLoader.getController();
//                        itemController.setData(items.get(i), myListener);
//
//                        if (column == 3) {
//                            column = 0;
//                            row++;
//                        }
//
//                        Gird.add(anchorPane, column++, row);   //(child,column,row)   
//                        GridPane.setMargin(anchorPane, new Insets(10));
//                    }
//                } catch (IOException e) {
//
//                }
//            });

            ObservableList<Category> ListCategory = CategoryDAO.selectAll();
            ObservableList<String> List = FXCollections.observableArrayList();

            for (Category C : ListCategory) {
                dropcate.getItems().addAll(C.getCategoryName());
            }

//        try {
//            for (int i = 0; i < items.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/UI/Item.fxml"));
//
//                AnchorPane anchorPane = fxmlLoader.load();
//                ItemController itemController = fxmlLoader.getController();
//                itemController.setData(items.get(i), myListener);
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//                Gird.add(anchorPane, column++, row);   //(child,column,row)   
//                GridPane.setMargin(anchorPane, new Insets(10));
//            }
//
//        } catch (IOException e) {
//
//        }
        }

    private void setChosenCard(Food item) {
        NameBox.setText(item.getName());
        PriceBox.setText(item.getPrice());
        QuantityBox.setText(String.valueOf(item.getQuantity()));
        image = new Image("file:/D:/Java/eproject2/src/img/" + item.getFoodIMG());
        ImageBox.setImage(image);
    }

    public ScrollPane createpage(int pageIndex) {
        from = pageIndex * itempage;
        to = itempage;
        items.clear();
        items = FoodDAO.selectAll(from, to);
        try {
            for (Food food : items) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UI/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(food, myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                Gird.add(anchorPane, column++, row);   //(child,column,row)   
                GridPane.setMargin(anchorPane, new Insets(10));

            }

        } catch (Exception e) {

        }
        return Scroll;
    }
     public ScrollPane createpageCategoryName(int pageIndex) {
        from = pageIndex * itempage;
        to = itempage;
        items.clear();
        items = FoodDAO.selectAllCategoryName(dropcate.getValue(),from, to);
        try {
            for (Food food : items) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UI/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(food, myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                Gird.add(anchorPane, column++, row);   //(child,column,row)   
                GridPane.setMargin(anchorPane, new Insets(10));

            }

        } catch (Exception e) {

        }
        return Scroll;
    }
}

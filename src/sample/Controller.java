package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Controller {

    @FXML
    private Button Finder;

    @FXML
    private TextField Input_money;

    @FXML
    private Text Exchange_Rates;

    @FXML
    private Text Date_Ex;

    @FXML
    void initialize() {
        Finder.setOnAction(event -> {
            String getRates = FirstUpperCase(Input_money.getText().trim());

            if (!getRates.equals("")) {
                Document GettingCourse = null;
                try {
                    GettingCourse = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert GettingCourse != null;
                Elements tbody = GettingCourse.select("tbody");
                Element h2 = GettingCourse.select("h2").first();
                String[] Currency = tbody.text().split(getRates);
                if (Currency.length > 1) {
                    assert h2 != null;
                    String dater = h2.text();
                    dater = dater.substring(50, 60);
                    Exchange_Rates.setText("В рублях: " + Currency[1].substring(1, 6));
                    Date_Ex.setText("Курс на: " + dater);
                } else {
                    Exchange_Rates.setText("Неверная валюта");
                    Date_Ex.setText("");
                }
            }
        });
    }
    public String FirstUpperCase(String word){
        if(word == null || word.isEmpty()) return ""; //или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}



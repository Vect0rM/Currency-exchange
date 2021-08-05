package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
            String getRates = Input_money.getText().trim().toLowerCase(Locale.ROOT);
            if (!getRates.equals("")) {
                if (Currency().containsKey(getRates)) {
                    String output = getUrlContent();
                    if (!output.isEmpty()) {
                        JSONObject object = new JSONObject(output);
                        Exchange_Rates.setText("В евро: " + object.getJSONObject("rates").getDouble(Currency().get(getRates)));
                        Date_Ex.setText("Курс на: " + object.getString( "date"));
                    }
                } else {
                    Exchange_Rates.setText("Неверная валюта");
                    Date_Ex.setText("");
                }
            }
        });
    }

    public Map<String, String> Currency(){
        Map<String, String> CurrencyValue = new HashMap<>();
        CurrencyValue.put("доллар" , "USD");
        CurrencyValue.put("фунт" ,"GBP");
        CurrencyValue.put("рубль" , "RUB");
        return CurrencyValue;
    }

    private static String getUrlContent() {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=a641f97d8f5aaa342466d8b62bdd0ebf&format=1");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Неверная валюта");
        }
        return content.toString();
    }
}



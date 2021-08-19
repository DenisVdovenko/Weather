package com.example.weather;

        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.text.Text;
        import javafx.scene.control.TextField;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;

public class HelloController {

    @FXML
    private TextField city;

    @FXML
    private Button getWeather;

    @FXML
    private Text temperatureText;

    @FXML
    void initialize() {
        getWeather.setOnAction(actionEvent -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String outputData = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid=97b68037b03f93e3e2665aab919c1627");

                if (!outputData.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(outputData);
                    System.out.println(outputData);
                    temperatureText.setText(String.valueOf(jsonObject.getJSONObject("main").getDouble("temp")));
                } else {
                    city.setText("City is not found!");
                }
            }
        });
    }

    private static String getUrlContent(String urlAddress) {
        StringBuffer getContent = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                getContent.append(stringLine + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Город не найден!");
        }
        return getContent.toString();
    }

}

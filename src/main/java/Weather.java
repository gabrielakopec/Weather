import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;


import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Weather {

    public static void main(String[] args) throws Exception {


        URI uri = new URI("http://traffic.erzeszow.pl/scripts/weather.php");
        String response = IOUtils.toString(uri, StandardCharsets.UTF_8.name());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Station[] stationArray = objectMapper.readValue(response.substring(1), Station[].class);


        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();


        System.out.println("Dane pogodowe dla miasta Rzeszów w dniu " + date + " o godzinie " + time);
        System.out.println(" ");
        System.out.println("Temperatura 2m poniżej gruntu " + gettemp2m_Average(stationArray) + " st.C");
        System.out.println("Temperatura 20cm poniżej gruntu " + gettemp20cm_Average(stationArray) + " st.C");
        System.out.println("Temperatura jezdni " + gettemp0m_Average(stationArray) + " st.C");
        System.out.println("Temperatura powietrza " + gettemp5cm_Average(stationArray) + " st.C");
        System.out.println("Kierunek wiatru " + getwind_dir(stationArray));
        System.out.println("Prędkość wiatru " + getwind_speed(stationArray) + " m/s");
        System.out.println("Poziom opadu " + getprecipitation(stationArray) + " mm/m2");


    }

    private static boolean IfMeasurmentDone10MinutesAgo(Station[] stationArray, int i) {

        LocalDateTime time10MinutesAgo = LocalDateTime.now().minusMinutes(10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(stationArray[i].getMeasure_time(), formatter).minusHours(1);

        return dateTime.isAfter(time10MinutesAgo);

    }


    private static double gettemp20cm_Average(Station[] stationArray) {
        double temp20cm_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp20cm();
                temp20cm_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp20cm_Average;
    }

    private static double gettemp2m_Average(Station[] stationArray) {
        double temp2m_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp2m();
                temp2m_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp2m_Average;

    }

    private static double gettemp0m_Average(Station[] stationArray) {
        double temp0m_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp0m();
                temp0m_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp0m_Average;
    }

    private static double gettemp5cm_Average(Station[] stationArray) {
        double temp5cm_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp5cm();
                temp5cm_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp5cm_Average;
    }

    private static double getwind_dir(Station[] stationArray) {
        double wind_die_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getWind_dir();
                wind_die_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return wind_die_Average;
    }

    private static double getwind_speed(Station[] stationArray) {
        double wind_speed_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getWind_speed();
                wind_speed_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return wind_speed_Average;
    }

    private static double getprecipitation(Station[] stationArray) {
        double precipitation_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (IfMeasurmentDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getPrecipitation();
                precipitation_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return precipitation_Average;
    }
}

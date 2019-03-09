import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;


import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;


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
        System.out.println("Temperatura 2m poniżej gruntu " + getTemp2mAverage(stationArray) + " st.C");
        System.out.println("Temperatura 20cm poniżej gruntu " + getTemp20cmAverage(stationArray) + " st.C");
        System.out.println("Temperatura jezdni " + getTemp0mAverage(stationArray) + " st.C");
        System.out.println("Temperatura powietrza " + getTemp5cmAverage(stationArray) + " st.C");
        System.out.println("Kierunek wiatru " + getWindDir(stationArray));
        System.out.println("Prędkość wiatru " + getWindSpeed(stationArray) + " m/s");
        System.out.println("Poziom opadu " + getPrecipitation(stationArray) + " mm/m2");

        //-----------------------------------------------------------------------------
        //wywołanie drugiej opcji
        Station average = getAverage(stationArray);

        System.out.println("Dane pogodowe dla miasta Rzeszów w dniu " + date + " o godzinie " + time);
        System.out.println(" ");
        System.out.println("Temperatura 2m poniżej gruntu " + average.getTemp2m() + " st.C");
        System.out.println("Temperatura 20cm poniżej gruntu " + average.getTemp20cm() + " st.C");
        System.out.println("Temperatura jezdni " + average.getTemp0m() + " st.C");
        System.out.println("Temperatura powietrza " + average.getTemp5cm() + " st.C");
        System.out.println("Kierunek wiatru " + average.getWindDir());
        System.out.println("Prędkość wiatru " + average.getWindSpeed() + " m/s");
        System.out.println("Poziom opadu " + average.getPrecipitation() + " mm/m2");
    }

    /*
        nazwa metody mówi co innego niż robi, mowa jest o pomiarze wykonanym 10 minut temu a sprawdzane
        jest czy pomiar był wykonany nie wcześniej niż 10 minut temu
    */
    private static boolean ifMeasurementDone10MinutesAgo(Station[] stationArray, int i) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(stationArray[i].getMeasureTime(), formatter).minusHours(1);

        return dateTime.isAfter(LocalDateTime.now().minusMinutes(10));

    }


    /*
        komentarze w tej metodzie dotyczą się wszystkich pozostałych metod
     */
    protected static double getTemp20cmAverage(Station[] stationArray) {
        double temp20cm_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp20cm();
                /*
                    linijki poniżej tu raczej być nie powinno, bo w tym momencie wyliczasz średnią z jeszcze
                    niepełnego zestawu danych, podzielić powinnaś dopiero w ostatniej linijce przy return ale
                    tylko przez liczbę stacji dla ktorych wyniki były nie wczesniej niz 10 minut temu
                 */
                temp20cm_Average = Sum / stationArray.length;
            } else {
                /*
                    nie wiem czemu jes tutaj ta inkrementacja, jeżeli znalazłby się pomiar wykonany wcześniej niż
                    10 minut temu to spowoduje że następny zostanie ominięty bo inkrementacja jest też w pętli for
                    więc i++ będzie zawołane dwa razy
                */
                i++;
            }
        }
        return temp20cm_Average;
    }

    private static double getTemp2mAverage(Station[] stationArray) {
        double temp2m_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp2m();
                temp2m_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp2m_Average;

    }

    private static double getTemp0mAverage(Station[] stationArray) {
        double temp0m_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp0m();
                temp0m_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp0m_Average;
    }

    private static double getTemp5cmAverage(Station[] stationArray) {
        double temp5cm_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getTemp5cm();
                temp5cm_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return temp5cm_Average;
    }

    private static double getWindDir(Station[] stationArray) {
        double wind_die_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getWindDir();
                wind_die_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return wind_die_Average;
    }

    private static double getWindSpeed(Station[] stationArray) {
        double wind_speed_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getWindSpeed();
                wind_speed_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return wind_speed_Average;
    }

    private static double getPrecipitation(Station[] stationArray) {
        double precipitation_Average = 0;
        double Sum = 0;

        for (int i = 0; i < stationArray.length; i++) {
            if (ifMeasurementDone10MinutesAgo(stationArray, i)) {
                Sum += stationArray[i].getPrecipitation();
                precipitation_Average = Sum / stationArray.length;
            } else {
                i++;
            }
        }
        return precipitation_Average;
    }

//-----------------------------------------------------------------------------------
    /*
        ja bym to tak zrobił
     */
    protected static Station getAverage(Station[] array) {
        Station sum = new Station();
        long count = Stream.of(array)
                //filtruje wyniki które są wczesniej niż 10 minut temu
                .filter(Weather::ifMeasurementDone10MinutesAgo)
                //sumuje poszczegolne wyniki
                .peek(station -> addResultsFromStation(sum, station))
                .count();
        //wyliczam średnią
        return calculateAverageResults(count, sum);
    }

    private static Station calculateAverageResults(long count, Station sum) {
        Station average = new Station();
        average.setPrecipitation(sum.getPrecipitation()/ count);
        average.setTemp0m(sum.getTemp0m()/ count);
        average.setTemp2m(sum.getTemp2m()/ count);
        average.setTemp5cm(sum.getTemp5cm()/ count);
        average.setTemp20cm(sum.getTemp20cm()/ count);
        average.setWindDir(sum.getWindDir()/ count);
        average.setWindSpeed(sum.getWindSpeed()/ count);
        return average;
    }

    private static void addResultsFromStation(Station sum, Station station) {
        sum.setPrecipitation(sum.getPrecipitation() + station.getPrecipitation());
        sum.setTemp0m(sum.getTemp0m() + station.getTemp0m());
        sum.setTemp2m(sum.getTemp2m() + station.getTemp2m());
        sum.setTemp5cm(sum.getTemp5cm() + station.getTemp5cm());
        sum.setTemp20cm(sum.getTemp20cm() + station.getTemp20cm());
        sum.setWindDir(sum.getWindDir() + station.getWindDir());
        sum.setWindSpeed(sum.getWindSpeed() + station.getWindSpeed());
    }

    private static boolean ifMeasurementDone10MinutesAgo(Station station) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(station.getMeasureTime(), formatter).minusHours(1);

        return dateTime.isAfter(LocalDateTime.now().minusMinutes(10));

    }
}

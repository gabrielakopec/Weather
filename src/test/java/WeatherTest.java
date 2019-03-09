import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void gettemp20cm_Average_shouldReturnReturnAvgTempForMeasurementsFromLast10Minutes() {
        Station[] array = getStations();

        double average = Weather.getTemp20cmAverage(array);

        assertEquals(25.0, average);
    }

    @Test
    void getAverage_shouldReturnAvgResultsForMesurementsFromLast10Minutes() {
        Station[] array = getStations();

        Station average = Weather.getAverage(array);

        assertEquals(25.0, average.getTemp20cm());
    }

    private Station[] getStations() {
        Station[] array = new Station[3];
        //to nie wchodzi do obliczeń bo pomiar był 15 minut temu
        array[0] = getStation(LocalDateTime.now().plusHours(1).minusMinutes(15), 25.0);
        //to wchodzi do obliczeń bo pomiar był 9 minut temu
        array[1] = getStation(LocalDateTime.now().plusHours(1).minusMinutes(9), 35.0);
        //to wchodzi do obliczen bo pomiar był 7 minut temu
        array[2] = getStation(LocalDateTime.now().plusHours(1).minusMinutes(7), 15.0);
        //z tych dwoch które wchodzą średnia: (35+15)/2 = 50/2 = 25
        return array;
    }

    private Station getStation(LocalDateTime dateTime, double tempeature) {
        Station station = new Station();
        station.setTemp20cm(tempeature);
        station.setMeasureTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime));
        return station;
    }

}
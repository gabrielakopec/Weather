import com.fasterxml.jackson.annotation.JsonProperty;

public class Station {

private int id;

private double temp20cm;
private double temp2m;
private double temp0m;
private double temp5cm;
/*
    w javie uzywa się raczej camel case'a, do załatwienia problemu parsowania
    można użyć adnotacji @JsonProperty
 */
@JsonProperty("wind_dir")
private double windDir;
@JsonProperty("wind_speed")
private double windSpeed;
private double precipitation;
@JsonProperty("measure_time")
private String measureTime;


    public Station() {
    }

    public Station(int id, double temp20cm, double temp2m, double temp0m, double temp5cm, double windDir, double windSpeed, double precipitation, String measureTime) {
        this.id = id;
        this.temp20cm = temp20cm;
        this.temp2m = temp2m;
        this.temp0m = temp0m;
        this.temp5cm = temp5cm;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
        this.measureTime = measureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp20cm() {
        return temp20cm;
    }

    public void setTemp20cm(double temp20cm) {
        this.temp20cm = temp20cm;
    }

    public double getTemp2m() {
        return temp2m;
    }

    public void setTemp2m(double temp2m) {
        this.temp2m = temp2m;
    }

    public double getTemp0m() {
        return temp0m;
    }

    public void setTemp0m(double temp0m) {
        this.temp0m = temp0m;
    }

    public double getTemp5cm() {
        return temp5cm;
    }

    public void setTemp5cm(double temp5cm) {
        this.temp5cm = temp5cm;
    }

    public double getWindDir() {
        return windDir;
    }

    public void setWindDir(double windDir) {
        this.windDir = windDir;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", temp20cm=" + temp20cm +
                ", temp2m=" + temp2m +
                ", temp0m=" + temp0m +
                ", temp5cm=" + temp5cm +
                ", windDir=" + windDir +
                ", windSpeed=" + windSpeed +
                ", precipitation=" + precipitation +
                ", measureTime='" + measureTime + '\'' +
                '}';
    }
}

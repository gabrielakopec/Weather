public class Station {

private int id;

private double temp20cm;
private double temp2m;
private double temp0m;
private double temp5cm;
private double wind_dir;
private double wind_speed;
private double precipitation;
private String measure_time;


    public Station() {
    }

    public Station(int id, double temp20cm, double temp2m, double temp0m, double temp5cm, double wind_dir, double wind_speed, double precipitation, String measure_time) {
        this.id = id;
        this.temp20cm = temp20cm;
        this.temp2m = temp2m;
        this.temp0m = temp0m;
        this.temp5cm = temp5cm;
        this.wind_dir = wind_dir;
        this.wind_speed = wind_speed;
        this.precipitation = precipitation;
        this.measure_time = measure_time;
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

    public double getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(double wind_dir) {
        this.wind_dir = wind_dir;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public String getMeasure_time() {
        return measure_time;
    }

    public void setMeasure_time(String measure_time) {
        this.measure_time = measure_time;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", temp20cm=" + temp20cm +
                ", temp2m=" + temp2m +
                ", temp0m=" + temp0m +
                ", temp5cm=" + temp5cm +
                ", wind_dir=" + wind_dir +
                ", wind_speed=" + wind_speed +
                ", precipitation=" + precipitation +
                ", measure_time='" + measure_time + '\'' +
                '}';
    }
}

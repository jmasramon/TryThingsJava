import java.util.ArrayList;

public class WeatherData implements Publisher {

    interface WeatherDisplay extends Subscriber, Display {}

    ArrayList<WeatherDisplay> displays;
    SensorData data;

    public void measurementsChanged() {
        data = new SensorData(getTemp(), getHum(), getPres());

        updateDisplays();
    }

    private void updateDisplays() {
        notifySubscribers();
    }

    public <T extends Subscriber & Display> void addDisplay(T display) {
        subscribe(display);
    }

    private float getTemp() {
        return 0;
    }
    private float getHum() {
        return 0;
    }
    private float getPres() {
        return 0;
    }

    @Override
    public void subscribe(Subscriber display) {
        displays.add((WeatherDisplay) display);
    }

    @Override
    public void unsubscribe(Subscriber display) {
        displays.remove(display);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber display: displays) {
            display.notify(data);
        }
    }
}

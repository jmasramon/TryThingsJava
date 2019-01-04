package weatherStation;

import weatherStation.SensorData;

public interface Subscriber {
    void notify(SensorData data);
}

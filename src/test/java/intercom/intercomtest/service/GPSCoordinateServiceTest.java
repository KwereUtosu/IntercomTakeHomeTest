package intercom.intercomtest.service;

import intercom.intercomtest.model.GPSCoordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GPSCoordinateServiceTest {

    @Test
    public void compareWhenFirstDistanceIsNull() {
        GPSCoordinate location2 = new GPSCoordinate(-53.339428, -6.257664);
        Double expectedDistance = 100d;

        assertEquals(-1, expectedDistance.compareTo(GPSCoordinateService.getDistanceInKm(null, location2)));
    }

    @Test
    public void getDistanceInKm() {
        GPSCoordinate location1 = new GPSCoordinate(400d, -500d);
        GPSCoordinate location2 = new GPSCoordinate(-350d, 200d);
        Double expectedDistance = 5195.4406931897d;

        assertEquals(1, expectedDistance.compareTo(GPSCoordinateService.getDistanceInKm(location1, location2)));

    }

}

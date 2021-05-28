package intercom.intercomtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GPSCoordinate {

    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "latitude: " + latitude + ", longitude: " + longitude;
    }

}

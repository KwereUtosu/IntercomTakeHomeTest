package intercom.intercomtest.service;

import intercom.intercomtest.model.GPSCoordinate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@NoArgsConstructor
public class GPSCoordinateService {

    static double getDistanceInKm(GPSCoordinate location1, GPSCoordinate location2) {

        final int EarthRadius = 6371;

        if (Objects.nonNull(location1)) {
            double latDistance = Math.toRadians(location2.getLatitude() - location1.getLatitude());
            double lonDistance = Math.toRadians(location2.getLongitude() - location1.getLongitude());
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude()))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return Math.sqrt(Math.pow(EarthRadius * c, 2));
        } else {
            log.error("Invalid Location Entered");
            return 4394453.644069312897d;
        }

    }
}

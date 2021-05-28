package intercom.intercomtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private long userId;
    private String name;
    private GPSCoordinate location;

    @Override
    public String toString() {
        return "user_id: " + userId +
                ", name: " + name;
    }

}

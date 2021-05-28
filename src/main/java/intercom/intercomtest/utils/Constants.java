package intercom.intercomtest.utils;

import intercom.intercomtest.model.GPSCoordinate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {

    public static final String CUSTOMER_INPUT_URL = "https://s3.amazonaws.com/intercom-take-home-test/customers.txt";
    public static final String CUSTOMER_OUTPUT_URL = "output.txt";

    public static final Charset JSON_FILE_ENCODING = StandardCharsets.UTF_8;

    public static final GPSCoordinate INTERCOM_GPS_COORDINATE = new GPSCoordinate(53.339428, -6.257664);
    public static final Double REQUIRED_RANGE_IN_KM = 100d;

    public static final String USER_ID = "user_id";
    public static final String CUSTOMER_NAME = "name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


}

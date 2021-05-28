package intercom.intercomtest.utils;

import intercom.intercomtest.model.Customer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsTest {

    @Test
    public void getCustomers() throws IOException {

        String exampleString = "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}\n" +
                "{\"latitude\": \"51.8856167\", \"user_id\": 2, \"name\": \"Ian McArdle\", \"longitude\": \"-10.4240951\"}\n" +
                "{\"latitude\": \"52.3191841\", \"user_id\": 3, \"name\": \"Jack Enright\", \"longitude\": \"-8.5072391\"}";

        InputStream inputStream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));

        List<Customer> customers = FileUtils.getCustomers(inputStream);

        assert (customers.size() == 3);

    }

    @Test
    public void convertJsonListToCustomerList() throws JSONException {

        List<JSONObject> customerJsonObjects = new ArrayList<>();

        customerJsonObjects.add(new JSONObject("{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}"));
        customerJsonObjects.add(new JSONObject("{\"latitude\": \"54.0894797\", \"user_id\": 8, \"name\": \"Eoin Ahearn\", \"longitude\": \"-6.18671\"}"));
        customerJsonObjects.add(new JSONObject("{\"latitude\": \"53.807778\", \"user_id\": 28, \"name\": \"Charlie Halligan\", \"longitude\": \"-7.714444\"}"));
        customerJsonObjects.add(new JSONObject("{\"latitude\": \"54.180238\", \"user_id\": 17, \"name\": \"Patricia Cahill\", \"longitude\": \"-5.920898\"}"));
        customerJsonObjects.add(new JSONObject("{\"latitude\": \"53.761389\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}"));

        List<Customer> customers = FileUtils.convertJsonListToCustomerList(customerJsonObjects);

        assertEquals(5, customers.size());
        assertEquals(12, customers.get(0).getUserId());
        assertEquals("Eoin Ahearn", customers.get(1).getName());
        assertEquals(0d, Double.compare(53.807778d, customers.get(2).getLocation().getLatitude()));
        assertEquals(0d, Double.compare(-5.920898d, customers.get(3).getLocation().getLongitude()));

    }

}

package intercom.intercomtest.utils;

import intercom.intercomtest.model.Customer;
import intercom.intercomtest.model.GPSCoordinate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class FileUtils {

    public static List<Customer> getCustomers(InputStream inputStream) throws IOException {
        List<JSONObject> customers = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Constants.JSON_FILE_ENCODING))) {
            String line;
            while ((line = rd.readLine()) != null) {
                customers.add(new JSONObject(line));
            }
        }
        return convertJsonListToCustomerList(customers);
    }

    static List<Customer> convertJsonListToCustomerList(List<JSONObject> customerJsonObjects) {
        List<Customer> customers = new ArrayList<>();
        for (JSONObject jsonObject : customerJsonObjects) {
            GPSCoordinate location = new GPSCoordinate(
                    Double.parseDouble(jsonObject.get(Constants.LATITUDE).toString()),
                    Double.parseDouble(jsonObject.get(Constants.LONGITUDE).toString()));
            Customer customer = new Customer(
                    Long.parseLong(jsonObject.get(Constants.USER_ID).toString()),
                    jsonObject.get(Constants.CUSTOMER_NAME).toString(),
                    location
            );
            customers.add(customer);
        }

        return customers;
    }

    public static void writeToFile(List<Customer> customers) {

        try {
            File output = new File(Constants.CUSTOMER_OUTPUT_URL);
            output.createNewFile();

            if (output.length() == 0) {

                FileWriter writer = new FileWriter(output);
                customers.forEach(customer -> {
                    try {
                        writer.write(customer.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        log.error("An Error Occured writing to File: ", e);
                    }
                });
                writer.close();
            }
        } catch (IOException e) {
            log.error("An Error Occured Creating File: ", e);
        }

    }

}

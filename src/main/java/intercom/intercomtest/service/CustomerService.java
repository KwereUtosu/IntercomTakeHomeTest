package intercom.intercomtest.service;

import intercom.intercomtest.model.Customer;
import intercom.intercomtest.model.GPSCoordinate;
import intercom.intercomtest.utils.Constants;
import intercom.intercomtest.utils.FileUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class CustomerService {

    public static List<Customer> getCustomersWithinDistance(List<Customer> customersToFilter, GPSCoordinate filterLocation, Double kilometerRange) {
        List<Customer> customersInRange = new ArrayList<>();

        for (Customer customer : customersToFilter) {
            if (kilometerRange.compareTo(GPSCoordinateService.getDistanceInKm(customer.getLocation(), filterLocation)) > 0) {
                customersInRange.add(customer);
            }
        }

        return customersInRange;
    }

    public static List<Customer> processCustomerData(String fileUrl, double validRange) {
        log.info("Getting List of customers within {}km Range of ({})", validRange, Constants.INTERCOM_GPS_COORDINATE);

        try {

            //Check if internet is avaliable to fetch data, else fetch from resources
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            String fileLocation = connection.getAllowUserInteraction() ? fileUrl : "customer.txt";

            log.info("Getting Customer data from => {}", fileLocation);

            Resource resource = new ClassPathResource(fileLocation);
            InputStream inputStream = resource.getInputStream();

            List<Customer> customers = FileUtils.getCustomers(inputStream);
            List<Customer> customersInRange = getCustomersWithinDistance(
                    customers, Constants.INTERCOM_GPS_COORDINATE, validRange);

            log.info("Total number of customers read from file => {}", customers.size());
            log.info("Total number of customers within {}km range is => {}", validRange, customersInRange.size());

            customersInRange.sort(Comparator.comparingLong(Customer::getUserId));
            StringBuilder sb = new StringBuilder();
            for (Customer customer : customersInRange) {
                sb.append(customer).append(System.lineSeparator());
            }

            //Writing to output.txt
            FileUtils.writeToFile(customersInRange);

            log.info("List of customers in range => \n\n{}", sb);
            return customersInRange;

        } catch (Exception e) {
            log.error("An Error has occurred, Exiting Application: ", e);
            return new ArrayList<>();
        }

    }
}

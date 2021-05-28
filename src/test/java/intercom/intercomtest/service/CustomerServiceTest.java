package intercom.intercomtest.service;


import intercom.intercomtest.model.Customer;
import intercom.intercomtest.model.GPSCoordinate;
import intercom.intercomtest.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceTest {

    @Test
    public void getCustomersWithinDistance() {

        List<Customer> customersToFilter = new ArrayList<>();
        customersToFilter.add(new Customer(2, "Chichi", new GPSCoordinate(53.2451022d, -6.238335d)));
        customersToFilter.add(new Customer(8, "Olay", new GPSCoordinate(55.033d, -8.112d)));
        customersToFilter.add(new Customer(4, "Chris", new GPSCoordinate(53d, -7d)));
        customersToFilter.add(new Customer(6, "Joy", new GPSCoordinate(51.92893d, -10.27699d)));
        customersToFilter.add(new Customer(7, "Kwere", new GPSCoordinate(52.966d, -6.463)));

        GPSCoordinate filterLocation = new GPSCoordinate(53.339428, -6.257664);

        Double kilometerRange = 100d;

        List<Customer> customersInRange = CustomerService.getCustomersWithinDistance(customersToFilter, filterLocation, kilometerRange);

        assertEquals(3, customersInRange.size());
        assertEquals(2, customersInRange.get(0).getUserId());
        assertEquals(4, customersInRange.get(1).getUserId());
        assertEquals(7, customersInRange.get(2).getUserId());

    }

    @Test
    public void processCustomerData() {

        List<Customer> customers = CustomerService.processCustomerData(Constants.CUSTOMER_INPUT_URL,
                Constants.REQUIRED_RANGE_IN_KM);

        assertEquals("Nora Dempsey", customers.get(1).getName());
        assertEquals(13, customers.get(6).getUserId());

    }

    @Test
    public void processCustomerDataError() {

        List<Customer> customers = CustomerService.processCustomerData("Put a Url that doesn't exist",
                Constants.REQUIRED_RANGE_IN_KM);

        assertTrue(customers.isEmpty());

    }
}

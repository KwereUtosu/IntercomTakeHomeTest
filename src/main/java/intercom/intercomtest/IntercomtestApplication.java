package intercom.intercomtest;

import intercom.intercomtest.service.CustomerService;
import intercom.intercomtest.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntercomtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntercomtestApplication.class, args);

        //if an arg is set for validRange, use the arg
        final double validRange = args.length > 0 ? Double.parseDouble(args[0]) : Constants.REQUIRED_RANGE_IN_KM;

        // if an arg is set for fileUrl, use it as the Customer Url
        final String fileUrl = args.length > 1 ? args[1] : Constants.CUSTOMER_INPUT_URL;
//        final String fileUrl = Constants.CUSTOMER_INPUT_URL ? args[1] : Constants.CUSTOMER_INPUT_URL;

        CustomerService.processCustomerData(fileUrl, validRange);
    }

}

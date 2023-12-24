package ba.edu.ibu.awslambdainventoryreport;

import ba.edu.ibu.awslambdainventoryreport.dto.OrderCustomDTO;
import ba.edu.ibu.awslambdainventoryreport.dto.OrderItemCustomDTO;
import ba.edu.ibu.awslambdainventoryreport.model.Order;
import ba.edu.ibu.awslambdainventoryreport.repository.OrderRepository;
import ba.edu.ibu.awslambdainventoryreport.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SpringBootApplication
public class AwsLambdaInventoryReportApplication {

    public AwsLambdaInventoryReportApplication(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaInventoryReportApplication.class, args);
    }
    @Autowired
    private MailgunEmailSender mailgunEmailSender;
    private final OrderService orderService;
    @Bean
    public Function<String, String> sendEmailFunction() {
        return (email) -> {
            String subject = "Orders Report";

            // Retrieve data using custom findAllCustom function
            List<OrderCustomDTO> orders = orderService.findAllCustom();

            // Format data as plain text
            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Dear Customer,\n");
            emailContent.append("Here is the summary of your todays orders:\n");

            for (OrderCustomDTO order : orders) {
                emailContent.append("Order ID: ").append(order.getId()).append("\n");
                emailContent.append("Order Date: ").append(order.getOrderDate()).append("\n");

                for (OrderItemCustomDTO product : order.getProducts()) {
                    emailContent.append("Product Name: ").append(product.getProductName()).append("\n");
                    emailContent.append("Quantity: ").append(product.getQuantity()).append("\n");
                    emailContent.append("Price: ").append(product.getPrice()).append("\n");
                    emailContent.append("Subtotal: ").append(product.getSubtotal()).append("\n");
                }

                emailContent.append("Total: ").append(order.getTotal()).append("\n");
                emailContent.append("\n---\n");
            }

            // You can customize the email sending logic here
            String response = mailgunEmailSender.sendEmail(email, subject, emailContent.toString());

            // Return the response from the email sending process
            return "Email sending response: " + response;
        };
    }

}

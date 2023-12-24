package ba.edu.ibu.awslambdainventoryreport;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunEmailSender {

    @Value("${email.mailgun.domain}")
    private String mailgunDomain;

    @Value("${email.mailgun.from-email}")
    private String fromEmail;

    @Value("${email.mailgun.username}")
    private String apiKeyUsername;

    @Value("${email.mailgun.password}")
    private String apiKey;

    public String sendEmail(String to, String subject, String text) {
        try {
            // Set up Mailgun API request
            HttpResponse<JsonNode> request = Unirest.post(mailgunDomain + "/messages")
                    .basicAuth(apiKeyUsername, apiKey)
                    .queryString("from", fromEmail)
                    .queryString("to", to)
                    .queryString("subject", subject)
                    .queryString("text", text)
                    .asJson();

            // Return the response from Mailgun API
            return "Mailgun API Response: " + request.getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
    public String sendEmailWithContentType(String to, String subject, String text, String contentType) {
        try {
            // Set up Mailgun API request
            HttpResponse<JsonNode> request = Unirest.post(mailgunDomain + "/messages")
                    .basicAuth(apiKeyUsername, apiKey)
                    .queryString("from", fromEmail)
                    .queryString("to", to)
                    .queryString("subject", subject)
                    .queryString("text", text)
                    .header("Content-Type", contentType) // Set the content type
                    .asJson();

            // Return the response from Mailgun API
            return "Mailgun API Response: " + request.getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}


package albert.practice.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

@Slf4j
public class HttpClientTest {

    private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {
        HttpClientTest test = new HttpClientTest();

        String url = "http://webe.cht.com.tw/";
        test.invokeGetMethod1(url);
        test.invokeGetMethod2(url);

        // String responseStr = "[1]msgid=0001125715statuscode=1AccountPoint=197";
        // int from = responseStr.indexOf("statuscode=");
        // int to = responseStr.indexOf("AccountPoint=");
        // String tmpStr = StringUtils.substring(responseStr, from, to);
        // log.info("tmpStr = " + tmpStr);
        //
        // String statusCode = StringUtils.substring(tmpStr, tmpStr.length() - 1, tmpStr.length());
        // log.info("statusCode = " + statusCode);

    }

    public void invokeGetMethod1(String url) {
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod(url);

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            System.out.println(new String(responseBody));

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }

    public void invokeGetMethod2(String url) throws IOException {
        URL obj = null;
        BufferedReader bufferedReader = null;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            // add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            log.info("\nSending 'GET' request to URL : " + url);
            log.info("Response Code : " + responseCode);

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = "";
            StringBuffer response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            // print result
            log.info(response.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

}

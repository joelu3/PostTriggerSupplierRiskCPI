package customer.supplierprofiletrigger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Legacy java libraries
import java.net.*;
import java.io.*;
//Unirest libraries
/*
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
*/
//OkHttp3 libraries
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SpringBootApplication
public class PostTrigger {

    static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(PostTrigger.class, args);
        System.out.println("hello1");
        try{

            String url="https://v469-ifl.avtsbhf.eu1.hana.ondemand.com/http/supplierprofilerequest";
            String input="{\"roles\":[\"ESMessaging.send\"]}";
            //String output = javaPost(url,input);
            String output = okhttpPost(url, input);
            System.out.println("output= "+output);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //OkHTTP3
    static String okhttpPost(String url, String json) throws IOException{
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .header("Authorization", "Basic UDIwMDE4MDQ0MDA6ZHVqZ3k0LW1lZ2R1ZC1mYWpXYWc=")
            .url(url)
            .post(body)
            .build();
        try(Response response = client.newCall(request).execute()){
            System.out.println("code ="+response.code());
            return response.body().string();
        }
    }
    //Java 
    /*   
    static String javaPost(String url, String json) throws IOException, MalformedURLException{
            URL db_url=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)db_url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Authorization", "Basic UDIwMDE4MDQ0MDA6ZHVqZ3k0LW1lZ2R1ZC1mYWpXYWc=");
             OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            if (conn.getResponseCode()!=HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed: HTTP error code : "+conn.getResponseCode());
            }
        
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            System.out.println("Output from Server..... \n");
            while ((output=br.readLine())!=null){
                System.out.println(output);
            }
            return output;
    }
    */
    //Unirest
    /*
    static String unirestPost(String url, String json) throws IOException, MalformedURLException{
            //Unirest 
            com.mashape.unirest.http.HttpResponse<JsonNode> response = (HttpResponse)Unirest.post(url)
                .header("accept", "application/json")
                .basicAuth("P2001804400","dujgy4-megdud-fajWag");
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
    }
    */
}
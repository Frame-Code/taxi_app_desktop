package shared.utils;

import lombok.extern.apachecommons.CommonsLog;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CommonsLog
public class HttpClientUtil {
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();


    public static Request.Builder getRequest(String url) throws IOException {
        return new Request.Builder()
                .url(url);

    }

    public static String getResponse(String url, Map<String, String> headers) throws IOException, NullPointerException {
        Request.Builder rb = getRequest(url);
        headers.forEach(rb::addHeader);
        Request request = rb.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public static String getResponse(String url, Map<String, String> headers, Map<String, String> params) throws IOException, NullPointerException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        params.forEach(urlBuilder::addQueryParameter);

        Request.Builder rb = new Request.Builder();
        rb.url(urlBuilder.build().toString());
        headers.forEach(rb::addHeader);

        Request request = rb.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            log.info("Http code response: " + response.code());
            return response.body().string();
        }
    }


}

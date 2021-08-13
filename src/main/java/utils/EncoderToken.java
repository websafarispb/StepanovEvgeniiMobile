package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;

public class EncoderToken {

    private String encodedToken;

    @Test
    public void encodeTest() {
        try {
            encodedToken = URLEncoder.encode(System.getenv("TOKEN"), StandardCharsets.UTF_8.name());
        } catch (
            UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encodedToken);
    }
}

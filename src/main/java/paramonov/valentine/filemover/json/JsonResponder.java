package paramonov.valentine.filemover.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Utility class to map response bodies to JSON
 */
public class JsonResponder {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * sets Content-Type header and writes body as JSON
     *
     * @param response the response object
     * @param body     object to be converted to json
     * @throws IOException if something went wrong during the serialization process
     */
    public static void respondWithJson(HttpServletResponse response, Object body)
        throws IOException
    {
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(body));
    }
}

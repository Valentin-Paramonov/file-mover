package paramonov.valentine.filemover.mover;

import paramonov.valentine.filemover.context.ContextAttribute;
import paramonov.valentine.filemover.json.JsonResponder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to get processed file list as JSON array. Check web.xml to see which URL it maps
 * to
 */
public class ProcessedFileListEndpoint extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        ProcessedFileLog processedFileLog = (ProcessedFileLog) req.getServletContext()
            .getAttribute(ContextAttribute.fileLog);
        JsonResponder.respondWithJson(res, processedFileLog.getProcessedFiles());
    }
}

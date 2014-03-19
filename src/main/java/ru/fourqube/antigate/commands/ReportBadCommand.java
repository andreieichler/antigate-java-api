package ru.fourqube.antigate.commands;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fourqube.antigate.AntigateSettings;
import ru.fourqube.antigate.Consts;
import ru.fourqube.antigate.exceptions.AntigateCode;
import ru.fourqube.antigate.exceptions.AntigateException;

import java.io.IOException;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class ReportBadCommand extends AbstractCommand<Void> {
    private final static Logger log = LoggerFactory.getLogger(ReportBadCommand.class);
    private final static String ACTION = "reportBad";

    private final String id;

    public ReportBadCommand(String id, AntigateSettings settings) {
        super(settings);
        this.id = id;
    }

    @Override
    protected Void parseResponse(HttpResponse execute) throws IOException {
        String response = EntityUtils.toString(execute.getEntity());
        log.debug("Response: {}", response);
        if (response.startsWith(Consts.OK_PREFIX)) {
            return null;
        } else {
            AntigateCode errorCode = AntigateCode.fromString(response);
            throw new AntigateException(errorCode);
        }
    }

    @Override
    protected void applyParams(URIBuilder uriBuilder) {
        uriBuilder.setPath(Consts.PATH_RES)
                .setParameter("id", id)
                .setParameter("action", ACTION);
    }
}

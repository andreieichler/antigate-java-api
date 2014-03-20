package ru.fourqube.antigate.commands;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fourqube.antigate.AntigateSettings;
import ru.fourqube.antigate.CaptchaStatus;
import ru.fourqube.antigate.Consts;
import ru.fourqube.antigate.exceptions.AntigateCode;
import ru.fourqube.antigate.exceptions.AntigateException;

import java.io.IOException;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class GetStatusCommand extends AbstractCommand<CaptchaStatus> {
    private final static Logger log = LoggerFactory.getLogger(GetStatusCommand.class);

    private final static String ACTION = "get";
    private final String id;

    public GetStatusCommand(String id, AntigateSettings settings) {
        super(settings);
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }

    @Override
    protected CaptchaStatus parseResponse(HttpResponse execute) throws IOException {
        CaptchaStatus status = new CaptchaStatus();
        String response = EntityUtils.toString(execute.getEntity());
        log.debug("Response: {}", response);
        if (response.startsWith(Consts.OK_PREFIX)) {
            status.setText(response.replace(Consts.OK_PREFIX, ""));
            status.setReady(true);
        } else if (response.contains("CAPTCHA_NOT_READY")) {
            status.setReady(false);
        } else if (response.contains("ERROR")) {
            AntigateCode errorCode = AntigateCode.fromString(response);
            throw new AntigateException(errorCode);
        }
        return status;
    }

    @Override
    protected void applyParams(URIBuilder uriBuilder) {
        uriBuilder.setPath(Consts.PATH_RES)
                .setParameter("action", ACTION)
                .addParameter("id", id);
    }
}

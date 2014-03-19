package ru.fourqube.antigate.commands;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fourqube.antigate.AntigateSettings;
import ru.fourqube.antigate.Consts;
import ru.fourqube.antigate.exceptions.AntigateException;

import java.io.IOException;

/**
 * Команда получения текущего баланса
 *
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class GetBalanceCommand extends AbstractCommand<Double> {
    private final static Logger log = LoggerFactory.getLogger(GetBalanceCommand.class);
    private final static String ACTION = "getbalance";

    public GetBalanceCommand(AntigateSettings settings) {
        super(settings);
    }

    @Override
    protected Double parseResponse(HttpResponse execute) throws IOException {
        String response = EntityUtils.toString(execute.getEntity());
        log.debug("Response: {}", response);
        try {
            return Double.parseDouble(response);
        } catch (NumberFormatException e) {
            throw new AntigateException(e.getMessage(), e);
        }
    }

    @Override
    protected void applyParams(URIBuilder uriBuilder) {
        uriBuilder.setPath(Consts.PATH_RES)
                .setParameter("action", ACTION);
    }

}

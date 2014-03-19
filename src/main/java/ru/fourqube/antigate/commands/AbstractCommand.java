package ru.fourqube.antigate.commands;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.fourqube.antigate.AntigateSettings;
import ru.fourqube.antigate.exceptions.AntigateException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public abstract class AbstractCommand<T> implements Command<T> {
    protected final AntigateSettings settings;

    public AbstractCommand(AntigateSettings settings) {
        this.settings = settings;
    }

    protected abstract T parseResponse(HttpResponse execute) throws IOException;

    @Override
    public T execute() throws AntigateException {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = createUriBuilder();
            applyParams(uriBuilder);
            HttpUriRequest httpGet = createRequest(uriBuilder.build());
            HttpResponse execute = httpClient.execute(httpGet);
            return parseResponse(execute);
        } catch (IOException | URISyntaxException e) {
            throw new AntigateException(e.getMessage(), e);
        }
    }

    protected URIBuilder createUriBuilder() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(settings.getHost())
                .setParameter("key", settings.getKey());
        return uriBuilder;
    }

    protected HttpUriRequest createRequest(URI uri) {
        return new HttpGet(uri);
    }

    protected void applyParams(URIBuilder uriBuilder) {}
}

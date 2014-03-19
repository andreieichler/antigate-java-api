package ru.fourqube.antigate.commands;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fourqube.antigate.AntigateSettings;
import ru.fourqube.antigate.Consts;
import ru.fourqube.antigate.exceptions.AntigateCode;
import ru.fourqube.antigate.exceptions.AntigateException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class UploadCommand extends AbstractCommand<String> {
    private final static Logger log = LoggerFactory.getLogger(UploadCommand.class);

    private File file;
    private URL url;

    public UploadCommand(File file, AntigateSettings settings) {
        super(settings);
        this.file = file;
    }

    public UploadCommand(String filePath, AntigateSettings settings) {
        super(settings);
        this.file = new File(filePath);
    }

    public UploadCommand(URL url, AntigateSettings settings) {
        super(settings);
        this.url = url;
    }

    @Override
    protected URIBuilder createUriBuilder() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(settings.getHost()).setPath(Consts.PATH_IN);
        return uriBuilder;
    }

    @Override
    protected void applyParams(URIBuilder uriBuilder) {
    }

    @Override
    protected HttpUriRequest createRequest(URI uri) {
        HttpPost post = new HttpPost(uri);
        MultipartEntity reqEntity = createMultipartEntity();
        post.setEntity(reqEntity);
        return post;
    }

    @Override
    protected String parseResponse(HttpResponse execute) throws IOException {
        String response = EntityUtils.toString(execute.getEntity());
        log.debug("Response: {}", response);
        if (response.startsWith(Consts.OK_PREFIX)) {
            return response.replace(Consts.OK_PREFIX, "");
        } else {
            AntigateCode errorCode = AntigateCode.fromString(response);
            throw new AntigateException(errorCode);
        }
    }

    private MultipartEntity createMultipartEntity() throws AntigateException {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            addPartToEntity(entity, "key", settings.getKey());
            addPartToEntity(entity, "method", "post");
            addPartToEntity(entity, "phrase", settings.isTwoWords());
            addPartToEntity(entity, "regsense", settings.isRegisterSensitive());
            addPartToEntity(entity, "numeric", settings.getNumeric());
            addPartToEntity(entity, "calc", settings.isCalc());
            addPartToEntity(entity, "min_len", String.valueOf(settings.getMinLength()));
            addPartToEntity(entity, "max_len", String.valueOf(settings.getMaxLength()));
            addPartToEntity(entity, "is_russian", settings.isRussian());
            addPartToEntity(entity, "max_bid", String.valueOf(settings.getMaxBid()));
            FileBody fileBody = new FileBody(getFile());
            entity.addPart("file", fileBody);
            return entity;
        } catch (IOException e) {
            throw new AntigateException(e.getMessage(), e);
        }
    }

    private void addPartToEntity(final MultipartEntity entity, final String name, final String value) throws UnsupportedEncodingException {
        if (value != null) {
            entity.addPart(name, new StringBody(value));
        }
    }

    private File getFile() throws IOException {
        if (file != null) {
            return file;
        } else if (url != null) {
            file = File.createTempFile("captcha", "tmp");
            FileUtils.copyURLToFile(url, file);
            return file;
        } else {
            return null;
        }
    }
}

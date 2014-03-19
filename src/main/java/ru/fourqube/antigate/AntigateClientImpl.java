package ru.fourqube.antigate;

import ru.fourqube.antigate.commands.GetBalanceCommand;
import ru.fourqube.antigate.commands.GetStatusCommand;
import ru.fourqube.antigate.commands.ReportBadCommand;
import ru.fourqube.antigate.commands.UploadCommand;

import java.io.File;
import java.net.URL;

/**
 * Имплементация клиента antigate.com
 *
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
class AntigateClientImpl implements AntigateClient {
    private AntigateSettings settings;

    public AntigateClientImpl(AntigateSettings settings) {
        this.settings = settings;
    }

    @Override
    public String upload(String filePath) {
        return new UploadCommand(filePath, settings).execute();
    }

    @Override
    public String upload(File file) {
        return new UploadCommand(file, settings).execute();
    }

    @Override
    public String upload(URL url) {
        return new UploadCommand(url, settings).execute();
    }

    @Override
    public CaptchaStatus checkStatus(String id) {
        return new GetStatusCommand(id, settings).execute();
    }

    @Override
    public void reportBad(String id) {
        new ReportBadCommand(id, settings);
    }

    @Override
    public double getBalance() {
        return new GetBalanceCommand(settings).execute();
    }
}

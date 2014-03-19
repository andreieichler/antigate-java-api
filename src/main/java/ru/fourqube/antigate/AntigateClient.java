package ru.fourqube.antigate;

import java.io.File;
import java.net.URL;

/**
 * Интерфейс взаимодействия с антигейтом
 *
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public interface AntigateClient {
    /**
     *
     * @param filePath путь к файлу изображения капчи
     * @return идентификатор в antigate.com, по которому можно забрать результат
     */
    String upload(String filePath);

    /**
     * @param file файл изображения капчи
     * @return идентификатор в antigate.com, по которому можно забрать результат
     */
    String upload(File file);

    /**
     * @param url адрес к изображению капчи
     * @return идентификатор в antigate.com, по которому можно забрать результат
     */
    String upload(URL url);

    /**
     * Проверка статуса
     *
     * @param id идентификатор в antigate.com
     * @return Статус обработки капчи
     */
    CaptchaStatus checkStatus(String id);

    /**
     * Отправить отчет о неправильно распознаной капчи
     * @param id идентификатор в antigate.com
     */
    void reportBad(String id);

    /**
     * @return текущий баланс на счету
     */
    double getBalance();
}

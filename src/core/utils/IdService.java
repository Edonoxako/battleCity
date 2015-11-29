package core.utils;

/**
 * Created by Евгений on 24.11.2015.
 *
 * Вспомогательный класс для генерации уникальных айдишников
 */
public class IdService {

    private static int lastId = 0;

    public static int generateId() {
        return lastId++;
    }

}

package core.utils;

/**
 * Created by ������� on 24.11.2015.
 *
 * ��������������� ����� ��� ��������� ���������� ����������
 */
public class IdService {

    private static int lastId = 0;

    public static int generateId() {
        return lastId++;
    }

}

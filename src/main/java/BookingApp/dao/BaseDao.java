package BookingApp.dao;

import BookingApp.logger.CustomLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseDao<T> implements Dao<T>{
    private final File file;

    public BaseDao(File file) {
        this.file = file;
    }

    @Override
    public boolean saveToFile(List<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<T> readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException e) {
            CustomLogger.error("File not found");
            return new ArrayList<>();
        } catch (ClassNotFoundException ex) {
            CustomLogger.error("Class not found");
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<T> get(T t){
        return readFromFile()
                .stream()
                .filter(t1 -> t1.equals(t))
                .findAny();
    }

    @Override
    public boolean save(T t) {
        List<T> list = new ArrayList<>();
        if (file.exists()) {
            list.addAll(readFromFile());
        }
        if (t == null) return false;

        if (list.contains(t)) {
            int index = list.indexOf(t);
            list.set(index, t);
        } else {
            list.add(t);
        }

        saveToFile(list);
        String result = String.format("A %s saved or updated" +
                " to the database.", t.getClass().getSimpleName().toLowerCase());
        CustomLogger.info(result);
        return true;
    }

    @Override
    public boolean delete(int index) {
        List<T> list = new ArrayList<>();
        if (file.exists()) {
            list.addAll(readFromFile());
        }
        int length = list.size();

        if (index <= length) {
            T t = list.get(index - 1);

            list.remove(index - 1);
            saveToFile(list);

            String result = String.format("A %s removed from" +
                    " the database.", t.getClass().getSimpleName().toLowerCase());
            CustomLogger.info(result);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        List<T> list = new ArrayList<>();
        if (file.exists()) {
            list.addAll(readFromFile());
        }
        if (t == null) return false;

        if (list.contains(t)) {
            list.remove(t);
            saveToFile(list);

            String result = String.format("A %s removed from" +
                    " the database.", t.getClass().getSimpleName().toLowerCase());
            CustomLogger.info(result);
            return true;
        } else {
            return false;
        }

    }
}

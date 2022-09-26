package com.nixalevel.csvhometask;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVMapper {
    public <T> List<T> map(CSVTable table, Class<T> resultType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<CSVRow> rows = table.getRows();
        List<T> result = new ArrayList<>(rows.size());
        Constructor<T> constructor = resultType.getConstructor();
        Field[] declaredFields = resultType.getDeclaredFields();
        Map<String, Field> columnToFieldMap = new HashMap<>(declaredFields.length);
        for (Field declaredField : declaredFields) {
            ActiveColumn column = declaredField.getAnnotation(ActiveColumn.class);
            if (column != null && declaredField.trySetAccessible()) {
                columnToFieldMap.put(column.value(), declaredField);
            }
        }
        for (CSVRow row : rows) {

            T instance = constructor.newInstance();

            for (Map.Entry<String, Field> e : columnToFieldMap.entrySet()) {
                String columnName = e.getKey();
                Field field = e.getValue();

                int index = table.getColumnIndex(columnName);

                String cell = row.get().get(index);

                Class<T> fieldType = (Class<T>) field.getType();

                if (fieldType.equals(String.class)) {
                    field.set(instance, cell);
                } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    field.setInt(instance, Integer.parseInt(cell));
                } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    field.setBoolean(instance, Boolean.parseBoolean(cell));
                } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                    field.setFloat(instance, Float.parseFloat(cell));
                } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                    field.setDouble(instance, Double.parseDouble(cell));
                }else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                    field.setLong(instance, Long.parseLong(cell));
                }
            }
            result.add(instance);
        }

        return result;
    }
}

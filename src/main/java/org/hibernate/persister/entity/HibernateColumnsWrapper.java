package org.hibernate.persister.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author e-Peng.Zhang2
 */
public class HibernateColumnsWrapper {

    public Map<String, String> getColumns(SingleTableEntityPersister persistent) {
        Map<String, String> map = new HashMap<>();

        String[] fields = persistent.getSubclassPropertyNameClosure();
        String[][] columns = persistent.getSubclassPropertyColumnReaderClosure();
        for (int i = 0; i < fields.length; i++) {
            map.put(fields[i], columns[i][0]);
        }

        return map;
    }

}

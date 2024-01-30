package com.example.task.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityService {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Transactional
    public List<Object[]> findAllValues(String tableName, String uniqueColumnName, String specificColumnName) {
        try{
            String nativeQuery = "SELECT " + uniqueColumnName + "," + specificColumnName + " FROM " + tableName;
            return entityManager.createNativeQuery(nativeQuery).getResultList();
        }
        catch (Exception e){
            System.out.println("Execptoin Getting table details => "+e);
        }
        return new ArrayList<>();
    }*/

    @Transactional
    public List<Object> findAllValuesForAllTables(String tableNameString, String uniqueColumnName, String specificColumnName) {
        // Get a list of all table names in the database
        List<String> tableNames = entityManager.createNativeQuery("SHOW TABLES").getResultList();

        // Create a list to store distinct values for all tables
        List<Object> distinctValues = null;

        for (String tableName : tableNames) {
            if(tableName.equals(tableNameString)){
                String nativeQuery = "SELECT DISTINCT " + specificColumnName + " FROM " + tableName;
                List<Object> tableDistinctValues = entityManager.createNativeQuery(nativeQuery).getResultList();

                // Combine distinct values for all tables
                if (distinctValues == null) {
                    distinctValues = tableDistinctValues;
                } else {
                    distinctValues.addAll(tableDistinctValues);
                }
            }
        }

        return distinctValues;
    }

    @Transactional
    public String updateColumn(String tableName, String columnName, String oldDistinctValue, String newColumnValue) {
        String nativeQuery = "UPDATE " + tableName +
                " SET " + columnName + " = :newColumnValue" +
                " WHERE " + columnName + " = :conditionValue";

        try {
            entityManager.createNativeQuery(nativeQuery)
                    .setParameter("newColumnValue", newColumnValue)
                    .setParameter("conditionValue", oldDistinctValue)
                    .executeUpdate();
            return "SUCCESS";
        }
        catch (Exception e){
            System.out.println("Exception : "+e);
        }
        return "FAILURE";
    }
}

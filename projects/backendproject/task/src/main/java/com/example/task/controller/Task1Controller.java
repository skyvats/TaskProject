package com.example.task.controller;

import com.example.task.service.EntityService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class Task1Controller {

    @Autowired
    private EntityService entityService;

    @RequestMapping("/getSpecificColumn")
    public List<Object> getSpecificColumn(@RequestParam("tableDetails") String tableDetails){
        System.out.println("Invoked getSpecificColumn => "+ tableDetails);

        Gson gson = new Gson();
        Map<String,Object> mapDetails = gson.fromJson(tableDetails, Map.class);
        String tableName = (String) mapDetails.get("tableName");
        String uniqueColumn = (String) mapDetails.get("uniqueColumn");
        String columnName = (String) mapDetails.get("columnName");

        List<Object> allValues = entityService.findAllValuesForAllTables(tableName, uniqueColumn, columnName);
        System.out.println("All Values %%%%%% => "+allValues);
        return allValues;
    }

    @RequestMapping("/updateRows")
    public String updateRows(@RequestParam("tableDetails") String tableDetails){
        System.out.println("Invoked updateRows => "+ tableDetails);

        Gson gson = new Gson();
        Map<String,Object> mapDetails = gson.fromJson(tableDetails, Map.class);
        String tableName = (String) mapDetails.get("tableName");
        String columnName = (String) mapDetails.get("columnName");
        String oldDistinctValue = (String) mapDetails.get("oldDistinctValue");
        String updatedValue = (String) mapDetails.get("updatedValue");


        String updateColumnStatus = entityService.updateColumn(tableName, columnName, oldDistinctValue, updatedValue);
        System.out.println("updateColumnStatus %%%%%% => "+updateColumnStatus);
        return updateColumnStatus;
    }

}

package day6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Arrays;

// Pojo ---Serilize----> JSON Object ------Deserilize-----> POJO
public class SerilizationDeserilization {


    // POJO ------------> JSON Serilization process
    @Test
    void convertPojo2Json() throws JsonProcessingException {

        // created Java Object using POJO class
        StudentPOJO studentPOJO = new StudentPOJO();
        studentPOJO.setName("pavan");
        studentPOJO.setLocation("india");
        studentPOJO.setPhone("13741793");
        String[] coursesArr = {"C", "C++"};
        studentPOJO.setCourses(coursesArr);

        //convert java object ------------> json object (serilization)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentPOJO);
        System.out.println(jsonData);
    }

    //Json ---> Pojo (De-Serilization)
    @Test
    void convertJson2Pojo() throws JsonProcessingException {

        String jsonData = """
                {
                  "name" : "HASSİİİ",
                  "location" : "ARTVİN",
                  "phone" : "9876543",
                  "courses" : [ "A", "A++" ]
                }
                """;

        //convert json data -----> Pojo object
        ObjectMapper studentObject = new ObjectMapper();
        StudentPOJO studentPOJO = studentObject.readValue(jsonData, StudentPOJO.class); // convert json to pojo
        System.out.println("studentPOJO.getName() = " + studentPOJO.getName());
        System.out.println("studentPOJO.getLocation() = " + studentPOJO.getLocation());
        System.out.println("studentPOJO.getPhone() = " + studentPOJO.getPhone());
        System.out.println("studentPOJO.getCourses() = " + (Arrays.toString(studentPOJO.getCourses())));
        System.out.println("studentPOJO.getCourses() = " + studentPOJO.getCourses()[0]);
        System.out.println("studentPOJO.getCourses() = " + studentPOJO.getCourses()[1]);


    }


}

package Utils;

import org.json.JSONObject;
import org.json.JSONObject.*;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload =
                "{\n" +
                        "  \"emp_firstname\": \"Matt\",\n" +
                        "  \"emp_lastname\": \"Carson\",\n" +
                        "  \"emp_middle_name\": \"Henry\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1983-05-20\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"Technician\"\n" +
                        "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","Matt");
        obj.put("emp_lastname","Carson");
        obj.put("emp_middle_name","Henry");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","1983-05-20");
        obj.put("emp_status","Active");
        obj.put("emp_job_title","Technician");
        return obj.toString();
    }

    public static String createEmployeePayloadDynamic
            (String fName, String lName, String mName, String gender, String bday,
                                                      String status, String job){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",fName);
        obj.put("emp_lastname",lName);
        obj.put("emp_middle_name",mName);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",bday);
        obj.put("emp_status",status);
        obj.put("emp_job_title",job);
        return obj.toString();
    }

    public static String updateEmployeePayloadDynamic
            (String id, String fName, String lName, String mName, String gender, String bday,
             String status, String job){
        JSONObject obj = new JSONObject();
        obj.put("employee_id",id);
        obj.put("emp_firstname",fName);
        obj.put("emp_lastname",lName);
        obj.put("emp_middle_name",mName);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",bday);
        obj.put("emp_status",status);
        obj.put("emp_job_title",job);
        return obj.toString();
    }
}

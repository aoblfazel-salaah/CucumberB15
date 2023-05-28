package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {

    String baseURI =
            RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJpYXQiOjE2ODUwNTc1OTIsImlzcyI6ImxvY2FsaG9zdCIsImV4cC" +
            "I6MTY4NTEwMDc5MiwidXNlcklkIjoiNTI3OSJ9.9-fQ1jHJv7y1o" +
            "gvtyk5OmoM2eDHzVjjXTQj2TfHIctY";
    static String employee_id;


    @Test
    public void b_getCreatedEmployee() {

        RequestSpecification preparedRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employee_id);

        Response response =
                preparedRequest.when().get("/getOneEmployee.php");

        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmp_id =
                response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(tempEmp_id, employee_id);

    }

    @Test
    public void a_createEmployee() {
        //prepare the request
        RequestSpecification preparedRequest =
                given().header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .body("{\n" +
                                "  \"emp_firstname\": \"Matt\",\n" +
                                "  \"emp_lastname\": \"Carson\",\n" +
                                "  \"emp_middle_name\": \"Henry\",\n" +
                                "  \"emp_gender\": \"M\",\n" +
                                "  \"emp_birthday\": \"1983-05-20\",\n" +
                                "  \"emp_status\": \"Active\",\n" +
                                "  \"emp_job_title\": \"Technician\"\n" +
                                "}");
        //hitting the response
        Response response = preparedRequest.when().post("/createEmployee.php");

        //verifying the assertions
        response.then().assertThat().statusCode(201);
        //it will print the output in the console
        response.prettyPrint();

        //verify body
        response.then().assertThat()
                .body("Employee.emp_firstname", equalTo("Matt"));

        //let's capture the employee id from the response
        employee_id = response.jsonPath().getString("Employee.employee_id");

        response.then().assertThat()
                .body("Employee.emp_lastname", equalTo("Carson"));

        // verify response headers
        response.then().assertThat().header("Content-Type",
                "application/json");
    }

    @Test
    public void c_updateEmployee() {
        RequestSpecification preparedRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Daniel\",\n" +
                        "  \"emp_lastname\": \"Carson\",\n" +
                        "  \"emp_middle_name\": \"Henry\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-05-20\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"Tech\"\n" +
                        "}");

        // we can use maps and gson to form up the body

        Response response = preparedRequest.put("/updateEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message",
                equalTo("Employee record Updated"));
    }

    @Test
    public void d_getUpdatedEmployee() {
        RequestSpecification preparedRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employee_id);

        Response response =
                preparedRequest.when().get("/getOneEmployee.php");

        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        response.then().assertThat().body("employee.employee_id",
                equalTo(employee_id));
    }

    @Test
    public void e_getAllEmployeeStatus() {
        RequestSpecification requestSpecification = given()
                .header("Authorization", token);

        Response response = requestSpecification.when().get("/employeementStatus.php");
        response.prettyPrint();

        //response.then().body("name", contains("Active"));

        response.then().assertThat().statusCode(200);
        //response.then().assertThat().body("Employeement Status[0].name", hasItems("Independent contractor"));

    }

    @Test
    public void f_getAllJobTitles(){
        RequestSpecification requestSpecification = given()
                .header("Authorization", token)
                //.contentType(ContentType.JSON)
                //.accept(ContentType.JSON)
                ;

        Response response = requestSpecification.when()
                .get("/jobTitle.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        response.then().assertThat().body("Jobs.job", hasItems("Technician"));


    }
}
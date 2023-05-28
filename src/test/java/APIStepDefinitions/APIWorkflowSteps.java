package APIStepDefinitions;

import Utils.APIConstants;
import Utils.APIPayloadConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class APIWorkflowSteps {
     RequestSpecification request;
     Response response;
    public static String employee_id;
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request= given()
                .header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                            APIConstants.HEADER_VALUE_CONTENT_TYPE)
                .header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateTokenSteps.token)
                .body(APIPayloadConstants.createEmployeePayload());
    }
    @When("a post call is made to create and employee")
    public void a_post_call_is_made_to_create_and_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }
    @Then("the status code for creating employee is {int}")
    public void the_status_code_for_creating_employee_is(Integer status_code) {
        response.prettyPrint();
        response.then().assertThat().statusCode(status_code);
    }
    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String message, String value) {
        response.then().assertThat().body(message, containsString(value));
    }
    @Then("the employee id {string} is stored as a global variable to be used in other places")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_in_other_places(String idPath) {
        employee_id = response.jsonPath().getString(idPath);
        System.out.println(employee_id);
    }

    @Given("a request is prepared to create and employee using json payload")
    public void a_request_is_prepared_to_create_and_employee_using_json_payload() {
        request= given()
                    .header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                            APIConstants.HEADER_VALUE_CONTENT_TYPE)
                    .header(APIConstants.HEADER_KEY_AUTHORIZATION,
                            GenerateTokenSteps.token)
                    .body(APIPayloadConstants.createEmployeePayloadJson());
    }
// ---------------------------------------------------------------------------------


    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_and_employee() {
        request = given()
                .header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE)
                .header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateTokenSteps.token)
                .queryParam("employee_id", employee_id);
    }
    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }
    @Then("the status code for getting employee is {int}")
    public void the_status_code_for_getting_employee_is(Integer int1) {
       response.then().assertThat().statusCode(int1);
    }
    @Then("the employee data we get having {string} must match with globally stored employee id")
    public void the_employee_data_we_get_having_must_match_with_global(String idPath) {
        String tempEmpId= response.jsonPath().getString(idPath);
        Assert.assertEquals(employee_id, tempEmpId);
    }
    @Then("the retrieved data at {string} object matches with the data of created employee")
    public void the_retrieved_data_at_object_matches_with_the_data_of_created_employee(String empObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map <String, String>> expectedData= dataTable.asMaps(String.class,String.class);
        Map<String, String> actualData = response.body().jsonPath().get(empObject);

        for (Map <String, String> map: expectedData){
            Set <String > keys= map.keySet();

            for (String key:keys){
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
            }
        }

    }

    //--------------------------------------------------------------------------
    @Given("a request is prepared to create an employee with dynamic data " +
            "{string}, {string}, {string}, {string}, {string},{string}, {string}")
    public void a_request_is_prepared_to_create_an_employee_with_dynamic_data
    (String fName, String lName, String mName, String gender, String bday,
     String status, String job) {
        request = given()
                .header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE)
                .header(APIConstants.HEADER_KEY_AUTHORIZATION,
                        GenerateTokenSteps.token)
                .body(APIPayloadConstants.createEmployeePayloadDynamic
                        (fName, lName, mName, gender, bday, status, job));
    }
//-----------------------update--------------------------------------
    @Given("a request is prepared to update an employee as {string}, {string}, {string}, {string}, {string}, {string},{string}, {string}")
    public void a_request_is_prepared_to_update_an_employee_as
    (String id, String fn, String ln, String mn, String gen, String bday,
     String status, String job) {
        id=employee_id;
        request = given()
                .header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE)
                .header(APIConstants.HEADER_KEY_AUTHORIZATION,
                        GenerateTokenSteps.token)
                .body(APIPayloadConstants.updateEmployeePayloadDynamic
                        (id, fn, ln, mn, gen, bday, status, job));
    }
    @When("a PUT call is made to Update an employee")
    public void a_put_call_is_made_to_update_an_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);

    }
    @Then("the status code for updating employee is {int}")
    public void the_status_code_for_updating_employee_is(Integer int1) {
        response.prettyPrint();
        response.then().assertThat().statusCode(int1);
    }

}

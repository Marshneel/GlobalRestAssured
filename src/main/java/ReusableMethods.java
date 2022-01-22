import io.restassured.path.json.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class ReusableMethods {

    public Response sendPostRequest(GraphQLQuery query){

        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(query).
                        when().
                        post("https://api.spacex.land/graphql/").
                        then().
                        extract().
                        response();


        return response;

    }

    public void assertStatusCode(Response response){
        Assert.assertEquals(response.getStatusCode(),200);
    }

    public void assertMissionNameObject(JsonPath jsResp){
        for(int i=0; i<jsResp.getInt("data.launches.size()");i++){
            Assert.assertTrue(jsResp.getString("data.launches["+i+"].mission_name").length()>0);
        }
    }

    public void assertNumberOfLaunches(JsonPath jsResp){
        Assert.assertTrue(jsResp.getInt("data.launches.size()") > 0);
    }

    public void assertNumberOfShips(JsonPath jsResp){
        Assert.assertTrue(jsResp.getInt("data.launches.ships.size()") > 0);
    }

    public void assertFirstStageSecondStage(JsonPath jsResp){
        for(int i=0; i<jsResp.getInt("data.launches.size()");i++){
            Assert.assertNotNull(jsResp.getString("data.launches["+i+"].rocket.first_stage"));
            Assert.assertNotNull(jsResp.getString("data.launches["+i+"].rocket.second_stage"));
        }

    }
}

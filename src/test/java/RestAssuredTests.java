import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class RestAssuredTests {
    GraphQLQuery query = new GraphQLQuery();
    ReusableMethods reusableMethods = new ReusableMethods();

    @Test
    public void getAllLaunches() {


        query.setQuery("{\n" +
                "  launches {\n" +
                "    id\n" +
                "    mission_name\n" +
                "    ships {\n" +
                "      id\n" +
                "    }\n" +
                "    rocket {\n" +
                "      first_stage {\n" +
                "        cores {\n" +
                "          core {\n" +
                "            id\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "      second_stage {\n" +
                "        block\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");


        Response response = reusableMethods.sendPostRequest(query);

        reusableMethods.assertStatusCode(response);

        JsonPath jsResp = new JsonPath(response.asString());

        reusableMethods.assertMissionNameObject(jsResp);

        reusableMethods.assertNumberOfLaunches(jsResp);

        reusableMethods.assertNumberOfShips(jsResp);

        reusableMethods.assertFirstStageSecondStage(jsResp);

    }

    @Test
    public void getLaunchesWithLimit() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query($limit: Int!){\n" +
                "\n" +
                "  launches(limit: $limit) {\n" +
                "    id\n" +
                "    mission_name\n" +
                "    ships {\n" +
                "      id\n" +
                "    }\n" +
                "    rocket {\n" +
                "      first_stage {\n" +
                "        cores {\n" +
                "          core {\n" +
                "            id\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "      second_stage {\n" +
                "        block\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");

        JSONObject variables = new JSONObject();
        variables.put("limit",2);

        query.setVariables(variables.toString());

        Response response = reusableMethods.sendPostRequest(query);

        reusableMethods.assertStatusCode(response);

        JsonPath jsResp = new JsonPath(response.asString());

        reusableMethods.assertMissionNameObject(jsResp);

        reusableMethods.assertNumberOfLaunches(jsResp);

        reusableMethods.assertNumberOfShips(jsResp);

        reusableMethods.assertFirstStageSecondStage(jsResp);


    }

    @Test
    public void getLaunchesWithOffset() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query ($offset: Int!) {\n" +
                "  launches(offset: $offset) {\n" +
                "    id\n" +
                "    mission_name\n" +
                "    ships {\n" +
                "      id\n" +
                "    }\n" +
                "    rocket {\n" +
                "      first_stage {\n" +
                "        cores {\n" +
                "          core {\n" +
                "            id\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "      second_stage {\n" +
                "        block\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n");

        JSONObject variables = new JSONObject();
        variables.put("offset",2);

        query.setVariables(variables.toString());

        Response response = reusableMethods.sendPostRequest(query);

        reusableMethods.assertStatusCode(response);

        JsonPath jsResp = new JsonPath(response.asString());

        reusableMethods.assertMissionNameObject(jsResp);

        reusableMethods.assertNumberOfLaunches(jsResp);

        reusableMethods.assertNumberOfShips(jsResp);

        reusableMethods.assertFirstStageSecondStage(jsResp);
    }


}

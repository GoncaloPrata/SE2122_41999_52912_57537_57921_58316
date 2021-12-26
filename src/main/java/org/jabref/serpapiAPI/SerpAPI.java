package org.jabref.serpapiAPI;

import com.google.gson.JsonObject;
import org.jabref.serpapiAPI.searchClasses.GoogleSearch;
import org.jabref.serpapiAPI.searchClasses.SerpApiSearchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerpAPI {

    private static final String API_KEY = "f55fdb161273599de83cc15797aa73c5b22d56c0c80395154e5368657774f58d";
    List<String> resultOfSearch;

    /**
     *
     * @param userID -> id of the user to research
     * @throws SerpApiSearchException
     */
    public SerpAPI (String userID) throws SerpApiSearchException {
        FirstFunctionality firstFunctionality = new FirstFunctionality(generateJSONObjectFromID(userID));
        resultOfSearch = firstFunctionality.generateOutput();
    }

    /**
     *
     * @param firstUserID -> id of the first user to research
     * @param secondUserID -> id of the second user to research
     * @throws SerpApiSearchException
     */
    public SerpAPI (String firstUserID, String secondUserID) throws SerpApiSearchException {
        SecondFunctionality secondFunctionality = new SecondFunctionality(generateJSONObjectFromID(firstUserID), generateJSONObjectFromID(secondUserID));
        resultOfSearch = secondFunctionality.generateOutput();
    }

    /**
     *
     * @param id -> id of profile to get the JSON data
     * @return -> the JSON data of the given ID
     * @throws SerpApiSearchException
     */
    private JsonObject generateJSONObjectFromID (String id) throws SerpApiSearchException {
        JsonObject result = null;
        Map<String, String> parameter = new HashMap<>();
        parameter.put("engine", "google_scholar_author");
        parameter.put("author_id", id);
        parameter.put(GoogleSearch.SERP_API_KEY_NAME, API_KEY);

        GoogleSearch googleSearch = new GoogleSearch(parameter);
        result = googleSearch.getJson();
        return result;
    }

    // TODO
    private boolean checkIfIDIsValid (String id) throws SerpApiSearchException {
        JsonObject objectToEvaluate = generateJSONObjectFromID(id);
        return false;
    }

    public List<String> getResultOfSearch() {
        return resultOfSearch;
    }
}

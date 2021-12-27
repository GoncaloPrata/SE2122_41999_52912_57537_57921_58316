package org.jabref.serpapiAPI;

import com.google.gson.JsonObject;

import java.util.List;

public class SecondFunctionality {

    /**
     * Jason object that contains the first profile's information.
     */
    JsonObject jsonObjectWithFirstProfileData;

    /**
     * Jason object that contains the second profile's information.
     */
    JsonObject jsonObjectWithSecondProfileData;

    /**
     * Constructor to the class. Initializes all the instance variables.
     * @param objectFirstProfile Jason object to store the first profile's information.
     * @param objectSecondProfile Jason object to store the second profile's information.
     */
    public SecondFunctionality (JsonObject objectFirstProfile, JsonObject objectSecondProfile) {
        this.jsonObjectWithFirstProfileData = objectFirstProfile;
        this.jsonObjectWithSecondProfileData = objectSecondProfile;
    }

    /**
     * This method gathers all the information that composes our output.
     * @return the output, comprised by the common interests, the common articles and the common co-authors.
     */
    public List<String> generateOutput() {
        List<String> output = generateCommonInterests();
        output.addAll(generateCommonArticles());
        output.addAll(generateCommonCoAuthors());
        return output;
    }

    /**
     * This method handles the search for the common interests between the two IDs.
     * @return the output which corresponds to the common interests between the two IDs.
     */
    private List<String> generateCommonInterests() {
        List<String> output = null;
        List<String> interestsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateGeneralInformation();
        List <String> interestsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateGeneralInformation();
        interestsFirstProfile.retainAll(interestsSecondProfile);
        output = interestsFirstProfile;
        return output;
    }

    /**
     * This method handles the search for the common articles between the two IDs.
     * @return the output which corresponds to the common articles between the two IDs.
     */
    private List<String> generateCommonArticles() {
        List<String> output = null;
        List<String> articlesFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateArticlesWrittenByAuthor();
        List <String> articlesSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateArticlesWrittenByAuthor();
        articlesFirstProfile.retainAll(articlesSecondProfile);
        articlesFirstProfile.add(0, "Common Articles:");
        for (String elem : articlesFirstProfile) {
            elem = ("\n    " + elem);
        }
        output = articlesFirstProfile;
        return output;
    }

    /**
     * This method handles the search for the common co-authors between the two IDs.
     * @return the output which corresponds to the common co-authors between the two IDs.
     */
    private List<String> generateCommonCoAuthors() {
        List<String> output = null;
        List<String> authorsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateCoAuthors();
        List <String> authorsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateCoAuthors();
        authorsFirstProfile.retainAll(authorsSecondProfile);
        output = authorsFirstProfile;
        return output;
    }

}

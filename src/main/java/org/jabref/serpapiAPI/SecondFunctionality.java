package org.jabref.serpapiAPI;

import com.google.gson.JsonObject;

import java.util.ArrayList;
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
        output.add(0, "Common interests:");
        output.addAll(generateCommonArticles());
        output.add("\nCommon co-authors:\n");
        output.addAll(generateCommonCoAuthors());
        return output;
    }

    /**
     * This method handles the search for the common interests between the two IDs.
     * @return the output which corresponds to the common interests between the two IDs.
     */
    private List<String> generateCommonInterests() {
        List<String> output = new ArrayList<>();
        List<String> interestsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateGeneralInformation();
        List <String> interestsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateGeneralInformation();
        interestsFirstProfile.retainAll(interestsSecondProfile);
        if(interestsFirstProfile.isEmpty())
            output.add(" No common interests between authors.\n");
        else
            output = interestsFirstProfile;
        return output;
    }

    /**
     * This method handles the search for the common articles between the two IDs.
     * @return the output which corresponds to the common articles between the two IDs.
     */
    private List<String> generateCommonArticles() {
        List<String> output = new ArrayList<>();
        List<String> articlesFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateArticlesWrittenByAuthor();
        List <String> articlesSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateArticlesWrittenByAuthor();
        articlesFirstProfile.retainAll(articlesSecondProfile);
        if(articlesFirstProfile.isEmpty())
            output.add("\nArticles: No common articles between authors.\n");
        else {
            output.add("\nArticles: " + articlesFirstProfile.size() + "\n");
            output = articlesFirstProfile;
        }
        return output;
    }

    /**
     * This method handles the search for the common co-authors between the two IDs.
     * @return the output which corresponds to the common co-authors between the two IDs.
     */
    private List<String> generateCommonCoAuthors() {
        List<String> output = new ArrayList<>();
        List<String> authorsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateCoAuthors();
        List <String> authorsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateCoAuthors();
        authorsFirstProfile.retainAll(authorsSecondProfile);
        if(authorsFirstProfile.isEmpty())
            output.add("No common co-authors between authors.\n");
        else {
            for (int i = 0; i < authorsFirstProfile.size(); i++) {
                output.add(authorsFirstProfile.get(i) + "\n");
            }
        }
        return output;
    }

}

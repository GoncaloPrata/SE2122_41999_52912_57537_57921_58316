package org.jabref.serpapiAPI;

import com.google.gson.JsonObject;

import java.util.List;

public class SecondFunctionality {

    JsonObject jsonObjectWithFirstProfileData;
    JsonObject jsonObjectWithSecondProfileData;

    public SecondFunctionality (JsonObject objectFirstProfile, JsonObject objectSecondProfile) {
        this.jsonObjectWithFirstProfileData = objectFirstProfile;
        this.jsonObjectWithSecondProfileData = objectSecondProfile;
    }

    // TODO
    public List<String> generateOutput() {
        List<String> output = generateCommonInterests();
        output.addAll(generateCommonArticles());
        output.addAll(generateCommonCoAuthors());
        return output;
    }

    // TODO
    private List<String> generateCommonInterests() {
        List<String> output = null;
        List<String> interestsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateGeneralInformation();
        List <String> interestsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateGeneralInformation();
        return output;
    }

    // TODO
    private List<String> generateCommonArticles() {
        List<String> output = null;
        List<String> interestsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateArticlesWrittenByAuthor();
        List <String> interestsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateArticlesWrittenByAuthor();
        return output;
    }

    //TODO
    private List<String> generateCommonCoAuthors() {
        List<String> output = null;
        List<String> interestsFirstProfile = new FirstFunctionality(jsonObjectWithFirstProfileData).generateCoAuthors();
        List <String> interestsSecondProfile = new FirstFunctionality(jsonObjectWithSecondProfileData).generateCoAuthors();
        return output;
    }

}

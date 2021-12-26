package org.jabref.serpapiAPI;

import com.google.gson.JsonObject;

import java.util.List;

public class FirstFunctionality {

    JsonObject jsonObjectWithProfileData;

    public FirstFunctionality (JsonObject object) {
        this.jsonObjectWithProfileData = object;
    }

    // TODO
    public List<String> generateOutput() {
        List<String> output = generateGeneralInformation();
        output.addAll(generateArticlesWrittenByAuthor());
        output.addAll(generateCoAuthors());
        return output;
    }

    // TODO
    public List<String> generateGeneralInformation() {
        List<String> output = null;
        return output;
    }

    // TODO
    public List<String> generateArticlesWrittenByAuthor() {
        List<String> output = null;
        return output;
    }

    //TODO
    public List<String> generateCoAuthors() {
        List<String> output = null;
        return output;
    }
}

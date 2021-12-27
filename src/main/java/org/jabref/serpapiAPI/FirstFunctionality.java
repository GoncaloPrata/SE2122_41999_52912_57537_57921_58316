package org.jabref.serpapiAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class FirstFunctionality {

    JsonObject jsonObjectWithProfileData;

    public FirstFunctionality (JsonObject object) {
        this.jsonObjectWithProfileData = object;
    }

    /**
     * The method generates the output of all methods
     * @return the output of all methods
     */
    public List<String> generateOutput() {
        List<String> output = generateGeneralInformation();
        output.add("\nArticles:\n");
        output.addAll(generateArticlesWrittenByAuthor());
        output.add("Co-authors:\n");
        output.addAll(generateCoAuthors());
        return output;
    }

    /**
     * The method searches the general information about an author
     * @return the general information of the author
     */
    public List<String> generateGeneralInformation() {
        List<String> output = new ArrayList<>();
        JsonObject author = jsonObjectWithProfileData.get("author").getAsJsonObject(); // Subsecção do JSON "author"
        String nameOfAuthor = author.get("name").getAsString();
        String affiliationsOfAuthor = author.get("affiliations").getAsString();
        output.add("Name: " + nameOfAuthor + "\nAffiliation: " + affiliationsOfAuthor + "\nInterest: ");
        JsonArray interestsAuthor = author.get("interests").getAsJsonArray();
        for (JsonElement interesses : interestsAuthor) {
            output.add("   " + interesses.getAsJsonObject().get("title").getAsString() + ";");
        }

        return output;

    }

    /**
     * The method lists the articles by an author, including the article's title, link and authors
     * @return the articles by an author
     */
    public List<String> generateArticlesWrittenByAuthor() {
        List<String> output = new ArrayList<>();
        JsonArray artigosAutor = jsonObjectWithProfileData.getAsJsonArray("articles");
        for (JsonElement artigos : artigosAutor) {
            String titleArticle = artigos.getAsJsonObject().get("title").getAsString();
            String linkArticle = artigos.getAsJsonObject().get("link").getAsString();
            String authorsArticle = artigos.getAsJsonObject().get("authors").getAsString();
            output.add("    Title: " + titleArticle +
                    "\n    Link: " + linkArticle +
                    "\n    Authors: " + authorsArticle + "\n\n");
        }
        return output;
    }

    /**
     * The method lists the coAuthors
     * @return the coAuthors
     */
    public List<String> generateCoAuthors() {
        List<String> output = new ArrayList<>();
        JsonArray coauthors = jsonObjectWithProfileData.getAsJsonArray("co_authors");
        for (JsonElement co_author : coauthors) {
            String nameCo_Author = co_author.getAsJsonObject().get("name").getAsString();
            String linkCo_Author = co_author.getAsJsonObject().get("link").getAsString();
            String affiliationsCo_Author = "No perfil Google Scholar não estão mencionadas afiliações.";
            if (co_author.getAsJsonObject().has("affiliations"))
                affiliationsCo_Author = co_author.getAsJsonObject().get("affiliations").getAsString();
            output.add("    Name of Coauthor: " + nameCo_Author +
                    "\n    Link: " + linkCo_Author +
                    "\n    Affiliations: " + affiliationsCo_Author + "\n\n");
        }
        return output;
    }


}

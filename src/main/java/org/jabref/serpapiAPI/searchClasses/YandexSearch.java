package org.jabref.serpapiAPI.searchClasses;

import java.util.Map;
import com.google.gson.JsonArray;

/***
 * Yandex Search Results using SerpApi
 *
 * Usage
 * ---
 * ```java
 * Map<String, String> parameter = new HashMap<>();
 * parameter.put("text", "Coffee");
 * YandexSearch yandex = new YandexSearch(parameter, "secret api key");
 * JsonObject data = yandex.getJson();
 * JsonArray organic_results = data.get("organic_results").getAsJsonArray();
 * ```
 */
public class YandexSearch extends SerpApiSearch {

  public YandexSearch(Map<String, String> parameter, String apiKey) {
    super(parameter, apiKey, "yandex");
  }

  public YandexSearch() {
    super("yandex");
  }

  public YandexSearch(Map<String, String> parameter) {
    super(parameter, "yandex");
  }

  @Override
   public JsonArray getLocation(String q, Integer limit) throws SerpApiSearchException {
     throw new SerpApiSearchException("location is not supported for Baidu");
   }
}

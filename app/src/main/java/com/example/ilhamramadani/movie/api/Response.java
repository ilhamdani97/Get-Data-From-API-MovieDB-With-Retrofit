package com.example.ilhamramadani.movie.api;

import com.example.ilhamramadani.movie.model.AndroidMovie;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<AndroidMovie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<AndroidMovie> getResults() {
        return results;
    }

    public void setResults(List<AndroidMovie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

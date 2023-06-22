package com.optymyze.coding.constant;

public class Constant {
    public static final String GITHUB_PROFILE_URL_REGEX = "(https:\\/\\/www\\.|http:\\/\\/www\\.|http:\\/\\/|https:\\/\\/|www\\.)?(github.com)(\\.[a-zA-Z0-9]{2,})?\\/[a-zA-Z0-9]{2,}";
    private static final String GITHUB_ROOT_URI = "https://api.github.com/";
    public static final String GITHUB_REPO_URL = GITHUB_ROOT_URI + "users/{username}/repos";
}

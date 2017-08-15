package com.twitter.utils

import com.jayway.restassured.specification.RequestSpecification
import groovy.json.JsonSlurper
import com.twitter.models.User

import static com.jayway.restassured.RestAssured.given

class OauthUtil {
    private static final USER_DATA_PATH = 'data/Users.json'
    private static final DEFAULT_USER = 'TestUser'
    private static List<User> usersList

    static RequestSpecification oauth(String useName = DEFAULT_USER) {
        def user = getUser(useName)
        given().auth()
                .oauth(user.consumerKey,
                user.consumerSecret,
                user.accessToken,
                user.tokenSecret)
    }

    private static User getUser(String userName) {
        if (!usersList) {
            def usersText = new File(USER_DATA_PATH).getText()
            usersList = new JsonSlurper().parseText(usersText) as List<User>
        }
        def user = usersList.find { user -> user.name == userName }
        assert user: "Can't find user with name  ${userName}"
        user
    }
}

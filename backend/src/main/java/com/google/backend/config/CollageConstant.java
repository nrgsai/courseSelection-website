package com.google.backend.config;

public class CollageConstant {

    //API_ADDRESS
    public static final String USER_CONTEXT = "/api/users";
    public static final String JWT_CONTEXT = "/jwt";
    public static final String CREATE_CONTEXT = "/create";
    public static final String UPDATE_CONTEXT = "/update";
    public static final String DELETE_CONTEXT = "/delete/{id}";
    public static final String GET_CONTEXT = "/get/{id}";
    public static final String LIST_CONTEXT = "/list";
    public static final String ROLE_CONTEXT = "/role";
    public static final String EXPERTISE_CONTEXT = "/api/expertise";
    public static final String PROFESSOR_CONTEXT = "/api/professor";
    public static final String LESSON_CONTEXT = "/api/lesson";
    public static final String UNIT_CONTEXT = "/api/unit";
    public static final String STUDENT_UNIT_CONTEXT = "/api/studentUnit";
    public static final String COURSE_CONTEXT = "/api/course";
    //END_API_ADDRESS

    //MESSAGE
    public static final String SUCCESS = "SUCCESS";
    public static final String STATUS = "STATUS";
    public static final String USERNAME_CONFLICT = "The username has already been registered ";
    public static final String USER_INVALID = "Incorrect username/password supplied";
    public static final String INVALID_TOKEN = "Invalid Token";
    //END_MESSAGE

    //VALUE
    public static final Long ONE_LONG = 1L;
    public static final Long TWO_LONG = 2L;
    //END_VALUE

    //SECURITY
    public static final String SECRET_KEY = "$2a$10$Q7vv9Y3ZuWQkU6320B7mq...QRr7/z76io4vma5rlII0ezZfhVK0e";
    public static final String DEFAULT_SECURITY_ROLE_CONSTANT = "ROLE_";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN = "/token";
    //END_SECURITY
}

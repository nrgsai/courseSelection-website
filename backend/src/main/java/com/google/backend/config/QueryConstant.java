package com.google.backend.config;

public class QueryConstant {

    public static final String GET_USER_ROLE_NAME = "select r.name from roles r left join users_roles ur on r.id = ur.role_id where ur.user_id = :id";

    public static final String USER_LIST = "select u.id, u.username, r.name " +
            "from users u " +
            "         left join users_roles ur on u.id = ur.user_id " +
            "         left join roles r on ur.role_id = r.id " +
            "where case " +
            "          when :username = '' then true " +
            "          else upper(:username) like '%' || upper(u.username) || '%' end " +
            "limit :limit offset :offset";

    public static final String EXPERTISE_LIST = "select * " +
            "from expertise e " +
            "limit :limit offset :offset ";

    public static final String GET_ROLE = "select r.name " +
            "from roles r " +
            "         left join users_roles ur on ur.role_id = r.id " +
            "where ur.user_id = :userId";

    public static final String PROFESSOR_LIST = "select p.id, p.first_name, p.last_name, " +
            "p.national_code, p.phone_number, p.expertise_id, e.title as expertise " +
            "from professor p " +
            "         left join expertise e on p.expertise_id = e.id " +
            "limit :limit offset :offset";

    public static final String LESSON_LIST = "select l.id, l.name, l.code, l.unit_number, " +
            "l.expertise_id, e.title as expertise " +
            "from lesson l " +
            "         left join expertise e on l.expertise_id = e.id " +
            "limit :limit offset :offset;";
}

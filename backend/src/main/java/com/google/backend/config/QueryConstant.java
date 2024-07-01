package com.google.backend.config;

public class QueryConstant {

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
}

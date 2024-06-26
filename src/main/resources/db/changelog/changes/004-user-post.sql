create table user_post (
    post_id bigint primary key,
    app_user_id varchar(255) not null
                    constraint fk_app_user_for_post
                       references app_user
)
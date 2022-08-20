create table role(
    role_id bigint not null,
    name varchar not null,
    primary key (role_id)
);

create table app_user(
    user_id bigint not null,
    firstname varchar,
    lastname varchar,
    username varchar not null,
    primary key (user_id)
);

create table user_role(
    id bigint not null,
    role_id bigint not null,
    user_id bigint not null,
    added_at timestamp with time zone,
    primary key (id),
    CONSTRAINT user_role_role_id
        FOREIGN KEY (role_id)
            REFERENCES role(role_id) on delete cascade,
    CONSTRAINT user_role_user_id
        FOREIGN KEY (user_id)
            REFERENCES app_user(user_id) on delete cascade
);

create table article(
    article_id bigint not null,
    parent_article_id bigint default null,
    author_id bigint not null,
    created_at timestamp with time zone default (NOW()::timestamp),
    updated_at timestamp with time zone default null,
    title varchar,
    content TEXT,
    article_order integer not null,
    primary key (article_id),
    CONSTRAINT article_parent_article_id
        FOREIGN KEY (parent_article_id)
            REFERENCES article(article_id) on delete set null,
    CONSTRAINT article_author_id
        FOREIGN KEY (author_id)
            REFERENCES app_user(user_id) on delete cascade
);

create table comment(
    comment_id bigint not null,
    parent_comment_id bigint default null,
    article_id bigint not null,
    author_id bigint not null,
    created_at timestamp with time zone default (NOW()::timestamp),
    updated_at timestamp with time zone default null,
    content TEXT,
    primary key (comment_id),
    CONSTRAINT comment_article_id
        FOREIGN KEY (article_id)
            REFERENCES article(article_id) on delete cascade,
    CONSTRAINT comment_author_id
        FOREIGN KEY (author_id)
            REFERENCES app_user(user_id) on delete cascade,
    CONSTRAINT comment_parent_comment_id
        FOREIGN KEY (parent_comment_id)
            REFERENCES comment(comment_id) on delete set null
);

create sequence article_id_seq
    start with 1
    increment by 1;

create sequence comment_id_seq
    start with 1
    increment by 1;

create sequence role_id_seq
    start with 1
    increment by 1;

create sequence user_id_seq
    start with 1
    increment by 1;

create sequence user_role_id_seq
    start with 1
    increment by 1;

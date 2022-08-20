create table tag_group(
    group_id bigint not null,
    name varchar(50) not null,
    description varchar default null,
    disjoint boolean default false,
    private boolean default false,
    created_at timestamp without time zone not null,
    updated_at timestamp without time zone default null,
    author_id bigint not null,

    primary key(group_id),
    CONSTRAINT tag_group_author_id
        FOREIGN KEY (author_id)
            REFERENCES app_user(user_id) on delete cascade
);

create sequence tag_group_id_seq
    start with 1
    increment by 1;

create table tag(
    tag_id bigint not null,
    group_id bigint not null,
    name varchar(50) not null,
    description varchar default null,
    created_at timestamp without time zone not null,
    updated_at timestamp without time zone default null,
    author_id bigint not null,

    primary key(tag_id),
    CONSTRAINT tag_group_author_id
      FOREIGN KEY (author_id)
          REFERENCES app_user(user_id) on delete cascade,
    CONSTRAINT tag_group_id
      FOREIGN KEY (group_id)
          REFERENCES tag_group(group_id) on delete cascade
);

create sequence tag_id_seq
    start with 1
    increment by 1;
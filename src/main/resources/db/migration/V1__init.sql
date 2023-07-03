CREATE TABLE chats
(
    id           bigserial PRIMARY KEY NOT NULL,
    tg_chat_name varchar(250)          NOT NULL,
    tg_chat_id   bigint                NOT NULL,
    chat_type    varchar(250)          NOT NULL,
    is_active    bool                  NOT NULL DEFAULT (true),
    created_at   timestamp                      DEFAULT (current_timestamp),
    updated_at   timestamp                      DEFAULT (current_timestamp)
);

CREATE TABLE admins
(
    id          bigserial PRIMARY KEY NOT NULL,
    username    varchar(250)          NOT NULL,
    tg_user_id  bigint                NOT NULL,
    created_at  timestamp DEFAULT (current_timestamp),
    updated_at  timestamp DEFAULT (current_timestamp)
--     CONSTRAINT fk_admin_chat_id FOREIGN KEY (int_chat_id) REFERENCES chats (id)
);

CREATE TABLE chats_admins
(
    chat_id  bigint,
    admin_id bigint,
    CONSTRAINT tags_tasks_pkey PRIMARY KEY (chat_id, admin_id),
    CONSTRAINT fk_tag_task_id FOREIGN KEY (chat_id) REFERENCES chats (id),
    CONSTRAINT fk_task_tag_id FOREIGN KEY (admin_id) REFERENCES admins (id)
);

CREATE TABLE rules
(
    id          bigserial PRIMARY KEY NOT NULL,
    int_chat_id bigint                NOT NULL,
    type        varchar(250)          NOT NULL,
    condition   varchar(250)          NOT NULL,
    property    varchar(250)          NOT NULL,
    action      varchar(250)          NOT NULL,
    created_at  timestamp DEFAULT (current_timestamp),
    updated_at  timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_rules_chat_id FOREIGN KEY (int_chat_id) REFERENCES chats (id)
);

CREATE TABLE banned_messages
(
    id            bigserial PRIMARY KEY NOT NULL,
    int_chat_id   bigint                NOT NULL,
    rule_id       bigint                NOT NULL,
    tg_message_id int                   NOT NULL,
    int_user_id   bigint                NOT NULL,
    text          text                  NOT NULL,
    created_at    timestamp DEFAULT (current_timestamp),
    updated_at    timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_banned_message_chat_id FOREIGN KEY (int_chat_id) REFERENCES chats (id),
    CONSTRAINT fk_banned_message_rule_id FOREIGN KEY (rule_id) REFERENCES rules (id)
);

CREATE TABLE banned_users
(
    id             bigserial PRIMARY KEY NOT NULL,
    int_chat_id    bigint                NOT NULL,
    rule_id        bigint                NOT NULL,
    username       varchar(250)          NOT NULL,
    tg_user_id     bigint                NOT NULL,
    int_message_id bigint                NOT NULL,
    created_at     timestamp DEFAULT (current_timestamp),
    updated_at     timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_banned_user_chat_id FOREIGN KEY (int_chat_id) REFERENCES chats (id),
    CONSTRAINT fk_banned_user_message_id FOREIGN KEY (int_message_id) REFERENCES banned_messages (id),
    CONSTRAINT fk_banned_user_rule_id FOREIGN KEY (rule_id) REFERENCES rules (id)
);
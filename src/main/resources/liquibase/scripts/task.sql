-- liquibase formatted sql

-- changeset s.danilcovich:1
create table notification_task
(
    id                smallserial  NOT NULL PRIMARY KEY,
    chat_id           bigint       NOT NULL,
    notification_date timestamp    NOT NULL,
    notification_text varchar(300) NOT NULL,
    status            varchar(300) NOT NULL
);
alter table notification_task
owner to student;


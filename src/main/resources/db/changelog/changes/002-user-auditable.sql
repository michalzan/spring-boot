alter table app_user
    add column created_at timestamp,
    add column last_modified_at timestamp;

alter table employee
    add column created_at timestamp,
    add column last_modified_at timestamp;

create table <%= tableName %> (
    id serial,
    "text" varchar(1024) not null,
    email varchar(1024) not null,
    username varchar(1024) not null,
    password varchar(1024) not null,
    primary key (id)
);

insert into <%= tableName %> (email, username, password,"text"  ) values ( 'email', 'username',
'$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6','Usuario');
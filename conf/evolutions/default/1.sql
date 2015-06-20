# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ban (
  id                        bigint not null,
  banner_id                 bigint,
  banned_id                 bigint,
  constraint pk_ban primary key (id))
;

create table gallery (
  id                        bigint not null,
  current                   integer,
  photo0                    varchar(255),
  photo1                    varchar(255),
  photo2                    varchar(255),
  photo3                    varchar(255),
  photo4                    varchar(255),
  owner_id                  bigint,
  constraint pk_gallery primary key (id))
;

create table post (
  id                        bigint not null,
  publisher_id              bigint,
  published_id              bigint,
  content                   varchar(255),
  link                      varchar(255),
  constraint pk_post primary key (id))
;

create table user1 (
  id                        bigint not null,
  email                     varchar(100) not null,
  password                  varchar(128) not null,
  photo                     varchar(255),
  birth                     timestamp,
  last_sign_in              timestamp,
  is_desactive              boolean,
  description               varchar(400),
  username                  varchar(30),
  mobile                    varchar(10),
  residence                 varchar(100),
  interests                 varchar(400),
  preference                integer,
  relationship              integer,
  perversion                integer,
  gender                    integer,
  whishes                   varchar(400),
  constraint uq_user1_email unique (email),
  constraint pk_user1 primary key (id))
;

create sequence ban_seq;

create sequence gallery_seq;

create sequence post_seq;

create sequence user1_seq;

alter table ban add constraint fk_ban_banner_1 foreign key (banner_id) references user1 (id);
create index ix_ban_banner_1 on ban (banner_id);
alter table ban add constraint fk_ban_banned_2 foreign key (banned_id) references user1 (id);
create index ix_ban_banned_2 on ban (banned_id);
alter table gallery add constraint fk_gallery_owner_3 foreign key (owner_id) references user1 (id);
create index ix_gallery_owner_3 on gallery (owner_id);
alter table post add constraint fk_post_publisher_4 foreign key (publisher_id) references user1 (id);
create index ix_post_publisher_4 on post (publisher_id);
alter table post add constraint fk_post_published_5 foreign key (published_id) references user1 (id);
create index ix_post_published_5 on post (published_id);



# --- !Downs

drop table if exists ban cascade;

drop table if exists gallery cascade;

drop table if exists post cascade;

drop table if exists user1 cascade;

drop sequence if exists ban_seq;

drop sequence if exists gallery_seq;

drop sequence if exists post_seq;

drop sequence if exists user1_seq;


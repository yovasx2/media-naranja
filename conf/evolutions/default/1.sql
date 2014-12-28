# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ban (
  id                        bigint auto_increment not null,
  banner_id                 bigint,
  banned_id                 bigint,
  constraint pk_ban primary key (id))
;

create table gallery (
  id                        bigint auto_increment not null,
  current                   tinyint(1),
  photo0                    varchar(255),
  photo1                    varchar(255),
  photo2                    varchar(255),
  photo3                    varchar(255),
  photo4                    varchar(255),
  owner_id                  bigint,
  constraint pk_gallery primary key (id))
;

create table post (
  id                        bigint auto_increment not null,
  publisher_id              bigint,
  published_id              bigint,
  content                   varchar(255),
  link                      varchar(255),
  constraint pk_post primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  password                  varchar(128) not null,
  photo                     varchar(255),
  birth                     datetime,
  last_sign_in              datetime,
  is_desactive              tinyint(1),
  description               varchar(400),
  username                  varchar(30),
  mobile                    varchar(10),
  residence                 varchar(100),
  interests                 varchar(400),
  preference                tinyint(1),
  relationship              tinyint(1),
  perversion                tinyint(1),
  gender                    tinyint(1),
  whishes                   varchar(400),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

alter table ban add constraint fk_ban_banner_1 foreign key (banner_id) references user (id) on delete restrict on update restrict;
create index ix_ban_banner_1 on ban (banner_id);
alter table ban add constraint fk_ban_banned_2 foreign key (banned_id) references user (id) on delete restrict on update restrict;
create index ix_ban_banned_2 on ban (banned_id);
alter table gallery add constraint fk_gallery_owner_3 foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_gallery_owner_3 on gallery (owner_id);
alter table post add constraint fk_post_publisher_4 foreign key (publisher_id) references user (id) on delete restrict on update restrict;
create index ix_post_publisher_4 on post (publisher_id);
alter table post add constraint fk_post_published_5 foreign key (published_id) references user (id) on delete restrict on update restrict;
create index ix_post_published_5 on post (published_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table ban;

drop table gallery;

drop table post;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


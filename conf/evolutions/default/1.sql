# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  password                  varchar(128) not null,
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




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


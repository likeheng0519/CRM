/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2017/1/4 11:00:02                            */
/*==============================================================*/

drop table if exists wz_project_search_condition;

drop table if exists wz_project_user_permission;

drop table if exists wz_appeal;

drop table if exists wz_project_content;

drop table if exists wz_project_metadata;

drop table if exists wz_user;

drop index if exists idx_username;



/*==============================================================*/
/* Table: wz_appeal                                             */
/*==============================================================*/
create table wz_appeal (
   id                   SERIAL8              not null,
   project_metadata_id                   INT8              not null,
   store_code           TEXT                 not null,
   author               INT8                 null,
   created              TIMESTAMP                 null,
   content              TEXT                 null,
   operator             INT8                 null,
   updated              TIMESTAMP                 null,
   status               INT2                 null,
   image1               TEXT                 null,
   image2               TEXT                 null,
   image3               TEXT                 null,
   image4               TEXT                 null,
   image5               TEXT                 null,
   image6               TEXT                 null,
   image7               TEXT                 null,
   image8               TEXT                 null,
   image9               TEXT                 null,
   image10              TEXT                 null,
   constraint PK_WZ_APPEAL primary key (id)
);

comment on column wz_appeal.status is
'1-等待处理2-已经处理3申诉资料不全不予处理4数据无误无需更改';

/*==============================================================*/
/* Table: wz_project_content                                    */
/*==============================================================*/
create table wz_project_content (
   id                   SERIAL8              not null,
   project_metadata_id  INT8                 not null,
   created              TIMESTAMP                 null,
   f1                   TEXT                 null,
   f2                   TEXT                 null,
   f3                   TEXT                 null,
   f4                   TEXT                 null,
   f5                   TEXT                 null,
   f6                   TEXT                 null,
   f7                   TEXT                 null,
   f8                   TEXT                 null,
   f9                   TEXT                 null,
   f10                  TEXT                 null,
   f11                  TEXT                 null,
   f12                  TEXT                 null,
   f13                  TEXT                 null,
   f14                  TEXT                 null,
   f15                  TEXT                 null,
   f16                  TEXT                 null,
   f17                  TEXT                 null,
   f18                  TEXT                 null,
   f19                  TEXT                 null,
   f20                  TEXT                 null,
   f21                  TEXT                 null,
   f22                  TEXT                 null,
   f23                  TEXT                 null,
   f24                  TEXT                 null,
   f25                  TEXT                 null,
   f26                  TEXT                 null,
   f27                  TEXT                 null,
   f28                  TEXT                 null,
   f29                  TEXT                 null,
   f30                  TEXT                 null,
   f31                  TEXT                 null,
   f32                  TEXT                 null,
   f33                  TEXT                 null,
   f34                  TEXT                 null,
   f35                  TEXT                 null,
   f36                  TEXT                 null,
   f37                  TEXT                 null,
   f38                  TEXT                 null,
   f39                  TEXT                 null,
   f40                  TEXT                 null,
   f41                  TEXT                 null,
   f42                  TEXT                 null,
   f43                  TEXT                 null,
   f44                  TEXT                 null,
   f45                  TEXT                 null,
   f46                  TEXT                 null,
   f47                  TEXT                 null,
   f48                  TEXT                 null,
   f49                  TEXT                 null,
   f50                  TEXT                 null,
   f51                  TEXT                 null,
   f52                  TEXT                 null,
   f53                  TEXT                 null,
   f54                  TEXT                 null,
   f55                  TEXT                 null,
   f56                  TEXT                 null,
   f57                  TEXT                 null,
   f58                  TEXT                 null,
   f59                  TEXT                 null,
   f60                  TEXT                 null,
   f61                  TEXT                 null,
   f62                  TEXT                 null,
   f63                  TEXT                 null,
   f64                  TEXT                 null,
   f65                  TEXT                 null,
   f66                  TEXT                 null,
   f67                  TEXT                 null,
   f68                  TEXT                 null,
   f69                  TEXT                 null,
   f70                  TEXT                 null,
   f71                  TEXT                 null,
   f72                  TEXT                 null,
   f73                  TEXT                 null,
   f74                  TEXT                 null,
   f75                  TEXT                 null,
   f76                  TEXT                 null,
   f77                  TEXT                 null,
   f78                  TEXT                 null,
   f79                  TEXT                 null,
   f80                  TEXT                 null,
   f81                  TEXT                 null,
   f82                  TEXT                 null,
   f83                  TEXT                 null,
   f84                  TEXT                 null,
   f85                  TEXT                 null,
   f86                  TEXT                 null,
   f87                  TEXT                 null,
   f88                  TEXT                 null,
   f89                  TEXT                 null,
   f90                  TEXT                 null,
   f91                  TEXT                 null,
   f92                  TEXT                 null,
   f93                  TEXT                 null,
   f94                  TEXT                 null,
   f95                  TEXT                 null,
   f96                  TEXT                 null,
   f97                  TEXT                 null,
   f98                  TEXT                 null,
   f99                  TEXT                 null,
   f100                 TEXT                 null,
   f101                 TEXT                 null,
   f102                 TEXT                 null,
   f103                 TEXT                 null,
   f104                 TEXT                 null,
   f105                 TEXT                 null,
   f106                 TEXT                 null,
   f107                 TEXT                 null,
   f108                 TEXT                 null,
   f109                 TEXT                 null,
   f110                 TEXT                 null,
   f111                 TEXT                 null,
   f112                 TEXT                 null,
   f113                 TEXT                 null,
   f114                 TEXT                 null,
   f115                 TEXT                 null,
   f116                 TEXT                 null,
   f117                 TEXT                 null,
   f118                 TEXT                 null,
   f119                 TEXT                 null,
   f120                 TEXT                 null,
   f121                 TEXT                 null,
   f122                 TEXT                 null,
   f123                 TEXT                 null,
   f124                 TEXT                 null,
   f125                 TEXT                 null,
   f126                 TEXT                 null,
   f127                 TEXT                 null,
   f128                 TEXT                 null,
   f129                 TEXT                 null,
   f130                 TEXT                 null,
   f131                 TEXT                 null,
   f132                 TEXT                 null,
   f133                 TEXT                 null,
   f134                 TEXT                 null,
   f135                 TEXT                 null,
   f136                 TEXT                 null,
   f137                 TEXT                 null,
   f138                 TEXT                 null,
   f139                 TEXT                 null,
   f140                 TEXT                 null,
   f141                 TEXT                 null,
   f142                 TEXT                 null,
   f143                 TEXT                 null,
   f144                 TEXT                 null,
   f145                 TEXT                 null,
   f146                 TEXT                 null,
   f147                 TEXT                 null,
   f148                 TEXT                 null,
   f149                 TEXT                 null,
   f150                 TEXT                 null,
   constraint PK_WZ_PROJECT_CONTENT primary key (id)
);

/*==============================================================*/
/* Table: wz_project_metadata                                   */
/*==============================================================*/
create table wz_project_metadata (
   id                   SERIAL8              not null,
   name                 TEXT                 not null,
   length               INT4                 not null,
   created              TIMESTAMP                 not null,
   updated              TIMESTAMP                 null,
   f1_title             TEXT                 null,
   f2_title             TEXT                 null,
   f3_title             TEXT                 null,
   f4_title             TEXT                 null,
   f5_title             TEXT                 null,
   f6_title             TEXT                 null,
   f7_title             TEXT                 null,
   f8_title             TEXT                 null,
   f9_title             TEXT                 null,
   f10_title            TEXT                 null,
   f11_title            TEXT                 null,
   f12_title            TEXT                 null,
   f13_title            TEXT                 null,
   f14_title            TEXT                 null,
   f15_title            TEXT                 null,
   f16_title            TEXT                 null,
   f17_title            TEXT                 null,
   f18_title            TEXT                 null,
   f19_title            TEXT                 null,
   f20_title            TEXT                 null,
   f21_title            TEXT                 null,
   f22_title            TEXT                 null,
   f23_title            TEXT                 null,
   f24_title            TEXT                 null,
   f25_title            TEXT                 null,
   f26_title            TEXT                 null,
   f27_title            TEXT                 null,
   f28_title            TEXT                 null,
   f29_title            TEXT                 null,
   f30_title            TEXT                 null,
   f31_title            TEXT                 null,
   f32_title            TEXT                 null,
   f33_title            TEXT                 null,
   f34_title            TEXT                 null,
   f35_title            TEXT                 null,
   f36_title            TEXT                 null,
   f37_title            TEXT                 null,
   f38_title            TEXT                 null,
   f39_title            TEXT                 null,
   f40_title            TEXT                 null,
   f41_title            TEXT                 null,
   f42_title            TEXT                 null,
   f43_title            TEXT                 null,
   f44_title            TEXT                 null,
   f45_title            TEXT                 null,
   f46_title            TEXT                 null,
   f47_title            TEXT                 null,
   f48_title            TEXT                 null,
   f49_title            TEXT                 null,
   f50_title            TEXT                 null,
   f51_title            TEXT                 null,
   f52_title            TEXT                 null,
   f53_title            TEXT                 null,
   f54_title            TEXT                 null,
   f55_title            TEXT                 null,
   f56_title            TEXT                 null,
   f57_title            TEXT                 null,
   f58_title            TEXT                 null,
   f59_title            TEXT                 null,
   f60_title            TEXT                 null,
   f61_title            TEXT                 null,
   f62_title            TEXT                 null,
   f63_title            TEXT                 null,
   f64_title            TEXT                 null,
   f65_title            TEXT                 null,
   f66_title            TEXT                 null,
   f67_title            TEXT                 null,
   f68_title            TEXT                 null,
   f69_title            TEXT                 null,
   f70_title            TEXT                 null,
   f71_title            TEXT                 null,
   f72_title            TEXT                 null,
   f73_title            TEXT                 null,
   f74_title            TEXT                 null,
   f75_title            TEXT                 null,
   f76_title            TEXT                 null,
   f77_title            TEXT                 null,
   f78_title            TEXT                 null,
   f79_title            TEXT                 null,
   f80_title            TEXT                 null,
   f81_title            TEXT                 null,
   f82_title            TEXT                 null,
   f83_title            TEXT                 null,
   f84_title            TEXT                 null,
   f85_title            TEXT                 null,
   f86_title            TEXT                 null,
   f87_title            TEXT                 null,
   f88_title            TEXT                 null,
   f89_title            TEXT                 null,
   f90_title            TEXT                 null,
   f91_title            TEXT                 null,
   f92_title            TEXT                 null,
   f93_title            TEXT                 null,
   f94_title            TEXT                 null,
   f95_title            TEXT                 null,
   f96_title            TEXT                 null,
   f97_title            TEXT                 null,
   f98_title            TEXT                 null,
   f99_title            TEXT                 null,
   f100_title           TEXT                 null,
   f101_title           TEXT                 null,
   f102_title           TEXT                 null,
   f103_title           TEXT                 null,
   f104_title           TEXT                 null,
   f105_title           TEXT                 null,
   f106_title           TEXT                 null,
   f107_title           TEXT                 null,
   f108_title           TEXT                 null,
   f109_title           TEXT                 null,
   f110_title           TEXT                 null,
   f111_title           TEXT                 null,
   f112_title           TEXT                 null,
   f113_title           TEXT                 null,
   f114_title           TEXT                 null,
   f115_title           TEXT                 null,
   f116_title           TEXT                 null,
   f117_title           TEXT                 null,
   f118_title           TEXT                 null,
   f119_title           TEXT                 null,
   f120_title           TEXT                 null,
   f121_title           TEXT                 null,
   f122_title           TEXT                 null,
   f123_title           TEXT                 null,
   f124_title           TEXT                 null,
   f125_title           TEXT                 null,
   f126_title           TEXT                 null,
   f127_title           TEXT                 null,
   f128_title           TEXT                 null,
   f129_title           TEXT                 null,
   f130_title           TEXT                 null,
   f131_title           TEXT                 null,
   f132_title           TEXT                 null,
   f133_title           TEXT                 null,
   f134_title           TEXT                 null,
   f135_title           TEXT                 null,
   f136_title           TEXT                 null,
   f137_title           TEXT                 null,
   f138_title           TEXT                 null,
   f139_title           TEXT                 null,
   f140_title           TEXT                 null,
   f141_title           TEXT                 null,
   f142_title           TEXT                 null,
   f143_title           TEXT                 null,
   f144_title           TEXT                 null,
   f145_title           TEXT                 null,
   f146_title           TEXT                 null,
   f147_title           TEXT                 null,
   f148_title           TEXT                 null,
   f149_title           TEXT                 null,
   f150_title           TEXT                 null,
   constraint PK_WZ_PROJECT_METADATA primary key (id)
);

/*==============================================================*/
/* Table: wz_project_search_condition                           */
/*==============================================================*/
create table wz_project_search_condition (
   id                   SERIAL8              not null,
   project_metadata_id  INT8                 not null,
   field1_title         TEXT                 null,
   field1               TEXT                 null,
   field1_type          INT2                 null,
   field2_title         TEXT                 null,
   field2               TEXT                 null,
   field2_type          INT2                 null,
   field3_title         TEXT                 null,
   field3               TEXT                 null,
   field3_type          INT2                 null,
   field4_title         TEXT                 null,
   field4               TEXT                 null,
   field4_type          INT2                 null,
   field5_title         TEXT                 null,
   field5               TEXT                 null,
   field5_type          INT2                 null,
   created              TIMESTAMP                 null,
   updated              TIMESTAMP                 null,
   constraint PK_WZ_PROJECT_SEARCH_CONDITION primary key (id)
);

comment on column wz_project_search_condition.field1_type is
'1-select
2-input
';

comment on column wz_project_search_condition.field2_type is
'1-select
2-input
';

comment on column wz_project_search_condition.field3_type is
'1-select
2-input
';

comment on column wz_project_search_condition.field4_type is
'1-select
2-input
';

comment on column wz_project_search_condition.field5_type is
'1-select
2-input
';

/*==============================================================*/
/* Table: wz_project_user_permission                            */
/*==============================================================*/
create table wz_project_user_permission (
   id                   SERIAL8              not null,
   project_metadata_id  INT8                 not null,
   user_id              INT8                 not null,
   created              TIMESTAMP                 null,
   constraint PK_WZ_PROJECT_USER_PERMISSION primary key (id)
);

/*==============================================================*/
/* Table: wz_user                                               */
/*==============================================================*/
create table wz_user (
   id                   SERIAL8              not null,
   username             TEXT                 not null,
   password             TEXT                 not null,
   user_type            INT2                 not null,
   created              TIMESTAMP                 null,
   updated              TIMESTAMP                 null,
   constraint PK_WZ_USER primary key (id)
);

comment on column wz_user.user_type is
'0-超管
1--客户
2-销售经理
3-销售代表
';


insert into wz_user(id, username, password, user_type,created, updated)
VALUES(nextval('wz_user_id_seq'::regclass), 'administrator','sha1$f17d5$31c0ec4c9e48370ac5787904b839b0a6eb5f39fb', 0, now(),now());

/*==============================================================*/
/* Index: idx_username                                          */
/*==============================================================*/
create unique index idx_username on wz_user (
username
);

alter table wz_project_content
   add constraint FK_PJ_CTT_RF_PJ_METADATA foreign key (project_metadata_id)
      references wz_project_metadata (id)
      on delete restrict on update restrict;

alter table wz_project_search_condition
   add constraint FK_PJSCHCNDTION_RF_PJ_MDATA foreign key (project_metadata_id)
      references wz_project_metadata (id)
      on delete restrict on update restrict;

alter table wz_project_user_permission
   add constraint FK_PJ_USER_PMISN_RF_USER foreign key (user_id)
      references wz_user (id)
      on delete restrict on update restrict;

alter table wz_project_user_permission
   add constraint FK_PJ_USER_PMISN_RF_PJ_MDATA foreign key (project_metadata_id)
      references wz_project_metadata (id)
      on delete restrict on update restrict;


CREATE SEQUENCE s_profile;

CREATE SEQUENCE s_product;

create table profile (
   	id_profile           	BIGINT               not null DEFAULT (nextval('s_profile')),
   	surname_profile         VARCHAR(100)         not null,
   	name_profile            VARCHAR(100)         not null,
   	birthday_profile        DATE                 not null,
   	phone_profile    		VARCHAR(20)          not null UNIQUE,
   	email_profile     		VARCHAR(100)         null UNIQUE,
	password_profile		VARCHAR(100)         null	
);

create table product (
   	id_product           		BIGINT              not null DEFAULT (nextval('s_product')),
   	name_product				VARCHAR(100)		not null,
	price						DECIMAL				not null,
	id_profile					BIGINT              not null
);

alter table profile
   add constraint PK_PROFILE primary key (id_profile);
   
alter table product
   add constraint PK_product primary key (id_product);

alter table product
   add constraint FK_PRODUCT_PROFILE foreign key (id_profile)
      references profile (id_profile)
      on delete cascade on update cascade;
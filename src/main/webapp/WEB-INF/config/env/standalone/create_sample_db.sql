/*
 * Creates sample schema for admin user in Derby.
 *
 * Sample schema creates everything required to login and create an offender.
 * With the addition of admin screens to add entities, all that should be
 * required in this script is the creation of the initial "ADMIN" user.
 *
 * Author: Stephen Abson
 */

/* Allows user of account to be null - required to add first user */
alter table "USER_ACCOUNT" alter "USER_ID" null;

/* Creates AUDIT user. */
insert into "USER_ACCOUNT" ("ID", "USERNAME") values (1, 'ADMIN');
insert into "PERSON" (
    "ID", "CREATE_ACCOUNT_ID", "CREATE_DATE", "UPDATE_ACCOUNT_ID", "UPDATE_DATE")
  values (1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
insert into "PERSON_NAME" (
     "ID", "PERSON_ID", "LAST_NAME", "FIRST_NAME",
     "CREATE_ACCOUNT_ID", "CREATE_DATE",
     "UPDATE_ACCOUNT_ID", "UPDATE_DATE")
  values (1, 1, 'Administrator', 'System', 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
insert into "PERSON_TO_NAME" ("PERSON_ID", "NAME_ID") values (1, 1);
update "USER_ACCOUNT" set "USER_ID" = 1 where "ID" = 1;

/* Prevents user of account from being null. */
alter table "USER_ACCOUNT" alter "USER_ID" not null;

/* Updates sequences. */
values next value for "PERSON_SEQ";
values next value for "PERSON_NAME_SEQ";
values next value for "USER_ACCOUNT_SEQ";

/* Creates Montana in the USA */
insert into "COUNTRY" ("ID", "NAME", "ABBREVIATION", "VALID")
  values (1, 'USA', 'USA', 'Y');
insert into "STATE" ("ID", "NAME", "ABBREVIATION", "VALID", "COUNTRY_ID", "HOME")
  values (1, 'Montana', 'MT', 'Y', 1, 'Y');
values next value for "COUNTRY_SEQ";
values next value for "STATE_SEQ";

/* 
 * Creates demographic entites required to create an offender - only needed until
 * admin screens exist for demographics entities - SA
 */
insert into "EYE_COLOR" ("ID", "NAME", "VALID")
  values (1, 'Brown', 'Y');
values next value for "EYE_COLOR_SEQ";
insert into "HAIR_COLOR" ("ID", "NAME", "VALID")
  values (1, 'Brown', 'Y');
values next value for "HAIR_COLOR_SEQ";
insert into "RACE" ("ID", "NAME", "VALID")
  values (1, 'White', 'Y');
values next value for "RACE_SEQ";
insert into "MARITAL_STATUS" ("ID", "NAME", "VALID")
  values (1, 'Single', 'Y');
values next value for "MARITAL_STATUS_SEQ";
/*
 * Creates audit user for unit tests.
 *
 * Author: Stephen Abson
 */

/* Allows user of account to be null. */
alter table "USER_ACCOUNT" alter "USER_ID" null;

/* Creates OMIS user. */
insert into "USER_ACCOUNT" ("ID", "USERNAME") values (1, 'AUDIT');
insert into "PERSON" (
    "ID", "CREATE_ACCOUNT_ID", "CREATE_DATE", "UPDATE_ACCOUNT_ID", "UPDATE_DATE")
  values (1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
insert into "PERSON_NAME" (
     "ID", "PERSON_ID", "LAST_NAME", "FIRST_NAME",
     "CREATE_ACCOUNT_ID", "CREATE_DATE",
     "UPDATE_ACCOUNT_ID", "UPDATE_DATE")
  values (1, 1, 'User', 'Audit', 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
insert into "PERSON_TO_NAME" ("PERSON_ID", "NAME_ID") values (1, 1);
update "USER_ACCOUNT" set "USER_ID" = 1 where "ID" = 1;

/* Prevents user of account from being null. */
alter table "USER_ACCOUNT" alter "USER_ID" not null;

/* Updates sequences. */
values next value for "PERSON_SEQ";
values next value for "PERSON_NAME_SEQ";
values next value for "USER_ACCOUNT_SEQ";
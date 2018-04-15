/*======================================================================================================*/
/* Dropping tables based on dependency. DO NOT CHANGE THE ORDER                                         */
/*======================================================================================================*/

DROP TABLE IF EXISTS admin_wing_members CASCADE;

DROP TABLE IF EXISTS club_members CASCADE;

DROP TABLE IF EXISTS council_members CASCADE;

DROP TABLE IF EXISTS event_participants CASCADE;

DROP TABLE IF EXISTS invites CASCADE;

DROP TABLE IF EXISTS notifications CASCADE;

DROP TABLE IF EXISTS program_members CASCADE;

DROP TABLE IF EXISTS schedules CASCADE;


DROP TABLE IF EXISTS clubs CASCADE;

DROP TABLE IF EXISTS events CASCADE;

DROP TABLE IF EXISTS event_status CASCADE;


DROP TABLE IF EXISTS user_accounts CASCADE;

DROP TABLE IF EXISTS users CASCADE;


DROP TABLE IF EXISTS admin_wing CASCADE;

DROP TABLE IF EXISTS councils CASCADE;

DROP TABLE IF EXISTS programs CASCADE;

DROP TABLE IF EXISTS roles CASCADE;


DROP TABLE IF EXISTS hierarchy CASCADE;


DROP TABLE IF EXISTS colleges CASCADE;

/*======================================================================================================*/
/* Table: colleges                                                                                      */
/*======================================================================================================*/
CREATE TABLE colleges (
  id_college INTEGER      NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_college)
);

/*======================================================================================================*/
/* Table: programs                                                                                      */
/*======================================================================================================*/
CREATE TABLE programs (
  id_program INTEGER      NOT NULL AUTO_INCREMENT,
  id_college INTEGER      NOT NULL,
  name       VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_program),
  FOREIGN KEY (id_college) REFERENCES colleges (id_college)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: council                                                                                       */
/*======================================================================================================*/
CREATE TABLE councils (
  id_council INTEGER      NOT NULL  AUTO_INCREMENT,
  id_college INTEGER      NOT NULL,
  name       VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_council),
  FOREIGN KEY (id_college) REFERENCES colleges (id_college)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: admin_wing                                                                                    */
/*======================================================================================================*/
CREATE TABLE admin_wing (
  id_admin_wing INTEGER      NOT NULL  AUTO_INCREMENT,
  id_college    INTEGER      NOT NULL,
  name          VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_admin_wing),
  FOREIGN KEY (id_college) REFERENCES colleges (id_college)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: hierarchy                                                                                     */
/*======================================================================================================*/
CREATE TABLE hierarchy (
  id_hierarchy INTEGER      NOT NULL  AUTO_INCREMENT,
  table_name   VARCHAR(255) NOT NULL,
  description  VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_hierarchy)
);

/*======================================================================================================*/
/* Table: roles                                                                                         */
/*======================================================================================================*/
CREATE TABLE roles (
  id_role         INTEGER      NOT NULL  AUTO_INCREMENT,
  name            VARCHAR(255) NOT NULL,
  privilege_level INTEGER      NOT NULL,
  id_hierarchy    INTEGER      NOT NULL,
  id_entity       INTEGER      NOT NULL,
  PRIMARY KEY (id_role),
  FOREIGN KEY (id_hierarchy) REFERENCES hierarchy (id_hierarchy)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: users                                                                                         */
/*======================================================================================================*/
CREATE TABLE users (
  id_user     INTEGER      NOT NULL  AUTO_INCREMENT,
  id_role     INTEGER      NOT NULL,
  user_name   VARCHAR(255) NOT NULL  UNIQUE,
  first_name  VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255),
  last_name   VARCHAR(255) NOT NULL,
  email       VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_user),
  FOREIGN KEY (id_role) REFERENCES roles (id_role)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: user_accounts                                                                                 */
/*======================================================================================================*/
CREATE TABLE user_accounts (
  id_user_account INTEGER      NOT NULL  AUTO_INCREMENT,
  id_user         INTEGER      NOT NULL  UNIQUE,
  user_name       VARCHAR(255) NOT NULL  UNIQUE,
  password        VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_user_account),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE
);


/*======================================================================================================*/
/* Table: clubs                                                                                         */
/*======================================================================================================*/
CREATE TABLE clubs (
  id_club      INTEGER      NOT NULL  AUTO_INCREMENT,
  id_hierarchy INTEGER      NOT NULL,
  name         VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_club),
  FOREIGN KEY (id_hierarchy) REFERENCES hierarchy (id_hierarchy)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: event_status                                                                                  */
/*======================================================================================================*/
CREATE TABLE event_status (
  id_status      INTEGER      NOT NULL  AUTO_INCREMENT,
  status_message VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_status)
);

/*======================================================================================================*/
/* Table: events                                                                                        */
/*======================================================================================================*/
CREATE TABLE events (
  id_event     INTEGER      NOT NULL  AUTO_INCREMENT,
  id_hierarchy INTEGER      NOT NULL,
  id_creator   INTEGER      NOT NULL,
  name         VARCHAR(255) NOT NULL,
  venue        VARCHAR(255) NOT NULL,
  start_time   TIMESTAMP    NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  end_time     TIMESTAMP    NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  status       INTEGER      NOT NULL,
  PRIMARY KEY (id_event),
  FOREIGN KEY (id_hierarchy) REFERENCES hierarchy (id_hierarchy)
    ON DELETE CASCADE,
  FOREIGN KEY (id_creator) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (status) REFERENCES event_status (id_status)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: program_members                                                                               */
/*======================================================================================================*/
CREATE TABLE program_members (
  id_program_members INTEGER NOT NULL,
  id_user            INTEGER NOT NULL,
  id_program         INTEGER NOT NULL,
  PRIMARY KEY (id_program_members),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_program) REFERENCES programs (id_program)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: council_members                                                                               */
/*======================================================================================================*/
CREATE TABLE council_members (
  id_council_members INTEGER NOT NULL,
  id_user            INTEGER NOT NULL,
  id_council         INTEGER NOT NULL,
  PRIMARY KEY (id_council_members),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_council) REFERENCES councils (id_council)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: admin_wing_members                                                                            */
/*======================================================================================================*/
CREATE TABLE admin_wing_members (
  id_admin_wing_members INTEGER NOT NULL,
  id_user               INTEGER NOT NULL,
  id_admin_wing         INTEGER NOT NULL,
  PRIMARY KEY (id_admin_wing_members),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_admin_wing) REFERENCES admin_wing (id_admin_wing)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: club_members                                                                                  */
/*======================================================================================================*/
CREATE TABLE club_members (
  id_club_members INTEGER NOT NULL,
  id_user         INTEGER NOT NULL,
  id_club         INTEGER NOT NULL,
  PRIMARY KEY (id_club_members),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_club) REFERENCES clubs (id_club)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: event_participants                                                                            */
/*======================================================================================================*/
CREATE TABLE event_participants (
  id_event_participants INTEGER NOT NULL,
  id_user               INTEGER NOT NULL,
  id_event              INTEGER NOT NULL,
  PRIMARY KEY (id_event_participants),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_event) REFERENCES events (id_event)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: invites                                                                                       */
/*======================================================================================================*/
CREATE TABLE invites (
  id_invite  INTEGER NOT NULL  AUTO_INCREMENT,
  id_invitee INTEGER NOT NULL,
  id_event   INTEGER NOT NULL,
  PRIMARY KEY (id_invite),
  FOREIGN KEY (id_invitee) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_event) REFERENCES events (id_event)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: schedules                                                                                     */
/*======================================================================================================*/
CREATE TABLE schedules (
  id_schedule INTEGER NOT NULL  AUTO_INCREMENT,
  id_user     INTEGER NOT NULL,
  id_event    INTEGER NOT NULL,
  PRIMARY KEY (id_schedule),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE,
  FOREIGN KEY (id_event) REFERENCES events (id_event)
    ON DELETE CASCADE
);

/*======================================================================================================*/
/* Table: notifications                                                                                 */
/*======================================================================================================*/
CREATE TABLE notifications (
  id_notification INTEGER      NOT NULL AUTO_INCREMENT,
  id_user         INTEGER      NOT NULL,
  message         VARCHAR(255) NOT NULL,
  read_flag       BIT(1)       NOT NULL,
  PRIMARY KEY (id_notification),
  FOREIGN KEY (id_user) REFERENCES users (id_user)
    ON DELETE CASCADE
);
/*
 *  Copyright (C) 2012 Bill Antonia
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.beaconhillcott.moodlerest;

/**
 *
 * @author root
 */
public class MoodleServicesArchived {
  
  // Group functions
  /**
   *
   */
  public static final String CORE_GROUP_CREATE_GROUPS="core_group_create_groups";
  /**
   *
   */
  public static final String CORE_GROUP_GET_GROUPS="core_group_get_groups";
  /**
   *
   */
  public static final String CORE_GROUP_GET_COURSE_GROUPS="core_group_get_course_groups";
  /**
   *
   */
  public static final String CORE_GROUP_DELETE_GROUPS="core_group_delete_groups";
  /**
   *
   */
  public static final String CORE_GROUP_GET_GROUP_MEMBERS="core_group_get_group_members";
  /**
   *
   */
  public static final String CORE_GROUP_ADD_GROUP_MEMBERS="core_group_add_group_members";
  /**
   *
   */
  public static final String CORE_GROUP_DELETE_GROUP_MEMBERS="core_group_delete_group_members";
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_GROUP_CREATE_GROUPS="moodle_group_create_groups";
  /**
   *
   */
  public static final String MOODLE_GROUP_GET_GROUPS="moodle_group_get_groups";
  /**
   *
   */
  public static final String MOODLE_GROUP_GET_COURSE_GROUPS="moodle_group_get_course_groups";
  /**
   *
   */
  public static final String MOODLE_GROUP_DELETE_GROUPS="moodle_group_delete_groups";
  /**
   *
   */
  public static final String MOODLE_GROUP_GET_GROUP_MEMBERS="moodle_group_get_group_members";
  /**
   *
   */
  public static final String MOODLE_GROUP_ADD_GROUP_MEMBERS="moodle_group_add_group_members";
  /**
   *
   */
  public static final String MOODLE_GROUP_DELETE_GROUP_MEMBERS="moodle_group_delete_group_members";
  /* New Calls */
  public static final String CORE_GROUP_ASSIGN_GROUPING="core_group_assign_grouping";
  public static final String CORE_GROUP_CREATE_GROUPINGS="core_group_create_groupings";
  public static final String CORE_GROUP_DELETE_GROUPINGS="core_group_delete_groupings";
  public static final String CORE_GROUP_GET_COURSE_GROUPINGS="core_group_get_course_groupings";
  public static final String CORE_GROUP_GET_GROUPINGS="core_group_get_groupings";
  public static final String CORE_GROUP_UNASSIGN_GROUPING="core_group_unassign_grouping";
  public static final String CORE_GROUP_UPDATE_GROUPINGS="core_group_update_groupings";
  
  // Files functions
  /**
   *
   */
  public static final String CORE_FILES_GET_FILES="core_files_get_files";
  /**
   *
   */
  public static final String CORE_FILES_UPLOAD="core_files_upload";
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_FILES_GET_FILES="moodle_files_get_files";
  /**
   *
   */
  public static final String MOODLE_FILES_UPLOAD="moodle_files_upload";
  
  // User functions
  /**
   *
   */
  public static final String CORE_USER_CREATE_USERS="core_user_create_users";
  /**
   *
   */
  public static final String CORE_USER_GET_USERS_BY_ID="core_user_get_users_by_id";
  /**
   *
   */
  public static final String CORE_USER_GET_COURSE_USER_PROFILE="core_user_get_course_user_profile";
  /**
   *
   */
  public static final String CORE_USER_DELETE_USERS="core_user_delete_users";
  /**
   *
   */
  public static final String CORE_USER_UPDATE_USERS="core_user_update_users";
  /**
   * 
   */
  public static final String CORE_USER_GET_USERS="core_user_get_users";
  /**
   * TO DO
   */
  public static final String CORE_USER_GET_USERS_WITH_CAPABILITY="core_users_get_users_with_capability";
  public static final String CORE_USER_GET_USERS_BY_FIELD="core_users_get_users_by_field";

  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_USER_CREATE_USERS="moodle_user_create_users";
  /**
   *
   */
  public static final String MOODLE_USER_GET_USERS_BY_ID="moodle_user_get_users_by_id";
  /**
   *
   */
  public static final String MOODLE_USER_GET_USERS_BY_COURSEID="moodle_user_get_users_by_courseid";
  /**
   *
   */
  public static final String MOODLE_USER_GET_COURSE_PARTICIPANTS_BY_ID="moodle_user_get_course_participants_by_id";
  /**
   *
   */
  public static final String MOODLE_USER_DELETE_USERS="moodle_user_delete_users";
  /**
   *
   */
  public static final String MOODLE_USER_UPDATE_USERS="moodle_user_update_users";
  
  // Enrol functions
  /**
   *
   */
  public static final String CORE_ENROL_GET_ENROLLED_USERS="core_enrol_get_enrolled_users";
  /**
   *
   */
  public static final String CORE_ENROL_GET_USERS_COURSES="core_enrol_get_users_courses";
  /**
   *
   */
  public static final String ENROL_MANUAL_ENROL_USERS="enrol_manual_enrol_users";
  public static final String CORE_ENROL_GET_ENROLLED_USERS_WITH_CAPABILITY="core_enrol_get_enrolled_users_with_capability";
  
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_ENROL_GET_ENROLLED_USERS="moodle_enrol_get_enrolled_users";
  /**
   *
   */
  public static final String MOODLE_ENROL_GET_USERS_COURSES="moodle_enrol_get_users_courses";
  
  // Role functions
  /**
   *
   */
  public static final String CORE_ROLE_ASSIGN_ROLES="core_role_assign_roles";
  /**
   *
   */
  public static final String CORE_ROLE_UNASSIGN_ROLES="core_role_unassign_roles";
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_ROLE_ASSIGN="moodle_role_assign";
  /**
   *
   */
  public static final String MOODLE_ROLE_UNASSIGN="moodle_role_unassign";
  
  // Course functions
  /**
   *
   */
  public static final String CORE_COURSE_GET_COURSES="core_course_get_courses";
  /**
   *
   */
  public static final String CORE_COURSE_CREATE_COURSES="core_course_create_courses";
  /**
   *
   */
  public static final String CORE_COURSE_GET_CONTENTS="core_course_get_contents";
  /**
   *
   */
  public static final String CORE_COURSE_CREATE_CATEGORIES="core_course_create_categories";
  /**
   *
   */
  public static final String CORE_COURSE_DELETE_CATEGORIES="core_course_delete_categories";
  /**
   * 
   */
  public static final String CORE_COURSE_GET_CATEGORIES="core_course_get_categories";
  /**
   * 
   */
  public static final String CORE_COURSE_UPDATE_CATEGORIES="core_course_update_categories";
  
  public static final String CORE_COURSE_DELETE_COURSES="core_course_delete_courses";
  
  public static final String CORE_COURSE_DUPLICATE_COURSE="core_course_duplicate_course";
  public static final String CORE_COURSE_IMPORT_COURSE="core_course_import_course";
  
  // To Do
  public static final String CORE_COURSE_DELETE_MODULES="core_course_delete_modules";
  public static final String CORE_COURSE_UPDATE_COURSES="core_course_update_courses";
  
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_COURSE_GET_COURSES="moodle_course_get_courses";
  /**
   *
   */
  public static final String MOODLE_COURSE_CREATE_COURSES="moodle_course_create_courses";
  
  // Message functions
  /**
   *
   */
  public static final String CORE_MESSAGE_SEND_INSTANT_MESSAGES="core_message_send_instant_messages";
  
  // To Do
  public static final String CORE_MESSAGE_BLOCK_CONTACTS="core_message_block_contacts";
  public static final String CORE_MESSAGE_CREATE_CONTACTS="core_message_create_contacts";
  public static final String CORE_MESSAGE_DELETE_CONTACTS="core_message_delete_contacts";
  public static final String CORE_MESSAGE_GET_CONTACTS="core_message_get_contacts";
  public static final String CORE_MESSAGE_SEARCH_CONTACTS="core_message_search_contacts";
  public static final String CORE_MESSAGE_UNBLOCK_CONTACTS="core_message_unblock_contacts";
  
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_MESSAGE_SEND_INSTANTMESSAGES="moodle_message_send_instantmessages";
  
  // Notes functions
  /**
   *
   */
  public static final String CORE_NOTES_CREATE_NOTES="core_notes_create_notes";
    
  //To Do
  public static final String CORE_NOTES_DELETE_NOTES="core_notes_delete_notes";
  public static final String CORE_NOTES_GET_NOTES="core_notes_get_notes";
  public static final String CORE_UPDATE_GET_NOTES="core_notes_update_notes";
  
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_NOTES_CREATE_NOTES="moodle_notes_create_notes";
  
  // Webservice functions
  /**
   *
   */
  public static final String CORE_WEBSERVICE_GET_SITE_INFO="core_webservice_get_site_info";
  //Legacy calls
  /**
   *
   */
  public static final String MOODLE_WEBSERVICE_GET_SITE_INFO="moodle_webservice_get_siteinfo";

  public static final String CORE_CALENDAR_CREATE_CALENDAR_EVENTS="core_calendar_create_calendar_events";
  public static final String CORE_CALENDAR_DELETE_CALENDAR_EVENTS="core_calendar_delete_calendar_events";
  public static final String CORE_CALENDAR_GET_CALENDAR_EVENTS="core_calendar_get_calendar_events";

  public static final String CORE_COHORT_GET_COHORTS="core_cohort_get_cohorts";
  public static final String CORE_COHORT_GET_COHORT_MEMBERS="core_cohort_get_cohort_members";
  public static final String CORE_COHORT_DELETE_COHORTS="core_cohort_delete_cohorts";
  public static final String CORE_COHORT_DELETE_COHORT_MEMBERS="core_cohort_delete_cohort_members";

  /**
   * TO DO
   */
  public static final String MOD_ASSIGN_GET_SUBMISSIONS="mod_assign_get_submissions";
  /**
   * TO DO
   */
  public static final String MOD_ASSIGN_GET_ASSIGNMENTS="mod_assign_get_assignments";
  /**
   * TO DO
   */
  public static final String MOD_ASSIGN_GET_GRADES="mod_assign_get_grades";
  
  //To Do
  public static final String MOD_FORUM_GET_FORUM_DISCUSSIONS="mod_forum_get_forum_discussions";
  public static final String MOD_FORUM_GET_FORUMS_BY_COURSES="mod_forum_get_forums_by_courses";
  
  public static final String CORE_GET_COMPONENT_STRINGS="core_get_component_strings";
  public static final String CORE_GET_STRINGS="core_get_strings";
  public static final String CORE_GET_STRING="core_get_string";

  //To Do
  public static final String CORE_GRADE_GET_GRADE_DEFINITIONS="core_grade_get_grade_definitions";

  //To Do
  public static final String CORE_COHORT_ADD_COHORT_MEMBERS="core_cohort_add_cohort_members";
  public static final String CORE_COHORT_CREATE_COHORTS="core_cohort_create_cohorts";
  public static final String CORE_COHORT_UPDATE_COHORTS="core_cohort_update_cohorts";
  
  /**
   *
   */
  public static final String ENCODING="UTF-8";
  
  public static final Long ROLE_MANAGER=1L;
  public static final Long ROLE_COURSE_CREATOR=2L;
  public static final Long ROLE_TEACHER=3L;
  public static final Long ROLE_NON_EDITING_TEACHER=4L;
  public static final Long ROLE_STUDENT=5L;
  public static final Long ROLE_GUEST=6L;
  public static final Long ROLE_AUTHENTICATED_USER=7L;
  public static final Long ROLE_AUTHENTICATED_USER_ON_FRONT_PAGE=8L;
  
  public static final Integer GROUPING_DESCRIPTIONFORMAT_MOODLE=0;
  public static final Integer GROUPING_DESCRIPTIONFORMAT_HTML=1;
  public static final Integer GROUPING_DESCRIPTIONFORMAT_PLAIN=2;
  public static final Integer GROUPING_DESCRIPTIONFORMAT_MARKDOWN=4;
  
}


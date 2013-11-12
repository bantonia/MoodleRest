/*
* Copyright (C) 2012 Bill Antonia
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
*/

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
* <p>
*         Class containing the Moodle web services function names as constants.
* </p>
*
* @author MoodyProject Team (Thanks to Bill Antonia)
*/
public enum MoodleServices implements Serializable {

        // ####################
        // # ACTUAL FUNCTIONS #
        // ####################
        // GROUP API
        CORE_GROUP_CREATE_GROUPS("core_group_create_groups"), // MoodleRestGroup      
        CORE_GROUP_GET_GROUPS("core_group_get_groups"), // MoodleRestGroup
        CORE_GROUP_GET_COURSE_GROUPS("core_group_get_course_groups"), // MoodleRestGroup
        CORE_GROUP_DELETE_GROUPS("core_group_delete_groups"), // MoodleRestGroup
        CORE_GROUP_GET_GROUP_MEMBERS("core_group_get_group_members"), // MoodleRestGroup
        CORE_GROUP_ADD_GROUP_MEMBERS("core_group_add_group_members"), // MoodleRestGroup
        CORE_GROUP_DELETE_GROUP_MEMBERS("core_group_delete_group_members"), // MoodleRestGroup
        CORE_GROUP_CREATE_GROUPINGS("core_group_create_groupings"), // MoodleRestGroup
        CORE_GROUP_UPDATE_GROUPINGS("core_group_update_groupings"), // MoodleRestGroup
        CORE_GROUP_GET_GROUPINGS("core_group_get_groupings"), // MoodleRestGroup
        CORE_GROUP_GET_COURSE_GROUPINGS("core_group_get_course_groupings"), // MoodleRestGroup
        CORE_GROUP_DELETE_GROUPINGS("core_group_delete_groupings"), // MoodleRestGroup
        CORE_GROUP_ASSIGN_GROUPING("core_group_assign_grouping"), // MoodleRestGroup
        CORE_GROUP_UNASSIGN_GROUPING("core_group_unassign_grouping"), // MoodleRestGroup

        // FILES API
        CORE_FILES_GET_FILES("core_files_get_fNot yet implementediles"), //MoodleRestFile
        CORE_FILES_UPLOAD("core_files_upload"), //MoodleRestFile

        // USER API
        CORE_USER_CREATE_USERS("core_user_create_users"), // MoodleRestUser       
        CORE_USER_GET_USERS_BY_ID("core_user_get_users_by_id"), // MoodleRestUser
        CORE_USER_GET_COURSE_USER_PROFILES("core_user_get_course_user_profiles"), // MoodleRestUser
        CORE_USER_DELETE_USERS("core_user_delete_users"), // MoodleRestUser
        CORE_USER_UPDATE_USERS("core_user_update_users"), // MoodleRestUser       
        CORE_USER_GET_USERS("core_user_get_users"), // MoodleRestUser       
        CORE_USER_GET_USERS_BY_FIELD("core_user_get_users_by_field"), // MoodleRestUser

        // ENROL
        CORE_ENROL_GET_ENROLLED_USERS_WITH_CAPABILITY("core_enrol_get_enrolled_users_with_capability"), // MoodleRestEnrol       
        CORE_ENROL_GET_ENROLLED_USERS("core_enrol_get_enrolled_users"), // MoodleRestEnrol       
        CORE_ENROL_GET_USERS_COURSES("core_enrol_get_users_courses"), // MoodleRestEnrol
        ENROL_MANUAL_ENROL_USERS("enrol_manual_enrol_users"), // MoodleRestUserEnrolment       

        // ROLE
        CORE_ROLE_ASSIGN_ROLES("core_role_assign_roles"),  //MoodleRestEnrol       
        CORE_ROLE_UNASSIGN_ROLES("core_role_unassign_roles"), //MoodleRestEnrol

        // COURSE
        CORE_COURSE_GET_CONTENTS("core_course_get_contents"), // MoodleRestCourse
        CORE_COURSE_GET_COURSES("core_course_get_courses"), // MoodleRestCourse
        CORE_COURSE_CREATE_COURSES("core_course_create_courses"), // MoodleRestCourse
        CORE_COURSE_DELETE_COURSES("core_course_delete_courses"), // MoodleRestCourse
        CORE_COURSE_DUPLICATE_COURSE("core_course_duplicate_course"), // Not yet implemented
        CORE_COURSE_GET_CATEGORIES("core_course_get_categories"), // MoodleRestCourse
        CORE_COURSE_CREATE_CATEGORIES("core_course_create_categories"), // MoodleRestCourse
        CORE_COURSE_UPDATE_CATEGORIES("core_course_update_categories"), // MoodleRestCourse
        CORE_COURSE_DELETE_CATEGORIES("core_course_delete_categories"), // MoodleRestCourse
        CORE_COURSE_IMPORT_COURSE("core_course_import_courses"), // Not yet implemented
        CORE_COURSE_DELETE_MODULES("core_course_delete_modules"), // MoodleRestCourse
        CORE_COURSE_UPDATE_COURSES("core_course_update_courses"), // Not yet implemented

        // MESSAGE
        CORE_MESSAGE_SEND_INSTANT_MESSAGES("core_message_send_instant_messages"), // MoodleRestMessage
        CORE_MESSAGE_CREATE_CONTACTS("core_message_create_contacts"), // MoodleRestMessage
        CORE_MESSAGE_DELETE_CONTACTS("core_message_delete_contacts"), // MoodleRestMessage
        CORE_MESSAGE_BLOCK_CONTACTS("core_message_block_contacts"), // MoodleRestMessage
        CORE_MESSAGE_UNBLOCK_CONTACTS("core_message_unblock_contacts"), // MoodleRestMessage
        CORE_MESSAGE_GET_CONTACTS("core_message_get_contacts"), // MoodleRestMessage
        CORE_MESSAGE_SEARCH_CONTACTS("core_message_search_contacts"), // MoodleRestMessage (Moodle bug?)

        // NOTES
        CORE_NOTES_CREATE_NOTES("core_notes_create_notes"), // MoodleRestNotes
        CORE_NOTES_DELETE_NOTES("core_notes_delete_notes"), // MoodleRestNotes (Moodle bug)
        CORE_NOTES_GET_NOTES("core_notes_get_notes"), // MoodleRestNotes (Moodle bug)
        CORE_NOTES_UPDATE_NOTES("core_notes_update_notes"), // Not yet implemented

        // WEBSERVICE
        CORE_WEBSERVICE_GET_SITE_INFO("core_webservice_get_site_info"), // MoodleRestWebService

        // GET
        CORE_GET_STRING("core_get_string"), // Not yet implemented
        CORE_GET_STRINGS("core_get_strings"), // Not yet implemented
        CORE_GET_COMPONENT_STRINGS("core_get_component_strings"), // Not yet implemented

        // ASSIGN
        MOD_ASSIGN_GET_GRADES("core_assign_get_grades"), // Not yet implemented
        MOD_ASSIGN_GET_ASSIGNMENTS("core_assign_get_assignments"), // MoodleRestModAssign
        MOD_ASSIGN_GET_SUBMISSIONS("core_assign_get_submissions"), // MoodleRestModAssign

        // COHORT
        CORE_COHORT_CREATE_COHORTS("core_cohort_create_cohorts"), // MoodleRestCohort
        CORE_COHORT_DELETE_COHORTS("core_cohort_delete_cohorts"), // MoodleRestCohort
        CORE_COHORT_GET_COHORTS("core_cohort_get_cohorts"), // MoodleRestCohort
        CORE_COHORT_UPDATE_COHORTS("core_cohort_update_cohorts"), // MoodleRestCohort (Dodgy Moodle parameter definition for id)
        CORE_COHORT_ADD_COHORT_MEMBERS("core_cohort_add_cohort_members"), // MoodleRestCohort
        CORE_COHORT_DELETE_COHORT_MEMBERS("core_cohort_delete_cohort_members"), // MoodleRestCohort
        CORE_COHORT_GET_COHORT_MEMBERS("core_cohort_get_cohort_members"), // MoodleRestCohort

        // GRADE
        CORE_GRADE_GET_DEFINITIONS("core_grade_get_definitions"), // Not yet implemented

        // CALENDAR
        CORE_CALENDAR_DELETE_CALENDAR_EVENTS("core_calendar_delete_calendar_events"), //MoodleRestCalendar
        CORE_CALENDAR_GET_CALENDAR_EVENTS("core_calendar_get_calendar_events"), //MoodleRestCalendar
        CORE_CALENDAR_CREATE_CALENDAR_EVENTS("core_calendar_create_calendar_events"), //MoodleRestCalendar

        // FORUM
        MOD_FORUM_GET_FORUMS_BY_COURSES("mod_forum_get_forums_by_courses"), // Not yet implemented
        MOD_FORUM_GET_FORUM_DISCUSSIONS("mod_forum_get_forum_discussions"), // Not yet implemented

        
        // ####################
        // # LEGACY FUNCTIONS #
        // ####################
        // GROUP API
        MOODLE_GROUP_CREATE_GROUPS("moodle_group_create_groups"), // MoodleRestGroup
        MOODLE_GROUP_GET_GROUPS("moodle_group_get_groups"), // MoodleRestGroup
        MOODLE_GROUP_GET_COURSE_GROUPS("moodle_group_get_course_groups"), // MoodleRestGroup
        MOODLE_GROUP_DELETE_GROUPS("moodle_group_delete_groups"), // MoodleRestGroup
        MOODLE_GROUP_GET_GROUPMEMBERS("moodle_group_get_groupmembers"), // MoodleRestGroup
        MOODLE_GROUP_ADD_GROUPMEMBERS("moodle_group_add_groupmembers"), // MoodleRestGroup
        MOODLE_GROUP_DELETE_GROUPMEMBERS("moodle_group_delete_groupmembers"), // MoodleRestGroup

        // FILE API
        MOODLE_FILE_GET_FILES("moodle_file_get_files"), // MoodleRestFile
        MOODLE_FILE_UPLOAD("moodle_file_upload"), // MoodleRestFile

        // USER API
        MOODLE_USER_CREATE_USERS("moodle_user_create_users"), // MoodleRestUser
        MOODLE_USER_GET_USERS_BY_ID("moodle_user_get_users_by_id"), // MoodleRestUser
        MOODLE_USER_GET_USERS_BY_COURSEID("moodle_user_get_users_by_courseid"), // Not yet implemented
        MOODLE_USER_GET_COURSE_PARTICIPANTS_BY_ID("moodle_user_get_course_participants_by_id"), // Not yet implemented
        MOODLE_USER_DELETE_USERS("moodle_user_delete_users"), // MoodleRestUser
        MOODLE_USER_UPDATE_USERS("moodle_user_update_users"), // MoodleRestUser

        // ENROL
        MOODLE_ENROL_GET_ENROLLED_USERS("moodle_enrol_get_enrolled_users"), // MoodleRestEnrol
        MOODLE_ENROL_GET_USERS_COURSES("moodle_enrol_get_users_courses"), // Not yet implemented
        MOODLE_ENROL_MANUAL_ENROL_USERS("moodle_enrol_manual_enrol_users"), // Not yet implemented

        // ROLE
        MOODLE_ROLE_ASSIGN("moodle_role_assign"), // MoodleRestEnrol
        MOODLE_ROLE_UNASSIGN("moodle_role_unassign"), // MoodleRestEnrol

        // COURSE
        MOODLE_COURSE_GET_COURSES("moodle_course_get_courses"), // MoodleRestCourse
        MOODLE_COURSE_CREATE_COURSES("moodle_course_create_courses"), // MoodleRestCourse

        // MESSAGE
        MOODLE_MESSAGE_SEND_INSTANTMESSAGES("moodle_message_send_instantmessages"), // Not yet implemented

        // NOTES
        MOODLE_NOTES_CREATE_NOTES("moodle_notes_create_notes"), // MoodleRestNotes

        // WEBSERVICE
        MOODLE_WEBSERVICE_GET_SITEINFO("moodle_webservice_get_siteinfo"), // MoodleRestWebService

        ENCODING("UTF-8");
        
        private MoodleServices(String value) {
          this.value=value;
        }
        
        private String value;
        
        @Override
        public String toString() {
          return value;
        }
}
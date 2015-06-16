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
        CORE_GROUP_GET_COURSE_USER_GROUPS("core_group_get_course_user_groups"), // Added 2015-05-26 (Untested)
        
        // FILES API
        CORE_FILES_GET_FILES("core_files_get_files"), //MoodleRestFile
        CORE_FILES_UPLOAD("core_files_upload"), //MoodleRestFile

        // USER API
        CORE_USER_CREATE_USERS("core_user_create_users"), // MoodleRestUser Tested 21/01/2014      
        CORE_USER_GET_USERS_BY_ID("core_user_get_users_by_id"), // MoodleRestUser Tested 03/01/2014
        CORE_USER_GET_COURSE_USER_PROFILES("core_user_get_course_user_profiles"), // MoodleRestUser Tested 03/01/2014
        CORE_USER_DELETE_USERS("core_user_delete_users"), // MoodleRestUser Tested 03/02/2014
        CORE_USER_UPDATE_USERS("core_user_update_users"), // MoodleRestUser Tested 21/01/2014       
        CORE_USER_GET_USERS("core_user_get_users"), // MoodleRestUser Tested 03/01/2014 and modified      
        CORE_USER_GET_USERS_BY_FIELD("core_user_get_users_by_field"), // MoodleRestUser Tested 03/01/2014
        CORE_USER_ADD_USER_DEVICE("core_user_add_user_device"), // Moodle 2.6 MoodleRestUser (Untested)
        CORE_USER_REMOVE_USER_DEVICE("core_user_remove_user_device"), // MoodleRestUser added 28/05/2015 (Untested)
        CORE_USER_VIEW_USER_LIST("core_user_view_user_list"), // MoodleRestUser Tested 27/05/2015
        CORE_USER_VIEW_USER_PROFILE("core_user_view_user_profile"), // MoodleRestUser Tested 27/05/2015

        // ENROL
        CORE_ENROL_GET_ENROLLED_USERS_WITH_CAPABILITY("core_enrol_get_enrolled_users_with_capability"), // MoodleRestEnrol Tested 21/01/2014      
        CORE_ENROL_GET_ENROLLED_USERS("core_enrol_get_enrolled_users"), // MoodleRestEnrol Tested 21/01/2014      
        CORE_ENROL_GET_USERS_COURSES("core_enrol_get_users_courses"), // MoodleRestEnrol Tested 21/01/2014 (Only visible courses returned by Moodle)
        CORE_ENROL_GET_COURSE_ENROLMENT_METHODS("core_enrol_get_course_enrolment_methods"), // Moodle 2.6 MoodleRestEnrol (Tested but returned no results, Moodle bug?)
        ENROL_MANUAL_ENROL_USERS("enrol_manual_enrol_users"), // MoodleRestUserEnrolment Tested 22/01/2014
        ENROL_SELF_GET_INSTANCE_INFO("enrol_self_get_instance_info"), // Moodle 2.6 MoodleRestEnrol Tested 21/01/2014
        ENROL_MANUAL_UNENROL_USERS("enrol_manual_unenrol_users"), // Added 2015-05-26 (Untested)

        // ROLE
        CORE_ROLE_ASSIGN_ROLES("core_role_assign_roles"),  //MoodleRestEnrol Tested 21/01/2014, returns an invalidparameter exception when tested with no optional parameters. Functions correctly when contextlevel and instanceid set.
        CORE_ROLE_UNASSIGN_ROLES("core_role_unassign_roles"), //MoodleRestEnrol Tested 21/01/2014, returns an invalidparameter exception when tested with no optional parameters. Functions when optional parameters set to the same values tested for core_role_assign_roles.

        // COURSE
        CORE_COURSE_GET_CONTENTS("core_course_get_contents"), // MoodleRestCourse Tested 21/01/2014
        CORE_COURSE_GET_COURSES("core_course_get_courses"), // MoodleRestCourse Tested 19/12/2013
        CORE_COURSE_CREATE_COURSES("core_course_create_courses"), // MoodleRestCourse Tested 02/02/2014
        CORE_COURSE_DELETE_COURSES("core_course_delete_courses"), // MoodleRestCourse Tested 02/02/2014
        CORE_COURSE_DUPLICATE_COURSE("core_course_duplicate_course"), // MoodleRestCourse (Untested)
        CORE_COURSE_GET_CATEGORIES("core_course_get_categories"), // MoodleRestCourse Tested 01/02/2014
        CORE_COURSE_CREATE_CATEGORIES("core_course_create_categories"), // MoodleRestCourse Tested 02/02/2014
        CORE_COURSE_UPDATE_CATEGORIES("core_course_update_categories"), // MoodleRestCourse Tested 02/02/2014
        CORE_COURSE_DELETE_CATEGORIES("core_course_delete_categories"), // MoodleRestCourse Tested 03/02/2014
        CORE_COURSE_IMPORT_COURSE("core_course_import_course"), //  MoodleRestCourse (Untested)
        CORE_COURSE_DELETE_MODULES("core_course_delete_modules"), // MoodleRestCourse
        CORE_COURSE_UPDATE_COURSES("core_course_update_courses"), // MoodleRestCourse Tested 02/02/2014
        CORE_COURSE_VIEW_COURSE("core_course_view_course"), // MoodleRestCourse Tested 27/05/2015

        // CORE MESSAGE
        CORE_MESSAGE_SEND_INSTANT_MESSAGES("core_message_send_instant_messages"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_CREATE_CONTACTS("core_message_create_contacts"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_DELETE_CONTACTS("core_message_delete_contacts"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_BLOCK_CONTACTS("core_message_block_contacts"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_UNBLOCK_CONTACTS("core_message_unblock_contacts"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_GET_CONTACTS("core_message_get_contacts"), // MoodleRestMessage Tested 03/02/2014
        CORE_MESSAGE_SEARCH_CONTACTS("core_message_search_contacts"), // MoodleRestMessage (Tested 03/02/2014 Moodle bug? Tracker MDL-42794)
        CORE_MESSAGE_GET_BLOCKED_USERS("core_message_get_blocked_users"), // MoodleRestMessage added 28/05/2015 (Untested)
        CORE_MESSAGE_GET_MESSAGES("core_message_get_messages"), // MoodleRestMessage added 28/05/2015 (Untested)
        CORE_MESSAGE_MARK_MESSAGE_READ("core_message_mark_message_read"), // MoodleRestMessage added 28/05/2015 (Untested)
        
        // MESSAGE
        MESSAGE_AIRNOTIFIER_ARE_NOTIFICATION_PREFERENCES_CONFIGURED("message_airnotifier_are_notification_preferences_configured"), // MoodleRestMessageAirNotifier added 29/05/2015 (Untested)
        MESSAGE_AIRNOTIFIER_IS_SYSTEM_CONFIGURED("message_airnotifier_is_system_configured"), // MoodleRestMessageAirNotifier added 29/05/2015 (Tested)

        // NOTES
        CORE_NOTES_CREATE_NOTES("core_notes_create_notes"), // MoodleRestNotes
        CORE_NOTES_DELETE_NOTES("core_notes_delete_notes"), // MoodleRestNotes (Moodle bug 2.5)
        CORE_NOTES_GET_NOTES("core_notes_get_notes"), // MoodleRestNotes (Moodle bug 2.5)
        CORE_NOTES_UPDATE_NOTES("core_notes_update_notes"), // MoodleRestNotes (Untested)
        CORE_NOTES_GET_COURSE_NOTES("core_notes_get_course_notes"), // MoodleRestNotes Tested 28/05/2015
        CORE_NOTES_VIEW_NOTES("core_notes_view_notes"), // MoodleRestNotes Tested 27/05/2015
        
        // WEBSERVICE
        CORE_WEBSERVICE_GET_SITE_INFO("core_webservice_get_site_info"), // MoodleRestWebService Tested 22/01/2014

        // GET
        CORE_GET_STRING("core_get_string"), // MoodleRestStrings (Bug in Moodle, will give an invalid parameter error if not altered within Moodle 2.6)
        CORE_GET_STRINGS("core_get_strings"), // MoodleRestStrings Tested 22/01/2014
        CORE_GET_COMPONENT_STRINGS("core_get_component_strings"), // MoodleRestStrings (Tested 17/12/2013)

        // ASSIGN
        MOD_ASSIGN_GET_GRADES("mod_assign_get_grades"), // MoodleRestModAssign (Untested)
        MOD_ASSIGN_GET_ASSIGNMENTS("mod_assign_get_assignments"), // MoodleRestModAssign
        MOD_ASSIGN_GET_SUBMISSIONS("mod_assign_get_submissions"), // MoodleRestModAssign (Untested)
        MOD_ASSIGN_GET_USER_FLAGS("mod_assign_get_user_flags"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_GET_USER_MAPPINGS("mod_assign_get_user_mappings"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_LOCK_SUBMISSIONS("mod_assign_lock_submissions"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_REVEAL_IDENTITIES("mod_assign_reveal_identities"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_REVERT_SUBMISSIONS_TO_DRAFT("mod_assign_revert_submissions_to_draft"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_SAVE_GRADE("mod_assign_save_grade"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_SAVE_SUBMISSION("mod_assign_save_submission"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_SAVE_USER_EXTENSIONS("mod_assign_save_user_extensions"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_SET_USER_FLAGS("mod_assign_set_user_flags"), // Moodle 2.6 Not MoodleRestModAssign (Untested)
        MOD_ASSIGN_SUBMIT_FOR_GRADING("mod_assign_submit_for_grading"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_UNLOCK_SUBMISSIONS("mod_assign_unlock_submissions"), // Moodle 2.6 MoodleRestModAssign (Untested)
        MOD_ASSIGN_SAVE_GRADES("mod_assign_save_grades"), // Moodle 2.7 (No code yet)
        
        // COHORT
        CORE_COHORT_CREATE_COHORTS("core_cohort_create_cohorts"), // MoodleRestCohort Tested 10/06/2014
        CORE_COHORT_DELETE_COHORTS("core_cohort_delete_cohorts"), // MoodleRestCohort
        CORE_COHORT_GET_COHORTS("core_cohort_get_cohorts"), // MoodleRestCohort
        CORE_COHORT_UPDATE_COHORTS("core_cohort_update_cohorts"), // MoodleRestCohort (Dodgy Moodle parameter definition for id)
        CORE_COHORT_ADD_COHORT_MEMBERS("core_cohort_add_cohort_members"), // MoodleRestCohort
        CORE_COHORT_DELETE_COHORT_MEMBERS("core_cohort_delete_cohort_members"), // MoodleRestCohort
        CORE_COHORT_GET_COHORT_MEMBERS("core_cohort_get_cohort_members"), // MoodleRestCohort

        // GRADE
        CORE_GRADE_GET_DEFINITIONS("core_grade_get_definitions"), // MoodleRestGrade (Switched calls to use core_grading_get_definitions, core_grade_get_definitions will be deprecated anyway by Moodle)
        
        // GRADES
        CORE_GRADES_GET_GRADES("core_grades_get_grades"), // Moodle 2.7 (No code yet)
        CORE_GRADES_UPDATE_GRADES("core_grades_update_grades"), // Moodle 2.7 (No code yet)

        // CALENDAR
        CORE_CALENDAR_DELETE_CALENDAR_EVENTS("core_calendar_delete_calendar_events"), //MoodleRestCalendar Tested 20/02/2014
        CORE_CALENDAR_GET_CALENDAR_EVENTS("core_calendar_get_calendar_events"), //MoodleRestCalendar Tested 20/02/2014
        CORE_CALENDAR_CREATE_CALENDAR_EVENTS("core_calendar_create_calendar_events"), //MoodleRestCalendar Tested 20/02/2014

        // FORUM
        MOD_FORUM_GET_FORUMS_BY_COURSES("mod_forum_get_forums_by_courses"), // MoodleRestModForum Tested 22/01/2014
        MOD_FORUM_GET_FORUM_DISCUSSIONS("mod_forum_get_forum_discussions"), // MoodleRestModForum Tested 22/01/2014
        MOD_FORUM_GET_FORUM_DISCUSSION_POSTS("mod_forum_get_forum_discussion_posts"), // Moodle 2.7 (No code yet)
        MOD_FORUM_GET_FORUM_DISCUSSIONS_PAGINATED("mod_forum_get_forum_discussions_paginated"), // No code yet
        MOD_FORUM_VIEW_FORUM("mod_forum_view_forum"), // MoodleRestModForum (Untested)
        MOD_FORUM_VIEW_FORUM_DISCUSSION("mod_forum_view_forum_discussion"), // MoodleRestModForum (Untested)
        
        // GRADING
        CORE_GRADING_GET_DEFINITIONS("core_grading_get_definitions"), // Moodle 2.6 MoodleRestGrade (Untested)
        CORE_GRADING_GET_GRADINGFORM_INSTANCES("core_grading_get_gradingform_instances"), // Moodle 2.6 MoodleRestGrade (Untested)
        CORE_GRADING_SAVE_DEFINITIONS("core_grading_save_definitions"), // No code yet
        
        // COMMENTS
        CORE_COMMENT_GET_COMMENTS("core_comment_get_comments"), // Added 2015-05-26 (Untested)
        
        // COMPLETION
        CORE_COMPLETION_GET_ACTIVITIES_COMPLETION_STATUS("core_comment_get_activities_completion_status"), // Added 2015-06-16 (Untested)
        CORE_COMPLETION_GET_COURSE_COMPLETION_STATUS("core_comment_get_course_completion_status"), // No code yet
        CORE_COMPLETION_UPDATE_ACTIVITY_COMPLETION_STATUS_MANUALLY("core_comment_update_activity_completion_status_manually"), // No code yet
        
        // OUTPUT
        CORE_OUTPUT_LOAD_TEMPLATE("core_output_load_template"), // No code yet
        
        // RATING
        CORE_RATING_GET_ITEM_RATINGS("core_rating_get_item_ratings"), // No code yet
        
        // GRADEREPORT
        GRADEREPORT_USER_GET_GRADES_TABLE("gradereport_user_get_grades_table"), // No code yet
        GRADEREPORT_USER_VIEW_GRADE_REPORT("gradereport_user_view_grade_report"), // No code yet
        
        // DATA
        MOD_DATA_GET_DATABASES_BY_COURSES("mod_data_get_databases_by_courses"), // MoodleRestModData added 29/05/2015 (Untested)
        
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
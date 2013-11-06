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
        CORE_GROUP_CREATE_GROUPS, // MoodleRestGroup      
        CORE_GROUP_GET_GROUPS, // MoodleRestGroup
        CORE_GROUP_GET_COURSE_GROUPS, // MoodleRestGroup
        CORE_GROUP_DELETE_GROUPS, // MoodleRestGroup
        CORE_GROUP_GET_GROUP_MEMBERS, // MoodleRestGroup
        CORE_GROUP_ADD_GROUP_MEMBERS, // MoodleRestGroup
        CORE_GROUP_DELETE_GROUP_MEMBERS, // MoodleRestGroup
        CORE_GROUP_CREATE_GROUPINGS, // MoodleRestGroup
        CORE_GROUP_UPDATE_GROUPINGS, // MoodleRestGroup
        CORE_GROUP_GET_GROUPINGS, // MoodleRestGroup
        CORE_GROUP_GET_COURSE_GROUPINGS, // MoodleRestGroup
        CORE_GROUP_DELETE_GROUPINGS, // MoodleRestGroup
        CORE_GROUP_ASSIGN_GROUPING, // MoodleRestGroup
        CORE_GROUP_UNASSIGN_GROUPING, // MoodleRestGroup

        // FILES API
        CORE_FILES_GET_FILES, //MoodleRestFile
        CORE_FILES_UPLOAD, //MoodleRestFile

        // USER API
        CORE_USER_CREATE_USERS, // MoodleRestUser       
        CORE_USER_GET_USERS_BY_ID, // MoodleRestUser
        CORE_USER_GET_COURSE_USER_PROFILES, // MoodleRestUser
        CORE_USER_DELETE_USERS, // MoodleRestUser
        CORE_USER_UPDATE_USERS, // MoodleRestUser       
        CORE_USER_GET_USERS, // MoodleRestUser       
        CORE_USER_GET_USERS_BY_FIELD, // MoodleRestUser

        // ENROL
        CORE_ENROL_GET_ENROLLED_USERS_WITH_CAPABILITY, // Not yet implemented       
        CORE_ENROL_GET_ENROLLED_USERS, // MoodleRestEnrol       
        CORE_ENROL_GET_USERS_COURSES, // MoodleRestEnrol
        ENROL_MANUAL_ENROL_USERS, // MoodleRestUserEnrolment       

        // ROLE
        CORE_ROLE_ASSIGN_ROLES,  //MoodleRestEnrol       
        CORE_ROLE_UNASSIGN_ROLES, //MoodleRestEnrol

        // COURSE
        CORE_COURSE_GET_CONTENTS, // MoodleRestCourse
        CORE_COURSE_GET_COURSES, // MoodleRestCourse
        CORE_COURSE_CREATE_COURSES, // MoodleRestCourse
        CORE_COURSE_DELETE_COURSES, // MoodleRestCourse
        CORE_COURSE_DUPLICATE_COURSE, // Not yet implemented
        CORE_COURSE_GET_CATEGORIES, // MoodleRestCourse
        CORE_COURSE_CREATE_CATEGORIES, // MoodleRestCourse
        CORE_COURSE_UPDATE_CATEGORIES, // MoodleRestCourse
        CORE_COURSE_DELETE_CATEGORIES, // MoodleRestCourse
        CORE_COURSE_IMPORT_COURSE, // Not yet implemented
        CORE_COURSE_DELETE_MODULES, // Not yet implemented
        CORE_COURSE_UPDATE_COURSES, // Not yet implemented

        // MESSAGE
        CORE_MESSAGE_SEND_INSTANT_MESSAGES, // MoodleRestMessage
        CORE_MESSAGE_CREATE_CONTACTS, // MoodleRestMessage
        CORE_MESSAGE_DELETE_CONTACTS, // MoodleRestMessage
        CORE_MESSAGE_BLOCK_CONTACTS, // MoodleRestMessage
        CORE_MESSAGE_UNBLOCK_CONTACTS, // MoodleRestMessage
        CORE_MESSAGE_GET_CONTACTS, // MoodleRestMessage
        CORE_MESSAGE_SEARCH_CONTACTS, // Not yet implemented

        // NOTES
        CORE_NOTES_CREATE_NOTES, // MoodleRestNotes
        CORE_NOTES_DELETE_NOTES, // Not yet implemented
        CORE_NOTES_GET_NOTES, // Not yet implemented
        CORE_NOTES_UPDATE_NOTES, // Not yet implemented

        // WEBSERVICE
        CORE_WEBSERVICE_GET_SITE_INFO, // MoodleRestWebService

        // GET
        CORE_GET_STRING, // Not yet implemented
        CORE_GET_STRINGS, // Not yet implemented
        CORE_GET_COMPONENT_STRINGS, // Not yet implemented

        // ASSIGN
        MOD_ASSIGN_GET_GRADES, // Not yet implemented
        MOD_ASSIGN_GET_ASSIGNMENTS, // MoodleRestModAssign
        MOD_ASSIGN_GET_SUBMISSIONS, // MoodleRestModAssign

        // COHORT
        CORE_COHORT_CREATE_COHORTS, // Not yet implemented
        CORE_COHORT_DELETE_COHORTS, // MoodleRestCohort
        CORE_COHORT_GET_COHORTS, // MoodleRestCohort
        CORE_COHORT_UPDATE_COHORTS, // Not yet implemented
        CORE_COHORT_ADD_COHORT_MEMBERS, // Not yet implemented
        CORE_COHORT_DELETE_COHORT_MEMBERS, // MoodleRestCohort
        CORE_COHORT_GET_COHORT_MEMBERS, // MoodleRestCohort

        // GRADE
        CORE_GRADE_GET_DEFINITIONS, // Not yet implemented

        // CALENDAR
        CORE_CALENDAR_DELETE_CALENDAR_EVENTS, //MoodleRestCalendar
        CORE_CALENDAR_GET_CALENDAR_EVENTS, //MoodleRestCalendar
        CORE_CALENDAR_CREATE_CALENDAR_EVENTS, //MoodleRestCalendar

        // FORUM
        MOD_FORUM_GET_FORUMS_BY_COURSES, // Not yet implemented
        MOD_FORUM_GET_FORUM_DISCUSSIONS, // Not yet implemented

        
        // ####################
        // # LEGACY FUNCTIONS #
        // ####################
        // GROUP API
        MOODLE_GROUP_CREATE_GROUPS, // MoodleRestGroup
        MOODLE_GROUP_GET_GROUPS, // MoodleRestGroup
        MOODLE_GROUP_GET_COURSE_GROUPS, // MoodleRestGroup
        MOODLE_GROUP_DELETE_GROUPS, // MoodleRestGroup
        MOODLE_GROUP_GET_GROUPMEMBERS, // MoodleRestGroup
        MOODLE_GROUP_ADD_GROUPMEMBERS, // MoodleRestGroup
        MOODLE_GROUP_DELETE_GROUPMEMBERS, // MoodleRestGroup

        // FILE API
        MOODLE_FILE_GET_FILES, // MoodleRestFile
        MOODLE_FILE_UPLOAD, // MoodleRestFile

        // USER API
        MOODLE_USER_CREATE_USERS, // MoodleRestUser
        MOODLE_USER_GET_USERS_BY_ID, // MoodleRestUser
        MOODLE_USER_GET_USERS_BY_COURSEID, // Not yet implemented
        MOODLE_USER_GET_COURSE_PARTICIPANTS_BY_ID, // Not yet implemented
        MOODLE_USER_DELETE_USERS, // MoodleRestUser
        MOODLE_USER_UPDATE_USERS, // MoodleRestUser

        // ENROL
        MOODLE_ENROL_GET_ENROLLED_USERS, // MoodleRestEnrol
        MOODLE_ENROL_GET_USERS_COURSES, // Not yet implemented
        MOODLE_ENROL_MANUAL_ENROL_USERS, // Not yet implemented

        // ROLE
        MOODLE_ROLE_ASSIGN, // MoodleRestEnrol
        MOODLE_ROLE_UNASSIGN, // MoodleRestEnrol

        // COURSE
        MOODLE_COURSE_GET_COURSES, // MoodleRestCourse
        MOODLE_COURSE_CREATE_COURSES, // MoodleRestCourse

        // MESSAGE
        MOODLE_MESSAGE_SEND_INSTANTMESSAGES, // Not yet implemented

        // NOTES
        MOODLE_NOTES_CREATE_NOTES, // MoodleRestNotes

        // WEBSERVICE
        MOODLE_WEBSERVICE_GET_SITEINFO, // MoodleRestWebService

        ENCODING {
                @Override
                public String toString() {
                        return "UTF-8";
        
                }
        }
}
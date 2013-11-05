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
        CORE_GROUP_CREATE_GROUPS,        
        CORE_GROUP_GET_GROUPS,
        CORE_GROUP_GET_COURSE_GROUPS,
        CORE_GROUP_DELETE_GROUPS,        
        CORE_GROUP_GET_GROUP_MEMBERS,        
        CORE_GROUP_ADD_GROUP_MEMBERS,        
        CORE_GROUP_DELETE_GROUP_MEMBERS,        
        CORE_GROUP_CREATE_GROUPINGS,        
        CORE_GROUP_UPDATE_GROUPINGS,        
        CORE_GROUP_GET_GROUPINGS,        
        CORE_GROUP_GET_COURSE_GROUPINGS,        
        CORE_GROUP_DELETE_GROUPINGS,        
        CORE_GROUP_ASSIGN_GROUPING,        
        CORE_GROUP_UNASSIGN_GROUPING,        

        // FILES API
        CORE_FILES_GET_FILES,
        CORE_FILES_UPLOAD,        

        // USER API
        CORE_USER_CREATE_USERS,        
        CORE_USER_GET_USERS_BY_ID,
        CORE_USER_GET_COURSE_USER_PROFILES,
        CORE_USER_DELETE_USERS,
        CORE_USER_UPDATE_USERS,        
        CORE_USER_GET_USERS,        
        CORE_USER_GET_USERS_BY_FIELD,

        // ENROL
        CORE_ENROL_GET_ENROLLED_USERS_WITH_CAPABILITY,        
        CORE_ENROL_GET_ENROLLED_USERS,        
        CORE_ENROL_GET_USERS_COURSES,
        ENROL_MANUAL_ENROL_USERS,        

        // ROLE
        CORE_ROLE_ASSIGN_ROLES,        
        CORE_ROLE_UNASSIGN_ROLES,

        // COURSE
        CORE_COURSE_GET_CONTENTS,        
        CORE_COURSE_GET_COURSES,        
        CORE_COURSE_CREATE_COURSES,
        CORE_COURSE_DELETE_COURSES,        
        CORE_COURSE_DUPLICATE_COURSE,        
        CORE_COURSE_GET_CATEGORIES,        
        CORE_COURSE_CREATE_CATEGORIES,        
        CORE_COURSE_UPDATE_CATEGORIES,        
        CORE_COURSE_DELETE_CATEGORIES,        
        CORE_COURSE_IMPORT_COURSE,
        CORE_COURSE_DELETE_MODULES,        
        CORE_COURSE_UPDATE_COURSES,        

        // MESSAGE
        CORE_MESSAGE_SEND_INSTANT_MESSAGES,        
        CORE_MESSAGE_CREATE_CONTACTS,        
        CORE_MESSAGE_DELETE_CONTACTS,        
        CORE_MESSAGE_BLOCK_CONTACTS,
        CORE_MESSAGE_UNBLOCK_CONTACTS,        
        CORE_MESSAGE_GET_CONTACTS,        
        CORE_MESSAGE_SEARCH_CONTACTS,

        // NOTES
        CORE_NOTES_CREATE_NOTES,        
        CORE_NOTES_DELETE_NOTES,        
        CORE_NOTES_GET_NOTES,        
        CORE_NOTES_UPDATE_NOTES,        

        // WEBSERVICE
        CORE_WEBSERVICE_GET_SITE_INFO,

        // GET
        CORE_GET_STRING,        
        CORE_GET_STRINGS,        
        CORE_GET_COMPONENT_STRINGS,        

        // ASSIGN
        MOD_ASSIGN_GET_GRADES,        
        MOD_ASSIGN_GET_ASSIGNMENTS,        
        MOD_ASSIGN_GET_SUBMISSIONS,        

        // COHORT
        CORE_COHORT_CREATE_COHORTS,        
        CORE_COHORT_DELETE_COHORTS,        
        CORE_COHORT_GET_COHORTS,
        CORE_COHORT_UPDATE_COHORTS,        
        CORE_COHORT_ADD_COHORT_MEMBERS,        
        CORE_COHORT_DELETE_COHORT_MEMBERS,
        CORE_COHORT_GET_COHORT_MEMBERS,        

        // GRADE
        CORE_GRADE_GET_DEFINITIONS,        

        // CALENDAR
        CORE_CALENDAR_DELETE_CALENDAR_EVENTS,        
        CORE_CALENDAR_GET_CALENDAR_EVENTS,        
        CORE_CALENDAR_CREATE_CALENDAR_EVENTS,

        // FORUM
        MOD_FORUM_GET_FORUMS_BY_COURSES,
        MOD_FORUM_GET_FORUM_DISCUSSIONS,

        
        // ####################
        // # LEGACY FUNCTIONS #
        // ####################
        // GROUP API
        MOODLE_GROUP_CREATE_GROUPS,        
        MOODLE_GROUP_GET_GROUPS,        
        MOODLE_GROUP_GET_COURSE_GROUPS,        
        MOODLE_GROUP_DELETE_GROUPS,        
        MOODLE_GROUP_GET_GROUPMEMBERS,        
        MOODLE_GROUP_ADD_GROUPMEMBERS,        
        MOODLE_GROUP_DELETE_GROUPMEMBERS,        

        // FILE API
        MOODLE_FILE_GET_FILES,        
        MOODLE_FILE_UPLOAD,        

        // USER API
        MOODLE_USER_CREATE_USERS,        
        MOODLE_USER_GET_USERS_BY_ID,        
        MOODLE_USER_GET_USERS_BY_COURSEID,        
        MOODLE_USER_GET_COURSE_PARTICIPANTS_BY_ID,        
        MOODLE_USER_DELETE_USERS,        
        MOODLE_USER_UPDATE_USERS,        

        // ENROL
        MOODLE_ENROL_GET_ENROLLED_USERS,        
        MOODLE_ENROL_GET_USERS_COURSES,        
        MOODLE_ENROL_MANUAL_ENROL_USERS,        

        // ROLE
        MOODLE_ROLE_ASSIGN,        
        MOODLE_ROLE_UNASSIGN,        

        // COURSE
        MOODLE_COURSE_GET_COURSES,        
        MOODLE_COURSE_CREATE_COURSES,        

        // MESSAGE
        MOODLE_MESSAGE_SEND_INSTANTMESSAGES,        

        // NOTES
        MOODLE_NOTES_CREATE_NOTES,        

        // WEBSERVICE
        MOODLE_WEBSERVICE_GET_SITEINFO,

        ENCODING {
                public String toString() {
                        return "UTF-8";
        
                }
        }
}
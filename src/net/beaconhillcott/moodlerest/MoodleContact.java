/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
* Class to hold the state of a MoodleUserContact object. Used when creating or
* fetching contact information in Moodle
*
* @see MoodleRestContact
* @author MoodyProject Team
* @contributor SérgioFilipe
*/

public class MoodleContact implements Serializable {

        /**
         *
         */
        private MoodleUser contactProfile = null;
        /**
         *
         */
        private Long unread = null;
        /**
         * the state of the relationship ONLINE - the user is a contact and is
         * online. OFFLINE - the user is a contact and is offline. STRANGER - the
         * user is not a contact but has sent an invitation to become one.
         */
        private MoodleContactState state = null;

        /**
         * Empty Constructor
         */
        public MoodleContact() {

        }

        /**
         * @param the
         * number of unread messages from this user
         */
        public MoodleContact(Long unread) {

                setUnread(unread);
                setContactProfile(new MoodleUser());
                setState(null);

        }

        /**
         * @param the
         * user contact
         */
        public MoodleContact(MoodleUser user) {

                this(Long.valueOf(0));
                setContactProfile(user);

        }

        /**
         * @param the
         * id of the contact
         * @param the
         * fullname of the contact
         * @param the
         * profileImageURL of the contact
         * @param the
         * profileImageURLSmall of the contact
         */
        public MoodleContact(Long idUserContact, String fullname,
                        String profileImageURL, String profileImageURLSmall) {

                this(Long.valueOf(0));
                setContactProfile(idUserContact, fullname, profileImageURL,
                                profileImageURLSmall);

        }

        /**
         * @param the
         * state of the contact
         */
        public MoodleContact(MoodleContactState state) {

                this(Long.valueOf(0));
                setState(state);

        }

        /**
         * @param the
         * id of the contact
         * @param the
         * fullname of the contact
         * @param the
         * profileImageURL of the contact
         * @param the
         * profileImageURLSmall of the contact
         * @param the
         * state of the contact relation
         */
        public MoodleContact(Long idUserContact, String fullname,
                        String profileImageURL, String profileImageURLSmall,
                        MoodleContactState state) {

                this(idUserContact, fullname, profileImageURL, profileImageURLSmall);
                setState(state);

        }

        /**
         * @param the
         * id of the contact
         * @param the
         * fullname of the contact
         * @param the
         * profileImageURL of the contact
         * @param the
         * profileImageURLSmall of the contact
         * @param the
         * number of unread messages from this user
         */
        public MoodleContact(Long idUserContact, String fullname,
                        String profileImageURL, String profileImageURLSmall, Long unread) {

                this(idUserContact, fullname, profileImageURL, profileImageURLSmall);
                setUnread(unread);

        }

        /**
         * @param the
         * id of the contact
         * @param the
         * fullname of the contact
         * @param the
         * profileImageURL of the contact
         * @param the
         * profileImageURLSmall of the contact
         * @param the
         * state of the contact relationship.
         * @param the
         * number of unread messages from this user
         */
        public MoodleContact(Long idUserContact, String fullname,
                        String profileImageURL, String profileImageURLSmall,
                        MoodleContactState state, Long unread) {

                this(idUserContact, fullname, profileImageURL, profileImageURLSmall,
                                unread);
                setState(state);

        }

        /**
         * @return the user contact
         */
        public MoodleUser getContactProfile() {
                return contactProfile;
        }

        /**
         * @return the number of unread messages from this user
         */
        public Long getUnread() {
                return unread;
        }

        /**
         * @return the state of the relationship with the contact
         */
        public MoodleContactState getState() {
                return state;
        }

        /**
         * Method to set the MoodleUserContact object information atribute.
         *
         * @param the
         * id of the contact
         * @param the
         * fullname of the contact
         * @param the
         * profileImageURL of the contact
         * @param the
         * profileImageURLSmall of the contact
         */
        public void setContactProfile(Long idUserContact, String fullname,
                        String profileImageURL, String profileImageURLSmall) {

                MoodleUser user = new MoodleUser();

                user.setId(idUserContact);
                user.setFullname(fullname);
                user.setProfileImageURL(profileImageURL);
                user.setProfileImageURLSmall(profileImageURLSmall);

                setContactProfile(user);
        }

        /**
         * Method to set the MoodleUserContact object atribute.
         *
         * @param the
         * user contact to set
         */
        public void setContactProfile(MoodleUser contact) {
                this.contactProfile = contact;
        }

        /**
         * Method to set the unread atribute of the MoodleUserContact object.
         *
         * @param the
         * number of unread messages from this user to set
         */
        public void setUnread(Long unread) {
                this.unread = unread;
        }

        /**
         * @param enumerable
         * state that defines de relationship nature with the user of the
         * contact
         */
        public void setState(MoodleContactState state) {
                this.state = state;
        }

        /**
         * <p>
         * setMoodleUserContactField sets an attribute with type conversion from the
         * name of the attribute and a value, both passed as strings.
         * </p>
         *
         * @param the
         * name of the node
         * @param the
         * content of the node
         */
        public void setMoodleUserContactField(String nodeName, Object content) {
                if (nodeName.equalsIgnoreCase("unread") && content != null)
                        setUnread(Long.valueOf((String) content));

                if ((nodeName.equalsIgnoreCase("id")
                                || nodeName.equalsIgnoreCase("fullname")
                                || nodeName.equalsIgnoreCase("profileimageurl") || nodeName
                                        .equalsIgnoreCase("profileimageurlsmall"))
                                && content != null)
                        getContactProfile().setMoodleUserField(nodeName, (String) content);

                if (nodeName.equals("state") && content != null)
                        setState((MoodleContactState) content);
        }

}

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

import java.io.Serializable;

/**
 * Class to store the name and version details of a Moodle webservice.
 *
 * @author Bill Antonia
 */
public class Function implements Serializable {
  private String name=null;
  private Double version;

  /**
   * Constructor for bean requirements
   */
  public Function() {}

  /**
   * Constructor for Moodle webservice function
   *
   * @param name String
   * @param version double
   */
  public Function(String name, Double version) {
      this.name=name;
      this.version=version;
  }

  /**
   * Method to return the name of the webservice function
   *
   * @return String
   */
  public String getName() {
      return name;
  }

  /**
   * Method to return the version of the webservice function
   *
   * @return double
   */
  public Double getVersion() {
      return version;
  }

  /**
   * Method to set the name of a webservice function within the object
   *
   * @param name
   */
  public void setName(String name) {
      this.name=name;
  }
  
  /**
   * Method to set the version of a webservice function within the object
   *
   * @param version double
   */
  public void setVersion(Double version) {
      this.version=version;
  }

  /**
   * Method to set a field given its field name and value as Strings, the value for the "version" field is parsed to a double within the method.
   * 
   * @param field String
   * @param value String
   */
  public void setFunctionField(String field, String value) {
      if (field.equals("name") && !value.equals("")) setName(value);
      if (field.equals("version") && !value.equals("")) setVersion(Double.parseDouble(value));
  }
}

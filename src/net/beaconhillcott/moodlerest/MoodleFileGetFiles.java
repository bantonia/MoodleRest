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

import java.util.ArrayList;
import java.io.Serializable;

/**
 * <p>Class to used to create objects to enable the storage of data returned from MoodleRestFile.getFiles</p>
 *
 * @author Bill Antonia
 * @see MoodleRestFile
 */
public class MoodleFileGetFiles implements Serializable {
  
  private ArrayList<MoodleFileParent> parents=null;
  private ArrayList<MoodleFileFile> files=null;
  
  /**
   * <p>Constructor which initialises the array storage for the returned results of a MoodleRestFile.getFiles.</p>
   */
  public MoodleFileGetFiles() {
    parents=new ArrayList<MoodleFileParent>();
    files=new ArrayList<MoodleFileFile>();
  }
  
  /**
   * <p>Method to return a pointer to the ArrayList containing the returned MoodleFileParent objects</p>
   *
   * @return ArrayList containing MoodleFileParent objects
   * @see MoodleFileParent
   */
  public ArrayList<MoodleFileParent> getParents() {
    return parents;
  }
  
  /**
   * <p>Method to return a pointer to the ArrayList containing the returned MoodleFileFile objects</p>
   *
   * @return ArrayList containing MoodleFileFile objects
   * @see MoodleFileFile
   */
  public ArrayList<MoodleFileFile> getFiles() {
    return files;
  }
  
  /**
   * <p>Method to add a MoodleFileParent object to the ArrayList.</p>
   * 
   * @param parent MoodleFileParent
   * @see MoodleFileParent
   */
  public void addParent(MoodleFileParent parent) {
    parents.add(parent);
  }
  
  /**
   * <p>Method to add a MoodleFileFile object to the ArrayList.</p>
   *
   * @param file MoodleFileFile
   * @see MoodleFileFile
   */
  public void addFile(MoodleFileFile file) {
    files.add(file);
  }
}

/**
 * @author Nurbek Malikov
 */

package wordmap;

import java.util.*;

public class Occurrences  
{
    private Map< String, Map< String, Set< Position >>> occ; 
        // Maps words -> filename -> sets of their Positions in the file.
 
    public Occurrences( )
    {
        occ = new TreeMap<> ( ); 
    }

    public void put( String word, String filename, Position pos ) {
        if ( !occ.containsKey(word) )  {
            Map< String, Set<Position> > newFileMap = new TreeMap<>();
            Set< Position > newPosSet = new TreeSet<>();

            newPosSet.add(pos);
            newFileMap.put(filename, newPosSet);
            occ.put(word, newFileMap);
        } else if ( !occ.get(word).containsKey(filename) ) {
            Set< Position > newPosSet = new TreeSet<>();
            newPosSet.add(pos);

            occ.get(word).put(filename, newPosSet);
        } else if ( !occ.get(word).get(filename).contains(pos) ) {
            occ.get(word).get(filename).add(pos);
        }
    }


    public int countWords( ) {
        return occ.size();
    }


    public int count( ) {
        int result = 0;
        for (String word : occ.keySet()) {
            result += count(word);
        }
        return result;
    }

    public int count( String word ) {
        int result = 0;

        if (occ.containsKey(word)) {
            for (String file : occ.get(word).keySet()) {
                result += count(word, file);
            }
        }

        return result;
    }

    public int count( String word, String file ) {
        if (occ.containsKey(word)) {
            if (occ.get(word).containsKey(file))
                return occ.get(word).get(file).size();
        }
        return 0;
    }

    public String toString( ) {
        String result = "\n";

        for (String w : occ.keySet()) {
            result += "word \"" + w + "\" occurs " + count(w) + " times:\n";
            for (String f : occ.get(w).keySet()) {
                result += "   in file \"" + f + "\":\n";

                for (Position p : occ.get(w).get(f))  {
                    result += "      at " + p + "\n";
                }
            }
        }

        result += "\noccurrences      " + count() + "\n";
        result += "distinct words   " + countWords() + "\n\n";

        return result;
    }
}



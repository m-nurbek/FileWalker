/**
 * @author Nurbek Malikov
 */

package wordmap;

public class Position implements Comparable<Position>
{
    final private int line;
    final private int column;

    public Position( int line, int column ) {
        this.line = line;
        this.column = column;
    }

    public Position clone( ) 
    {
        return new Position( line, column );
    }

    public String toString() {
        return "line " + line + ", column " + column;  
    }

    public int compareTo( Position pos ) {
        if (line == pos.line) {
            if (column == pos.column)
                return 0;
            else if (column > pos.column)
                return 1;
            else
                return -1;
        }

        if (line > pos.line)
            return 1;
        return -1;
    }

}



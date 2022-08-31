
package wordmap;

import java.lang.Character;

public interface Syntax {

    /**
     * Interface that determines which characters 
     * will be allowed in a word by our application. 
     *
     * @param ch the character to check
     * @return true if ch should belong to a word
     */

    static boolean isInWord( char ch ) 
    {
        // Apostrophe and hyphen cases:

        if( ch == 0x2019 || ch == 0x27 || ch == '-' )
            return true;

        // Digit cases: 

        if( '0' <= ch && ch <= '9' )
            return true;

        // Alphabetic cases:
        return Character.isLetter(ch);
    }


    static boolean isNewLine( char c )
    {
        return c == '\n';
    }

}



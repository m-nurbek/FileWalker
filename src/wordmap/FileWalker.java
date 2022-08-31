/**
 * @author Nurbek Malikov
 */

package wordmap;
import java.io.File;
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;


public abstract class FileWalker
{

    public static boolean seemsOK( File f )
    {
        return f. exists( ) && f. canRead( ); 
    }

    public static boolean seemsOK( String filename )
    { 
        return seemsOK( new File( filename ));
    }

    public static Occurrences getOccurrences( String filename ) 
    throws FileNotFoundException, IOException
    {
        Occurrences occ = new Occurrences( ); 
        addOccurrences( new File( filename ), occ );
        return occ; 
    } 


    public static void addOccurrences( File file, Occurrences occ )
        throws FileNotFoundException, IOException {
        if (!seemsOK(file)) {
            throw new IOException("No such file exists");
        }
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                addOccurrences(f, occ);
            }
            else if (f.isFile()) {
                addOccurrences(new BufferedReader(new FileReader(f)) , f.getPath(), occ);
            }
        }
    }


    public static
    void addOccurrences( BufferedReader source, String sourcename,
                         Occurrences occ )
    throws IOException  {
        int line = 1;
        int column = 1;


        int ch;
        StringBuffer str = new StringBuffer("");
         do {
            ch=source.read();
            if (Syntax.isInWord((char)ch)) {
                ch = Character.toLowerCase(ch);
                str.append((char)ch);
            }
            else if ( ((char)ch == ' ' || ch == -1 || !Syntax.isInWord((char)ch) || Syntax.isNewLine((char)ch) )
                            && str.length() != 0    ) // this is the word
            {
                Position newpos = new Position(line, column-str.length());
                occ.put(str.toString(), sourcename, newpos);
                str.delete(0,str.length());

                if (Syntax.isNewLine((char)ch)) {
                    line++;
                    column=0;
                }
            }
            else if (Syntax.isNewLine((char)ch)) {
                line++;
                column=0;
            }
            column++;
        } while (ch != -1);

        source.close();
    }
}



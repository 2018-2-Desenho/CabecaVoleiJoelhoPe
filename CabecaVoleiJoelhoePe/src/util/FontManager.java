package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class FontManager {
    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int BOLD_ITALIC = BOLD | ITALIC;
    static private FontManager instance;
    private HashMap<String, Font> fonts;

    private FontManager(){
        fonts = new HashMap<String, Font>();
    }

    static public FontManager getInstance(){
        if (instance == null){
            instance = new FontManager();
        }
        return instance;
    }

    public Font loadFont(String fileName, int size, int style){
        URL url = getClass().getResource("/" + fileName);
        if (url == null){
            throw new RuntimeException("A fonte /" + fileName
                            + " não foi encontrada.");
        } else{
            try {
                Font font = fonts.get(fileName);
                if (font == null){
                    File file = new File(url.toURI());
                    font = Font.createFont(Font.TRUETYPE_FONT, file);
                    fonts.put(fileName, font);
                }
                
                font = font.deriveFont((float) size);
                
                if ((style & BOLD) == BOLD){
                    font = font.deriveFont(Font.BOLD);
                }
                
                if ((style & ITALIC) == ITALIC){
                    font = font.deriveFont(Font.ITALIC);
                }
                
                return font;
            } catch (FontFormatException ex){
                throw new RuntimeException(ex);
            } catch (IOException ex){
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex){
                throw new RuntimeException(ex);
            }
        }
    }   
}

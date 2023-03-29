package Data_Layer;

import java.io.IOException;

public class FileWriter {

    private static String file;
    private static java.io.FileWriter fileWriter;

    public void setFile(String file) {
        FileWriter.file = file;
        try {
            fileWriter = new java.io.FileWriter(FileWriter.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String string){
        try {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


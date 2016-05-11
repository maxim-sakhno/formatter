package ru.sakhno.bootstrap;


import ru.sakhno.formatter.FormatterException;
import ru.sakhno.formatter.JavaFormatter;
import ru.sakhno.reader.FileReader;
import ru.sakhno.reader.ReaderException;
import ru.sakhno.writer.FileWriter;
import ru.sakhno.writer.StringWriter;
import ru.sakhno.writer.WriterException;

import java.io.File;

/**
 * Formatter demonstration.
 */
final class Demonstration {


    private Demonstration() {}


    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main(final String[] args) {
        try {
            File input = new File(args[0]);
            File output = new File(args[1]);
            FileReader fileReader = new FileReader(input);
            FileWriter fileWriter = new FileWriter(output);
            JavaFormatter javaFormatter = new JavaFormatter();
            javaFormatter.format(fileReader, fileWriter);
        } catch (IndexOutOfBoundsException exception) {
            System.out.print("Use: formatter <input_file_name> <output_file_name>\n");
        } catch (ReaderException exception) {
            System.out.print("Can not open input file.\n");
        } catch (WriterException exception) {
            System.out.print("Can not open output file.\n");
        } catch (FormatterException exception) {
            exception.printStackTrace();
        }
    }


}

package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Futil extends SimpleFileVisitor<Path> {
    private static FileChannel outputFileChannel;
    private ByteBuffer buffer;
    Charset in  = Charset.forName("Cp1250");
    Charset out = Charset.forName("UTF-8");

    public static void processDir(String input_directory_str, String output_file_str){

        Path output_file_path = Paths.get(output_file_str);
        Path starting_directory = Paths.get(input_directory_str);

        try{
            outputFileChannel = FileChannel.open(output_file_path, EnumSet.of(CREATE, APPEND));
            Files.walkFileTree(starting_directory, new Futil());
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    private void recodeForUtf(FileChannel input_file_channel, long buffer_size){
        buffer = ByteBuffer.allocate((int)buffer_size +1);
        buffer.clear();

        try {

            input_file_channel.read(buffer);
            buffer.flip();
            CharBuffer cbuf = in.decode(buffer);
            ByteBuffer buf = out.encode(cbuf);

            while( buf.hasRemaining() ){
                outputFileChannel.write(buf);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public FileVisitResult visitFile(Path file_path, BasicFileAttributes attr) {
        System.out.format("VisitedFile: %s ", file_path);
        System.out.println();
        try{
            this.recodeForUtf(FileChannel.open(file_path), attr.size());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file_path, IOException exc) {
        System.err.println("visitFileFailed!");
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }
}
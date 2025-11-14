package src;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        String source = Files.readString(Path.of("example.cobj"), StandardCharsets.UTF_8);

        var tokens = Lexer.tokenize(source);
        var parser = new Parser();
        var program = parser.parseProgram(tokens);

        RuntimeExec.exec(program);
    }
}

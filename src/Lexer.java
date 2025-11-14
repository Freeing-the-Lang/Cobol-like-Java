package src;

import java.util.*;

public class Lexer {

    public enum TokenKind {
        NUMBER,
        IDENT,
        LPAREN,
        RPAREN,
        COMMA,
        EOF
    }

    public static class Token {
        public TokenKind kind;
        public String text;

        Token(TokenKind k, String t) {
            this.kind = k;
            this.text = t;
        }
    }

    public static List<Token> tokenize(String src) {
        List<Token> out = new ArrayList<>();
        int i = 0;
        int n = src.length();

        while (i < n) {
            char c = src.charAt(i);

            if (Character.isDigit(c)) {
                int start = i;
                while (i < n && Character.isDigit(src.charAt(i))) i++;
                out.add(new Token(TokenKind.NUMBER, src.substring(start, i)));
                continue;
            }

            if (Character.isAlphabetic(c)) {
                int start = i;
                while (i < n && Character.isAlphabetic(src.charAt(i))) i++;
                out.add(new Token(TokenKind.IDENT, src.substring(start, i)));
                continue;
            }

            switch (c) {
                case '(' -> out.add(new Token(TokenKind.LPAREN, "("));
                case ')' -> out.add(new Token(TokenKind.RPAREN, ")"));
                case ',' -> out.add(new Token(TokenKind.COMMA,  ","));
            }

            i++;
        }

        out.add(new Token(TokenKind.EOF, ""));
        return out;
    }
}

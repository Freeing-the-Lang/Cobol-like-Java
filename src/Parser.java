package src;

import java.util.*;
import src.Lexer.*;
import src.AST.*;

public class Parser {

    private List<Lexer.Token> tokens;
    private int pos;

    public Program parseProgram(List<Lexer.Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;

        Program p = new Program();

        while (peek().kind != TokenKind.EOF) {
            p.statements.add(parseStmt());
        }
        return p;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private Token advance() {
        return tokens.get(pos++);
    }

    private Stmt parseStmt() {
        Token t = peek();

        // DISPLAY expr
        if (t.kind == TokenKind.IDENT && t.text.equals("DISPLAY")) {
            advance(); // eat DISPLAY
            Stmt s = new Stmt();
            s.kind = StmtKind.DISPLAY;
            s.expr = parseExpr();
            return s;
        }

        // default: CALL
        Stmt s = new Stmt();
        s.kind = StmtKind.CALL;
        s.expr = parseExpr();
        return s;
    }

    private Expr parseExpr() {
        Token t = advance();
        Expr e = new Expr();

        switch (t.kind) {
            case NUMBER -> {
                e.kind = ExprKind.NUMBER;
                e.value = t.text;
            }
            case IDENT -> {
                e.kind = ExprKind.IDENT;
                e.name = t.text;

                // call form: IDENT(expr, expr...)
                if (peek().kind == TokenKind.LPAREN) {
                    advance(); // (
                    e.kind = ExprKind.CALL;
                    while (peek().kind != TokenKind.RPAREN) {
                        e.args.add(parseExpr());
                        if (peek().kind != TokenKind.COMMA) break;
                        advance(); // comma
                    }
                    advance(); // )
                }
            }
            default -> {
                e.kind = ExprKind.IDENT;
                e.name = "";
            }
        }

        return e;
    }
}

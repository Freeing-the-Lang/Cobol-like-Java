package src;

import java.util.*;

public class AST {

    public enum ExprKind {
        NUMBER,
        IDENT,
        CALL
    }

    public static class Expr {
        public ExprKind kind;
        public String value;
        public String name;
        public List<Expr> args = new ArrayList<>();
    }

    public enum StmtKind {
        DISPLAY,
        CALL
    }

    public static class Stmt {
        public StmtKind kind;
        public Expr expr;
    }

    public static class Program {
        public List<Stmt> statements = new ArrayList<>();
    }
}

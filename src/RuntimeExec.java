package src;

import src.AST.*;

public class RuntimeExec {

    public static void exec(Program p) {
        for (Stmt s : p.statements) {
            switch (s.kind) {
                case DISPLAY -> displayExpr(s.expr);
                case CALL -> System.out.println("[CALL ignored]");
            }
        }
    }

    private static void displayExpr(Expr e) {
        switch (e.kind) {
            case NUMBER -> System.out.println(e.value);
            case IDENT -> System.out.println(e.name);
            case CALL -> System.out.println("[call expr]");
        }
    }
}

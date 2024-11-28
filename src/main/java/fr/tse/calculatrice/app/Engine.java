//ss

package main.java.fr.tse.calculatrice.app;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Engine {
    private ScriptEngine scriptEngine;

    public Engine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName("JavaScript");
    }

    public String evaluateExpression(String expression) throws ScriptException {
        return scriptEngine.eval(expression).toString();
    }
}

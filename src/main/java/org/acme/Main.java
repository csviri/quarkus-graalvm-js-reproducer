package org.acme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@QuarkusMain
public class Main {
    public static void main(String... args) {
        Quarkus.run(MyApp.class,
                (exitCode, exception) -> {
                    // do whatever
                },
                args);
    }

    public static class MyApp implements QuarkusApplication {
        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            String jsscript = """
                     x = 1;
                     x<2;
                    """;

            CompiledScript script = ((Compilable) engine).compile(jsscript);
            var res = (boolean) script.eval();
            System.out.println("Result: "+res);
            System.out.println("Engine: "+engine);
            return 0;
        }
    }
}
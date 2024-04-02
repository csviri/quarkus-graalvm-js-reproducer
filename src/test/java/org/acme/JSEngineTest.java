package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.script.*;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class JSEngineTest {

    @Test
    void testEngine() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        String jsscript = """
                     x = 1;
                     x<2;
                    """;

        CompiledScript script = ((Compilable) engine).compile(jsscript);
        var res = (boolean) script.eval();

        assertThat(engine).isNotNull();
        assertThat(res).isTrue();
    }

}

package org.hexagonoframework.textparser.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class ErrorTest {

	@Test
    public void deveObterMesmoType() {
        ErrorType type = TestErrorType.ERR_SIMPLES;
        Error error = Error.of(type);
        assertEquals(error.getType(), type);
    }
	
    @Test
    public void deveObterMesmaMensagemDoTemplate() {
        ErrorType type = TestErrorType.ERR_SIMPLES;
        Error error = Error.of(type);
        String message = error.getMessage();
        assertEquals(type.getPattern(), message);
    }

    @Test
    public void deveObterMesmaMensagemSimples() {
        ErrorType type = TestErrorType.ERR_SIMPLES;
        Error error = Error.of(type);
        String message = error.getMessage();
        assertEquals("Mensagem simples de erro", message);
    }

    @Test
    public void deveObterMesmaMensagemComParametros() {
        ErrorType type = TestErrorType.ERR_COM_PARAMETROS;
        Error error = Error.of(type, 1, 2);
        String message = error.getMessage();
        assertEquals("Mensagem de erro com parametros 1=1 e 2=2", message);
    }
    
    @Test
    public void deveSerMesmoErro() {
        ErrorType type = TestErrorType.ERR_COM_PARAMETROS;
        Error error1 = Error.of(type, 1, 1);
        Error error2 = Error.of(type, 1, 1);
        assertTrue(error1.equals(error2));
    }
    
    @Test
    public void deveSerErroDiferente() {
        Error error1 = Error.of(TestErrorType.ERR_SIMPLES);
        Error error2 = Error.of(TestErrorType.ERR_COM_PARAMETROS);
        assertFalse(error1.equals(error2));
    }
    
    @Test
    public void naoDeveSerMesmoErroQuandoParametrosDiferentes() {
        Error error1 = Error.of(TestErrorType.ERR_COM_PARAMETROS, 1, 1);
        Error error2 = Error.of(TestErrorType.ERR_COM_PARAMETROS, 2, 2);
        assertFalse(error1.equals(error2));
    }     

    private enum TestErrorType implements ErrorType {

        ERR_SIMPLES("Mensagem simples de erro"),
        ERR_COM_PARAMETROS("Mensagem de erro com parametros 1={0} e 2={1}");

        private String message;

        private TestErrorType(String message) {
            this.message = message;
        }

        @Override
        public String getPattern() {
            return message;
        }
    }
}

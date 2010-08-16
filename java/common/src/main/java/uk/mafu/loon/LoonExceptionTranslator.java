package uk.mafu.loon;

public interface LoonExceptionTranslator {
	RuntimeException translate(Throwable t);
}

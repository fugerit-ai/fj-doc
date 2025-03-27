package test.org.fugerit.java.doc.sample.handlertest;

import org.fugerit.java.doc.mod.fop.FreeMarkerFopTypeHandler;
import org.fugerit.java.doc.mod.fop.PdfFopTypeHandler;
import org.junit.jupiter.api.Test;

public class TestFullFopPDF extends TestHandleBase {

	@Test
	void testPDF() {
		this.testWorker( "handler_full_test" , 
				FreeMarkerFopTypeHandler.HANDLER_UTF8, 
				PdfFopTypeHandler.HANDLER );
	}
	
}

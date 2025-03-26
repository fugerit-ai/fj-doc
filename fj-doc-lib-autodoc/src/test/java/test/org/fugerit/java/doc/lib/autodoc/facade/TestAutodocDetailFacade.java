package test.org.fugerit.java.doc.lib.autodoc.facade;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileOutputStream;

import org.fugerit.java.doc.lib.autodoc.VenusAutodocFacade;
import org.fugerit.java.doc.lib.autodoc.detail.model.AutodocDetail;
import org.fugerit.java.doc.lib.autodoc.facade.AutodocDetailFacade;
import org.fugerit.java.doc.lib.autodoc.parser.model.AutodocModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TestAutodocDetailFacade {

	private static final Logger logger = LoggerFactory.getLogger( TestAutodocDetailFacade.class );
	
	private static final String TARGET = "target/autodoc-detail.xml";
	
	@Test
	void testParseXsdModel() {
		try ( FileOutputStream fos = new FileOutputStream( new File( TARGET ) ) )  {
			AutodocModel autodocModel = VenusAutodocFacade.parseLast();
			AutodocDetail autodocDetail = AutodocDetailFacade.populateStub(autodocModel);
			AutodocDetailFacade.marshal( autodocDetail, fos, true, false );
		} catch (Exception e) {
			String message = "Error : "+e;
			logger.error( message, e );
			fail( message );
		}
	}
	
}

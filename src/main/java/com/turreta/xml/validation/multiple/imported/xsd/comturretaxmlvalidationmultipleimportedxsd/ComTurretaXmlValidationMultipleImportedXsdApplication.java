package com.turreta.xml.validation.multiple.imported.xsd.comturretaxmlvalidationmultipleimportedxsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@SpringBootApplication
public class ComTurretaXmlValidationMultipleImportedXsdApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ComTurretaXmlValidationMultipleImportedXsdApplication.class, args);

		Schema schema = getSchemaToValidateAgainst();

		try {
			// Validates CustomerDetails01.xml
			schema.newValidator().validate(new StreamSource(
					new File(ComTurretaXmlValidationMultipleImportedXsdApplication.class
							.getClassLoader().getResource("xml/CustomerDetails01.xml").getFile())
			));
			System.out.println("No Exceptions thrown. CustomerDetails01.xml is valid!");
		}
		catch(Exception e)
		{
			System.out.println("Exceptions thrown. CustomerDetails01.xml is invalid!");
		}


		try {
			// Validates Purchase01.xml
			schema.newValidator().validate(new StreamSource(
					new File(ComTurretaXmlValidationMultipleImportedXsdApplication.class
							.getClassLoader().getResource("xml/Purchase01.xml").getFile())
			));
			System.out.println("No Exceptions thrown. Purchase01.xml is valid!");

		}
		catch(Exception e)
		{
			System.out.println("Exceptions thrown. Purchase01.xml is invalid!");
		}


		try {
			// Validates CustomerDetails01.xml
			schema.newValidator().validate(new StreamSource(
					new File(ComTurretaXmlValidationMultipleImportedXsdApplication.class
							.getClassLoader().getResource("xml/CustomerDetails02-invalid.xml").getFile())
			));
			System.out.println("No Exceptions thrown. CustomerDetails02-invalid.xml is valid!");
		}
		catch(Exception e)
		{
			System.out.println("Exceptions thrown. CustomerDetails02-invalid.xml is invalid!");
		}


	}


	private static Schema getSchemaToValidateAgainst() throws Exception
	{
		// We reference the main XSD that imports other XSD files
		String mainXsdStr = "myxsds/Main.xsd";

		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = schemaFactory.newSchema(
				ComTurretaXmlValidationMultipleImportedXsdApplication.class
				.getClassLoader().getResource(mainXsdStr));

		return schema;
	}
}
